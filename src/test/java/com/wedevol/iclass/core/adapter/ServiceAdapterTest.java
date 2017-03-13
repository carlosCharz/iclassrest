package com.wedevol.iclass.core.adapter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.view.request.UserView;

/**
 * Service Adapter Test
 *
 * @author Charz++
 */
public class ServiceAdapterTest {
	
	private ServiceAdapter adapter;
	private UserView userView;
	private Student student;
	
	@Before
	public void init() {
		adapter = new ServiceAdapter();
		// User view
		userView = new UserView();
		userView.setFirstName("Carlos");
		userView.setLastName("Becerra");
		userView.setPhone("4567897");
		userView.setEmail("carlos.becerra@gmail.com");
		userView.setPassword("12345678");
		userView.setDeviceId("android123");
		// Student
		student = new Student.StudentBuilder("Carlos", "Becerra", "4567897", "carlos.becerra@gmail.com", "12345678").build();
	}

	@Test
	public void attributeMatchesWithNameAToB() {
		final Student student = adapter.convertTo(userView, Student.class);
		assertEquals(student.getFirstName(), userView.getFirstName());
	}
	
	@Test
	public void attributeMatchesWithNameBToA() {
		final UserView userView = adapter.convertTo(student, UserView.class);
		assertEquals(userView.getFirstName(), student.getFirstName());
	}
	
}

