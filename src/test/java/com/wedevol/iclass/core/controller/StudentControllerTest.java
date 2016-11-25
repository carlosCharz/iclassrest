package com.wedevol.iclass.core.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.enums.BadRequestErrorType;
import com.wedevol.iclass.core.enums.NotFoundErrorType;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.service.StudentServiceImpl;
import com.wedevol.iclass.core.util.Util;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private StudentServiceImpl studentService;

	private Student student1;

	private String student1JsonString;

	@Before
	public void init() throws JsonProcessingException {
		student1 = new Student.StudentBuilder("Carlos", "Becerra", "5216031", "carlos@gmail.com", "123456").build();
		student1JsonString = Util.toJsonString(student1);
		student1.setId(1L);
	}

	@Test
	public void findExistingStudent() throws Exception {

		when(studentService.findById(1L)).thenReturn(student1);

		mvc.perform(get("/students/1"))
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.firstName").value("Carlos"))
			.andExpect(jsonPath("$.lastName").value("Becerra"));

		verify(studentService, times(1)).findById(1L);
		verifyNoMoreInteractions(studentService);
	}

	@Test
	public void throwExpcetionWhenGetAndStudentDoesNotExist() throws Exception {

		when(studentService.findById(11L)).thenThrow(new ResourceNotFoundException(NotFoundErrorType.USER_NOT_FOUND));

		mvc.perform(get("/students/11"))
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(status().isNotFound())
			.andExpect(jsonPath("$.errorCode").value(100));

		verify(studentService, times(1)).findById(11L);
		verifyNoMoreInteractions(studentService);
	}

	@Test
	public void throwExceptionWhenCreatingAndStudentExists() throws Exception {

		Mockito.doThrow(new BadRequestException(BadRequestErrorType.BAD_REQUEST_EXCEPTION))
				.when(studentService)
				.create(Mockito.any(Student.class));

		mvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON)
										.content(student1JsonString))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.errorCode").value(400));

		verify(studentService, times(1)).create(Mockito.any(Student.class));
		verifyNoMoreInteractions(studentService);
	}

	@Test
	public void createStudent() throws Exception {

		mvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON)
										.content(student1JsonString))
			.andExpect(status().isCreated());

		verify(studentService, times(1)).create(Mockito.any(Student.class));
		verifyNoMoreInteractions(studentService);
	}

	@Test
	public void throwExceptionWhenCreatingAndPasswordTooLong() throws Exception {

		student1 = new Student.StudentBuilder("Carlos", "Becerra", "5216031", "carlos@gmail.com", "12345678901234567").build();
		student1JsonString = Util.toJsonString(student1);
		mvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON)
										.content(student1JsonString))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.errorCode").value(401));

		verify(studentService, times(0)).create(Mockito.any(Student.class));
		verifyNoMoreInteractions(studentService);
	}

	@Test
	public void throwExceptionWhenUpdatingAndNameTooLong() throws Exception {

		student1 = new Student.StudentBuilder(
				"CarlosCarlosCarlosCarlosCarlosCarlosCarlosCarlosCarlosCarlosCarlosCarlosCarlosCarlosCarlosCarlosCarlos",
				"Becerra", "5216031", "carlos@gmail.com", "123456").build();
		student1JsonString = Util.toJsonString(student1);
		mvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON)
										.content(student1JsonString))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.errorCode").value(401));

		verify(studentService, times(0)).create(Mockito.any(Student.class));
		verifyNoMoreInteractions(studentService);
	}

	// TODO: test validations in update
	/* .contentType(MediaType.APPLICATION_JSON_VALUE) */

}
