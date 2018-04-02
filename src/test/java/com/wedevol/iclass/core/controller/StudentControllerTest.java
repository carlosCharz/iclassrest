package com.wedevol.iclass.core.controller;

import static com.wedevol.iclass.core.util.CommonUtil.toJsonString;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wedevol.iclass.core.EntryPoint;
import com.wedevol.iclass.core.entity.AccessToken;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.entity.enums.UserType;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.enums.NotFoundErrorType;
import com.wedevol.iclass.core.service.impl.AccessTokenServiceImpl;
import com.wedevol.iclass.core.service.impl.StudentServiceImpl;
import com.wedevol.iclass.core.view.request.UserView;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EntryPoint.class)
@AutoConfigureMockMvc
public class StudentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StudentServiceImpl studentService;
	
	@MockBean
	private AccessTokenServiceImpl accessTokenService;

	private Student student1;

	private String student1JsonString;
	
	private AccessToken accessToken;

	@Before
	public void init() throws JsonProcessingException {
		final Set<String> placeOptionsSet = new HashSet<String>();
		placeOptionsSet.add("house");
		placeOptionsSet.add("university");

		student1 = new Student.StudentBuilder("Carlos", "Becerra", "5216031", "carlos@gmail.com", "123456").build();
		student1.setPlaceOptions(placeOptionsSet);
		student1.setFcmToken("abc");
		student1.setDeviceId("device123");
		// Fix why testing is requiring the placeOptions

		student1JsonString = toJsonString(student1);
		student1.setId(1L);
		
		accessToken = new AccessToken(1L, UserType.STUDENT);
	}

	@Test
	public void findExistingStudent() throws Exception {

		Mockito.when(studentService.findById(1L)).thenReturn(student1);
		Mockito.when(accessTokenService.findByToken(Mockito.anyString())).thenReturn(accessToken);

		mockMvc.perform(get("/students/1")
					.header("authorization", "123456"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("Carlos"))
				.andExpect(jsonPath("$.lastName").value("Becerra"));

		Mockito.verify(studentService, times(1)).findById(1L);
		Mockito.verifyNoMoreInteractions(studentService);
	}

	@Test
	public void throwExpcetionWhenGetAndStudentDoesNotExist() throws Exception {

		Mockito.when(studentService.findById(11L)).thenThrow(
				new ResourceNotFoundException(NotFoundErrorType.STUDENT_NOT_FOUND));
		Mockito.when(accessTokenService.findByToken(Mockito.anyString())).thenReturn(accessToken);

		mockMvc.perform(get("/students/11")
					.header("authorization", "123456"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.code").value(1));

		Mockito.verify(studentService, times(1)).findById(11L);
		Mockito.verifyNoMoreInteractions(studentService);
	}

	@Test
	public void throwExceptionWhenCreatingAndStudentExists() throws Exception {

		Mockito.doThrow(new BadRequestException(BadRequestErrorType.BAD_REQUEST_EXCEPTION))
					.when(studentService)
					.createStudentWithCourse(Mockito.any(UserView.class));

		mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON).content(student1JsonString))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.code").value(1));

		Mockito.verify(studentService, times(1)).createStudentWithCourse(Mockito.any(UserView.class));
		Mockito.verifyNoMoreInteractions(studentService);
	}

	@Test
	public void createStudent() throws Exception {

		mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON).content(student1JsonString)).andExpect(
				status().isCreated());

		Mockito.verify(studentService, times(1)).createStudentWithCourse(Mockito.any(UserView.class));
		Mockito.verifyNoMoreInteractions(studentService);
	}

	@Test
	public void throwExceptionWhenCreatingAndPasswordTooLong() throws Exception {

		student1 = new Student.StudentBuilder("Carlos", "Becerra", "5216031", "carlos@gmail.com",
				"12345678901234567").build();
		student1JsonString = toJsonString(student1);
		mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON).content(student1JsonString))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.code").value(2));

		Mockito.verify(studentService, times(0)).create(Mockito.any(Student.class));
		Mockito.verifyNoMoreInteractions(studentService);
	}

	@Test
	public void throwExceptionWhenUpdatingAndNameTooLong() throws Exception {

		student1 = new Student.StudentBuilder(
				"CarlosCarlosCarlosCarlosCarlosCarlosCarlosCarlosCarlosCarlosCarlosCarlosCarlosCarlosCarlosCarlosCarlos",
				"Becerra", "5216031", "carlos@gmail.com", "123456").build();
		student1JsonString = toJsonString(student1);
		mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON).content(student1JsonString))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.code").value(2));

		Mockito.verify(studentService, times(0)).create(Mockito.any(Student.class));
		Mockito.verifyNoMoreInteractions(studentService);
	}

	// TODO: test validations in update
	/* .contentType(MediaType.APPLICATION_JSON_VALUE) */

}
