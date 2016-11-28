package com.wedevol.iclass.core.service;

import java.util.List;

import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.entity.StudentCourse;

public interface StudentService {

	List<Student> findAll();

	Student findByEmail(String email);

	Student findById(Long userId);

	void create(Student student);

	void update(Long userId, Student student);

	void delete(Long userId);

	List<StudentCourse> findStudentCourses(Long studentId);

}
