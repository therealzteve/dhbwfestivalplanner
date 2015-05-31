package test.java;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.model.Budget;
import com.model.Event;
import com.model.User;

@WebAppConfiguration()
@ContextConfiguration(classes = { com.config.security.SecurityConfig.class,
		com.config.AppConfig.class })
public abstract class AbstractTest {

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private SessionFactory sessionFactory;

	private MockMvc mockMvc;

	@Autowired
	private javax.servlet.Filter[] springSecurityFilterChain;

	@Before
	public void setup() throws Exception {
		this.setMockMvc(MockMvcBuilders.webAppContextSetup(this.wac)
				.addFilters(springSecurityFilterChain).build());
	}
	
	protected int prepareTestEvent() {
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

	protected RequestPostProcessor userPresets() {
		return user(createTestUser());
	}

	private User createTestUser() {
		User user = new User();
		user.setId(1);
		user.setName("t");
		user.setPassword("t");
		return user;
	}

	public MockMvc getMockMvc() {
		return mockMvc;
	}

	public void setMockMvc(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

}
