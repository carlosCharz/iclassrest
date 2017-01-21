package com.wedevol.iclass.core.service;

import java.util.Date;
import java.util.List;

import com.wedevol.iclass.core.entity.ClassFullInfo;
import com.wedevol.iclass.core.entity.ClassRoom;

/**
 * Class Service Interface
 * 
 * @author charz
 *
 */
public interface ClassService {

	List<ClassRoom> findAll();

	ClassRoom findById(Long classId);

	void create(ClassRoom c);

	void update(Long classId, ClassRoom c);

	void delete(Long classId);

	List<ClassFullInfo> findClassesByStudentIdByDateTimeWithClassStatusFilter(Long studentId, Date actualDate,
			Integer actualTime, String statusFilter);

}
