package com.wedevol.iclass.core.service;

import java.util.Date;
import java.util.List;

import com.wedevol.iclass.core.entity.Clase;
import com.wedevol.iclass.core.view.response.ClassResponse;

/**
 * Class Service Interface
 * 
 * @author charz
 *
 */
public interface ClassService extends BaseService<Clase> {

	List<Clase> findAll();

	List<ClassResponse> findComingClassesByStudentIdByDateTimeWithClassStatusFilter(Long studentId, Date actualDate,
			Integer actualTime, String statusFilter);

	List<ClassResponse> findHistoricClassesByStudentIdWithClassStatusFilter(Long studentId, String statusFilter);

	List<ClassResponse> findComingClassesByInstructorIdByDateTimeWithClassStatusFilter(Long instructorId,
			Date actualDate, Integer actualTime, String classStatusFilter);

	List<ClassResponse> findHistoricClassesByInstructorIdWithClassStatusFilter(Long instructorId,
			String classStatusFilter);

	void instructorConfirmClass(Long classId, Long instructorId);

	void instructorRejectClass(Long classId, Long instructorId);

	void studentCancelClass(Long classId, Long studentId);

	void rateInstructorClass(Long classId, Long instructorId, Float rating);

	void rateStudentClass(Long classId, Long studentId, Float rating);

}
