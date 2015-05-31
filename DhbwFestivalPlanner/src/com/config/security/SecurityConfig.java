package com.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.service.MyUserDetailsService;

@ComponentScan("com.config")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
 
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.authorizeRequests().
	    antMatchers("/login").permitAll().
	    antMatchers("/register").permitAll().
	    antMatchers("/unlock").permitAll().
	    antMatchers("/event/guestView**").permitAll().
	    antMatchers("/style/**").permitAll().
	    antMatchers("/js/**").permitAll().
	    anyRequest()
		.access("hasRole('ROLE_USER')").and().formLogin()
		.loginPage("/login").failureUrl("/login?error")
		.usernameParameter("username")
		.passwordParameter("password").defaultSuccessUrl("/")
		.and().logout().logoutSuccessUrl("/login?logout")
		.and().csrf().disable();


	}
	
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	@Bean
	protected UserDetailsService userDetailsService(){
		MyUserDetailsService myUserDetailsService = new MyUserDetailsService();
		
		return myUserDetailsService;
	}
	
}
