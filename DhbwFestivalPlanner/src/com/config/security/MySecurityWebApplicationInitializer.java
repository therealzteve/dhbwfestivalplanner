package com.config.security;


import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;


public class MySecurityWebApplicationInitializer extends
		AbstractSecurityWebApplicationInitializer {
	
	    public MySecurityWebApplicationInitializer() {
	    	
	        super(SecurityConfig.class);
	    }
	
	    

}