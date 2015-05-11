package com.config;

import java.io.IOException;
import java.util.Properties;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.http.MediaType;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;

import com.factory.EventFactory;

@Configuration
@ComponentScan("com")
@EnableWebMvc
@EnableTransactionManagement
public class AppConfig extends WebMvcConfigurerAdapter {
	private PropertyFactory propertyFactory = new PropertyFactory();

	@Override
	public void configureContentNegotiation(
			ContentNegotiationConfigurer configurer) {
		// Simple strategy: only path extension is taken into account
		configurer.favorPathExtension(true).ignoreAcceptHeader(false)
				.useJaf(false).defaultContentType(MediaType.TEXT_HTML)
				.mediaType("html", MediaType.TEXT_HTML)
				.mediaType("xml", MediaType.APPLICATION_XML)
				.mediaType("json", MediaType.APPLICATION_JSON);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("js/**").addResourceLocations("/views/js/")
				.setCachePeriod(31556926);
		registry.addResourceHandler("style/**")
				.addResourceLocations("/views/style/").setCachePeriod(31556926);
	}

	
	/*
	 * 
	 *  Configure Spring beans:
	 * 
	 */
	
	@Bean(name = "jspViewResolver")
	public UrlBasedViewResolver urlBasedViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

	@Bean
	@Scope("singleton")
	public LocalSessionFactoryBean sessionFactory() throws IOException {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

		DataSource ds = new DataSource();

		try {
			Properties prop = propertyFactory.getDbProperties();

			ds.setUrl(prop.getProperty("url"));
			ds.setDriverClassName(prop.getProperty("driver"));
			ds.setUsername(prop.getProperty("username"));
			ds.setPassword(prop.getProperty("password"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		sessionFactory.setDataSource(ds);
		sessionFactory.setPackagesToScan("com");
		sessionFactory.setHibernateProperties(propertyFactory
				.getHibernateProperties());

		return sessionFactory;
	}

	@Bean
	public HibernateTransactionManager transactionManager(
			SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	TilesConfigurer tilesConfigurer() {
		TilesConfigurer tc = new TilesConfigurer();
		return tc;
	}

	@Bean
	MailSender mailSender() {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost("localhost");
		sender.setPort(23);
		sender.setPassword("1234");
		sender.setUsername("username");

		Properties p = new Properties();
		p.setProperty("mail.transport.protocol", "smtp");
		p.setProperty("mail.smtp.auth", "true");
		p.setProperty("mail.smtp.starttls.enable", "true");
		sender.setJavaMailProperties(p);

		return sender;

	}

	@Bean
	EventFactory eventFactory() {
		EventFactory eventFactory = new EventFactory();
		return eventFactory;
	}
}
