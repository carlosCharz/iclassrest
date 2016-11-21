package com.wedevol.iclass.core.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.service.StudentServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

	@InjectMocks
	private StudentController studentController;
	
	@Mock
	private StudentServiceImpl studentService;
	
	private MockMvc mockMvc;

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
	}
	
	@Test
	public void noParamGreetingShouldReturnDefaultMessage() throws Exception {
		//TODO: implement
		Student student1 = new Student.StudentBuilder("Carlos", "Becerra", "1234567", "carlos@gmail.com",
				"sfdt4ygdgdsda").build();

		final MvcResult result = mockMvc.perform(get("/students/1"))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.content").value("Hello, World!")).andReturn();
	}

	@Test
	public void paramGreetingShouldReturnTailoredMessage() throws Exception {

		this.mockMvc.perform(get("/greeting").param("name", "Spring Community"))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.content").value("Hello, Spring Community!"));
	}

}
