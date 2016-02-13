package org.springframework.demo.config;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; 
import org.springframework.web.servlet.view.InternalResourceViewResolver; 
 

/**
 * Spring MVC configuration for the View Technologies.
 * 
 */
@Configuration
public class WebViewConfig {
	
//	@Bean
//	public ViewResolver viewResolver() {
//		BeanNameViewResolver viewResolver = new BeanNameViewResolver();
//		viewResolver.setOrder(1);
//		return viewResolver;
//	} 
	@Bean
	public InternalResourceViewResolver getIRViewResolver() {
		InternalResourceViewResolver viewResolver;
		viewResolver = new InternalResourceViewResolver();
		viewResolver.setExposedContextBeanNames("hardCodeAuthConfig");
		return viewResolver;
	}
	@Bean(name="hardCodeAuthConfig")
	public HardCodeAuthConfig getHardCodeAuthConfig(){
		return new HardCodeAuthConfig();
	}
	
	 

}
