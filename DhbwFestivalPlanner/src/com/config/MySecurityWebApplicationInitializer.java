package com.config;


import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;


public class MySecurityWebApplicationInitializer extends
		AbstractSecurityWebApplicationInitializer {
	
	    public MySecurityWebApplicationInitializer() {
	        super(SecurityConfig.class);
	    }
	

}