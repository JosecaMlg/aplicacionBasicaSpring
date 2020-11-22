package com.apijccp.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.csrf.LazyCsrfTokenRepository;

import com.apijccp.filter.CustomCsrfFilter;
import com.apijccp.filter.CustomFilter;
import com.apijccp.security.RestAuthenticationEntryPoint;
import com.apijccp.service.spring.security.SeguridadService;
import com.apijccp.service.spring.security.SimpleUserDetailsService;

@Configuration
@ComponentScan(basePackages = {
		"com.apijccp.security",
		"com.apijccp.basic"
		})
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired 
	private RestAuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		authenticationProvider.setUserDetailsService(userDetailsService());
		auth.authenticationProvider(authenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/greet/**")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic()
        .authenticationEntryPoint(authenticationEntryPoint);

        //METER AQUI OAUTH2LOGIN
        //METER TAMBIEN TOKEN AUTH
        //PROBAR MULTIHILO
        
        http.addFilterAt(new CustomCsrfFilter(new LazyCsrfTokenRepository(
			new HttpSessionCsrfTokenRepository())),  CsrfFilter.class);
        http.addFilterAfter(new CustomFilter(),
                BasicAuthenticationFilter.class);
	}
	
	//Establece el prefijo con el que empiezan todos los roles...
	@Bean
	GrantedAuthorityDefaults grantedAuthorityDefaults() {
	    return new GrantedAuthorityDefaults("ROL_");
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		return new SimpleUserDetailsService();
	}
	
	@Bean
	public SeguridadService seguridadService() {
		return new SeguridadService();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
}
