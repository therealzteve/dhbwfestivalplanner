package test.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import test.component.IPersonService;
import test.component.PersonService;

@Configuration 
@ComponentScan("test") 
@EnableWebMvc   
public class AppConfig {  
    @Bean  
    public UrlBasedViewResolver setupViewResolver() {  
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();  
        resolver.setPrefix("/views/");  
        resolver.setSuffix(".jsp");  
        resolver.setViewClass(JstlView.class);  
        return resolver;  
    }
    
    @Bean  
    public IPersonService personService() {  
        return new PersonService();  
    }
} 