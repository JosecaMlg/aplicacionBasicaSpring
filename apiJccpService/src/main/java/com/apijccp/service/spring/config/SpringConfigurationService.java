package com.apijccp.service.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = {
		"com.apijccp.service.*",
		"com.apijccp.dao.impl"
		})
public class SpringConfigurationService implements WebMvcConfigurer{
	
	@Bean 
	public String beanPruebaService(){
		return "miPrimerBean";
	}
	
}
