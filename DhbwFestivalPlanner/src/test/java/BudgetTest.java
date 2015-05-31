package test.java;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.ResultActions;

@RunWith(SpringJUnit4ClassRunner.class)
public class BudgetTest extends AbstractTest {



	@Test
	public void addBudget() throws Exception {
		int eventId = prepareTestEvent();

		this.getMockMvc().perform(
				post("/budget/add").param("eventId", Integer.toString(eventId))
						.param("amount", Integer.toString(1))
						.param("price", Float.toString(9.99f))
						.param("name", "testName").with(userPresets())
						.accept(MediaType.TEXT_HTML))
				.andExpect(status().isOk());

	}
	
	@Test
	public void deleteBudget() throws Exception {
		int eventId = prepareTestEvent();

		this.getMockMvc().perform(
				post("/budget/add").param("eventId", Integer.toString(eventId))
						.param("amount", Integer.toString(1))
						.param("price", Float.toString(9.99f))
						.param("name", "testName").with(userPresets())
						.accept(MediaType.TEXT_HTML))
				.andExpect(status().isOk());

	}


}
