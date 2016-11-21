package com.wedevol.iclass.core.service;

import java.util.List;

import com.wedevol.iclass.core.entity.Student;

public interface StudentService {

	List<Student> findAll();

	Student findByEmail(Student student);

	Student findById(Long userId);

	void create(Student student);

	void update(Long userId, Student student);

	void delete(Long userId);

}
