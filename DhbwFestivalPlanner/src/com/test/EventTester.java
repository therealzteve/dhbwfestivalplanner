package com.test;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.model.User;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration()
@ContextConfiguration(classes  = {com.config.security.SecurityConfig.class ,com.config.AppConfig.class  })
public class EventTester {
	
	@Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private javax.servlet.Filter[] springSecurityFilterChain;
    
    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilters(springSecurityFilterChain).build();
    }

    @Test
    public void getEvent() throws Exception {
        this.mockMvc
        	.perform(get("/event/display?id=1")
        			.with(userPresets())
        			.accept("application/json"))
        	.andExpect(status().isOk());
    }
    
    private RequestPostProcessor userPresets(){
    	
    	User user = new User();
    	user.setId(1);
    	user.setName("t");
    	user.setPassword("t");
    	return user(user);
    }

}
