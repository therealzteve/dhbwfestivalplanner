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
public class LoginTest extends AbstractTest {

	/*@Test
	public void getEvent() throws Exception {
		ResultActions ra = this.getMockMvc().perform(
				get("/event/display?id=1").with(userPresets()).accept(
						"application/json"));

		ra.andExpect(status().isOk());
	}*/

	@Test
	public void login() throws Exception {

		this.getMockMvc()
				.perform(
						post("/login")
								.param("username", "Test3")
								.param("password", "test")
								.accept(MediaType.TEXT_HTML))
				.andExpect(status().is3xxRedirection());

	}

}
