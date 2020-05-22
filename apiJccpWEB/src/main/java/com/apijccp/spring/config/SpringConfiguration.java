package com.apijccp.spring.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.apijccp.interceptor.GlobalInterceptor;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {
		"com.apijccp",
		"com.apijccp.controller",
		"com.apijccp.vo",
		"com.apijccp.error"
		})
public class SpringConfiguration implements WebMvcConfigurer{
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GlobalInterceptor()).addPathPatterns("/*");
    }
	
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    	System.out.println("entro!");
	}

	@Bean 
	public String beanPrueba(){
		return "miPrimerBean";
	}
	
	/**
	 * DEFINIMOS LAS PROPIEDADES DE LA APLICACION
	 */
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties(){
	    PropertySourcesPlaceholderConfigurer pspc
	      = new PropertySourcesPlaceholderConfigurer();
	    Resource[] resources = new ClassPathResource[ ]
	      { new ClassPathResource( "apijccp.properties" ) };
	    pspc.setLocations( resources );
	    pspc.setIgnoreUnresolvablePlaceholders( true );
	    return pspc;
	}
}
