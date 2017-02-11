package com.wedevol.iclass.core.service;

import java.util.Date;
import java.util.List;

import com.wedevol.iclass.core.entity.Clase;
import com.wedevol.iclass.core.view.response.ClassFull;

/**
 * Class Service Interface
 * 
 * @author charz
 *
 */
public interface ClassService {

	List<Clase> findAll();

	Clase findById(Long classId);

	Clase create(Clase c);

	void update(Long classId, Clase c);

	void delete(Long classId);

	List<ClassFull> findComingClassesByStudentIdByDateTimeWithClassStatusFilter(Long studentId, Date actualDate,
			Integer actualTime, String statusFilter);

	List<ClassFull> findHistoricClassesByStudentIdWithClassStatusFilter(Long studentId, String statusFilter);

	List<ClassFull> findComingClassesByInstructorIdByDateTimeWithClassStatusFilter(Long instructorId,
			Date actualDate, Integer actualTime, String classStatusFilter);

	List<ClassFull> findHistoricClassesByInstructorIdWithClassStatusFilter(Long instructorId,
			String classStatusFilter);

	void instructorConfirmClass(Long classId, Long instructorId);

	void instructorRejectClass(Long classId, Long instructorId);

	void studentCancelClass(Long classId, Long studentId);

	void rateInstructorClass(Long classId, Long instructorId, Float rating);

	void rateStudentClass(Long classId, Long studentId, Float rating);

}
