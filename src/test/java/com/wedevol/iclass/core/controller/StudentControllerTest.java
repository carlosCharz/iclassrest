package com.wedevol.iclass.core.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.enums.ErrorType;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.service.StudentServiceImpl;
import com.wedevol.iclass.core.util.Util;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

	@InjectMocks
	private StudentController studentController;

	@Mock
	private StudentServiceImpl studentService;

	private MockMvc mockMvc;

	private Student student1;

	@Before
	public void init() {
		mockMvc = MockMvcBuilders	.standaloneSetup(studentController)
									.build();
		student1 = new Student.StudentBuilder("Carlos", "Becerra", "5216031", "carlos@gmail.com", "123456").build();
		student1.setId(1L);
	}

	@Test
	public void findExistingStudent() throws Exception {

		when(studentService.findById(1L)).thenReturn(student1);

		mockMvc	.perform(get("/students/1"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("Carlos"))
				.andExpect(jsonPath("$.lastName").value("Becerra"));

		verify(studentService, times(1)).findById(1L);
		verifyNoMoreInteractions(studentService);
	}

	@Test(expected = ResourceNotFoundException.class)
	public void throwExpcetionWhenFindingNotExistingStudent() throws Exception {

		when(studentService.findById(100L)).thenThrow(new ResourceNotFoundException(ErrorType.RESOURCE_NOT_FOUND));

		mockMvc	.perform(get("/students/100").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.errorCode").value(1));

		verify(studentService, times(1)).findById(100L);
		verifyNoMoreInteractions(studentService);
	}

}
