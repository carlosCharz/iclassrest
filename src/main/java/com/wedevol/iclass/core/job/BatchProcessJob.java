package com.wedevol.iclass.core.job;

import java.util.List;

import org.quartz.DisallowConcurrentExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wedevol.iclass.core.entity.BatchNotification;
import com.wedevol.iclass.core.entity.Clase;
import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.entity.enums.ClassStatusType;
import com.wedevol.iclass.core.service.BatchNotificationService;
import com.wedevol.iclass.core.service.ClassService;
import com.wedevol.iclass.core.service.CourseService;
import com.wedevol.iclass.core.service.InstructorService;
import com.wedevol.iclass.core.service.NotificationService;
import com.wedevol.iclass.core.service.StudentService;

/**
 * Batch process job
 *
 * @author Charz++
 */
@DisallowConcurrentExecution
public class BatchProcessJob {

	protected static final Logger logger = LoggerFactory.getLogger(BatchProcessJob.class);

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private BatchNotificationService batchNotificationService;

	@Autowired
	private ClassService classService;

	@Autowired
	private InstructorService instructorService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private StudentService studentService;

	public void execute() {
		logger.info("Batch process job executed");
		processNotificationsToBeSent();
		processConfirmedFinishedClasses();
	}

	private void processNotificationsToBeSent() {
		final List<BatchNotification> batchList = batchNotificationService.getNotificationsToBeSent();
		notificationService.sendBatchNotifications(batchList);
		batchList.forEach(batch -> {
			batchNotificationService.delete(batch.getId());
		});
	}

	private void processConfirmedFinishedClasses() {
		final List<Clase> classes = classService.getConfirmedFinishedClasses();
		classes.forEach(clase -> {
			// Update class to DONE
			Clase newClase = Clase.from(clase.getId());
			newClase.setStatus(ClassStatusType.DONE.getDescription());
			classService.update(newClase.getId(), newClase);
			// The instructor should exist
			final Instructor instructor = instructorService.findById(clase.getInstructorId());
			// The course should exist
			final Course course = courseService.findById(clase.getCourseId());
			// The student should exist
			final Student student = studentService.findById(clase.getStudentId());
			// Send notification to student to rate the instructor
			notificationService.sendRateFinishedClassNotificationToStudent(student.getFcmToken(), instructor, course);
		});
	}

}
