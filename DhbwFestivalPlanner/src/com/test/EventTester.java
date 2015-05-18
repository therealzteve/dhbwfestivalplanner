package com.test;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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
        login();
    }
    
    public void login() throws Exception{

    }

    @Test
    public void getAccount() throws Exception {
    	//this.mockMvc.perform(post("/register").requestAttr("username", "t").requestAttr("password", "t").requestAttr("email", "test@test.de"));
    	HttpSession session = this.mockMvc.perform(post("/login").param("username", "t").param("password", "t")).andReturn().getRequest().getSession();
        this.mockMvc.perform(get("/event/display?id=1").accept("application/json").session((MockHttpSession)session)).andExpect(status().isOk());
//            .andExpect(content().contentType("application/json"))
//            .andExpect(jsonPath("$.name").value("Lee"));
    }

}
