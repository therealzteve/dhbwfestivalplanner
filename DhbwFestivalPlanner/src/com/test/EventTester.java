package com.test;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.model.Budget;
import com.model.Event;
import com.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration()
@ContextConfiguration(classes = { com.config.security.SecurityConfig.class,
		com.config.AppConfig.class })
public class EventTester {

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private SessionFactory sessionFactory;

	private MockMvc mockMvc;

	@Autowired
	private javax.servlet.Filter[] springSecurityFilterChain;

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
				.addFilters(springSecurityFilterChain).build();
	}

	@Test
	public void getEvent() throws Exception {
		ResultActions ra = this.mockMvc.perform(
				get("/event/display?id=1").with(userPresets()).accept(
						"application/json"));
		
		ra.andExpect(status().isOk());
	}

	@Test
	public void deleteEvent() throws Exception {
		int eventId = prepareTestEvent();

		this.mockMvc.perform(
				post("/event/delete").param("id", Integer.toString(eventId))
						.with(userPresets()).accept(MediaType.TEXT_HTML))
				.andExpect(status().isOk());

	}

	@Test
	public void addBudget() throws Exception {
		int eventId = prepareTestEvent();

		this.mockMvc.perform(
				post("/budget/add").param("eventId", Integer.toString(eventId))
						.param("amount", Integer.toString(1))
						.param("price", Float.toString(9.99f))
						.param("name", "testName").with(userPresets())
						.accept(MediaType.TEXT_HTML))
				.andExpect(status().isOk());

	}

	private int prepareTestEvent() {
		Event event = new Event();

		event.setCreator(createTestUser());
		
		Budget budget = new Budget();
		
		event.setBudget(budget);
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(budget);
		session.saveOrUpdate(event);
		session.getTransaction().commit();

		return event.getId();
	}

	private RequestPostProcessor userPresets() {
		return user(createTestUser());
	}

	private User createTestUser() {
		User user = new User();
		user.setId(1);
		user.setName("t");
		user.setPassword("t");
		return user;
	}
}
