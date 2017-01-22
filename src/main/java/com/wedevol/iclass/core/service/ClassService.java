package com.wedevol.iclass.core.service;

import java.util.Date;
import java.util.List;

import com.wedevol.iclass.core.entity.Clase;
import com.wedevol.iclass.core.entity.ClassFullInfo;

/**
 * Class Service Interface
 * 
 * @author charz
 *
 */
public interface ClassService {

	List<Clase> findAll();

	Clase findById(Long classId);

	void create(Clase c);

	void update(Long classId, Clase c);

	void delete(Long classId);

	List<ClassFullInfo> findClassesByStudentIdByDateTimeWithClassStatusFilter(Long studentId, Date actualDate,
			Integer actualTime, String statusFilter);
	
	List<ClassFullInfo> findClassesByInstructorIdByDateTimeWithClassStatusFilter(Long instructorId, Date actualDate,
			Integer actualTime, String classStatusFilter);

}
