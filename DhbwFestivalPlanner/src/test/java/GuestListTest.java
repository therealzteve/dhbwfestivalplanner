package test.java;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.ResultActions;

@RunWith(SpringJUnit4ClassRunner.class)
public class GuestListTest extends AbstractTest {

	@Test
	public void getEvent() throws Exception {
		
		int eventId = prepareTestEvent();
		ResultActions ra = this.getMockMvc().perform(
				get("/guestList/show?id=" + eventId).with(userPresets()).accept(
						MediaType.TEXT_HTML));

		ra.andExpect(status().isOk());
	}


}
