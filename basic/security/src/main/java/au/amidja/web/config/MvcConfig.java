package au.amidja.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@ComponentScan({"au.amidja.web.controller"})
@Configuration
public class MvcConfig  extends WebMvcConfigurerAdapter{
	
	 @Override
	 public void addViewControllers(ViewControllerRegistry registry) {
		 registry.addViewController("/").setViewName("home");
		 registry.addViewController("/echo");		 	    
		 registry.addViewController("/login").setViewName("login");
	 }
	 
	 @Override
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
		 registry.addResourceHandler("/resources/**").addResourceLocations("/", "/resources/", "classpath:/META-INF/web-resources/");
	 }
	 	 
	 @Override
	 public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		 configurer.enable();
	 }
	
	@Bean
	//This MessageSource is being used in a web application
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();		
		messageSource.setBasenames("messages", "validation");
		return messageSource;
	}					
}