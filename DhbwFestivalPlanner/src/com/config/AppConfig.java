package com.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.jasper.tagplugins.jstl.core.Url;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;


@Configuration
@ComponentScan("com")
@EnableWebMvc
@EnableTransactionManagement
public class AppConfig extends WebMvcConfigurerAdapter{

	@Bean
	public UrlBasedViewResolver setupViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

	@Bean
	@Scope("singleton")
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

		
		
		DataSource ds = new DataSource();
		
		
		try {
			Properties prop = getProperties();
			
			ds.setUrl(prop.getProperty("url"));
			ds.setDriverClassName(prop.getProperty("driver"));
			ds.setUsername(prop.getProperty("username"));
			ds.setPassword(prop.getProperty("password"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		sessionFactory.setDataSource(ds);
		sessionFactory.setPackagesToScan("com");
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}

	@Bean
	@Autowired
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

	Properties hibernateProperties() {
		return new Properties() {
			{
				setProperty("hibernate.dialect",
						"org.hibernate.dialect.MySQLDialect");
			}
		};
	}
	// @Bean
	// public EventService eventService() {
	// EventServiceImpl eventServiceImpl = new EventServiceImpl();
	// eventServiceImpl.setSessionFactory(sessionFactory());
	// return new EventServiceImpl();
	// }

	// @Bean
	// @Scope(value = "singleton")
	// public SessionFactory sessionFactory(){
	// org.hibernate.cfg.Configuration configuration = new
	// org.hibernate.cfg.Configuration().configure();
	// StandardServiceRegistryBuilder builder = new
	// StandardServiceRegistryBuilder().
	// applySettings(configuration.getProperties());
	// SessionFactory factory =
	// configuration.buildSessionFactory(builder.build());
	// return factory;
	// }
	
	private Properties getProperties() throws IOException{
		String result = "";
		Properties prop = new Properties();
		String propFileName = "com/config/config.properties";
		 
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		if (inputStream == null) {
		throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
		prop.load(inputStream);

		 
		return prop;
	}
	

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("js/**").addResourceLocations("/views/js/").setCachePeriod(31556926);
    	registry.addResourceHandler("style/**").addResourceLocations("/views/style/").setCachePeriod(31556926);
       // registry.addResourceHandler("/js/**").addResourceLocations("/views/").setCachePeriod(31556926);
    }
    

    @Bean
    TilesConfigurer tilesConfigurer(){
    	TilesConfigurer tc = new TilesConfigurer();
    	return tc;
    }
    
    @Bean
    MailSender mailSender(){
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

}