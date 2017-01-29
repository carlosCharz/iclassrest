package com.wedevol.iclass.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.Department;
import com.wedevol.iclass.core.entity.DepartmentId;

/**
 * Department Repository
 * 
 * @author charz
 *
 */
@Repository
@Transactional
public interface DepartmentRepository extends CrudRepository<Department, DepartmentId> {

}
