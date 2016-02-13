package org.springframework.demo.config;
 

import java.util.Locale;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration; 

@Configuration 
public class ServiceConfig {
	
	
	@Bean 
	public Locale getLocale(){
		return  Locale.ENGLISH;
	}
}
