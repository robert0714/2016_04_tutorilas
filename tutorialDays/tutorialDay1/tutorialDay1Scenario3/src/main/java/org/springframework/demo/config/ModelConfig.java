package org.springframework.demo.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.demo.swing.util.OSValidator;

@Configuration 
@Import(ViewConfig.class)
public class ModelConfig {

	@Bean
	public OSValidator getOSValidator(){
		return new OSValidator();
	}
	 
}
