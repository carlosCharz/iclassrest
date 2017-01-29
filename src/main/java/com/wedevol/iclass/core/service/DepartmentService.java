package com.wedevol.iclass.core.service;

import java.util.List;

import com.wedevol.iclass.core.entity.Department;
import com.wedevol.iclass.core.entity.DepartmentId;

/**
 * Department Service Interface
 * 
 * @author charz
 *
 */
public interface DepartmentService {

	List<Department> findAll();

	Department findById(DepartmentId id);

	Department create(Department department);

	void update(DepartmentId id, Department department);

	void delete(DepartmentId id);

}
