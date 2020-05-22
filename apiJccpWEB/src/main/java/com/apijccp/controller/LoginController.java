package com.apijccp.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apijccp.service.spring.security.SeguridadService;
import com.apijccp.vo.RespLogin;

@RestController("/login")
public class LoginController {

	private static final Logger LOGGER = LogManager.getLogger(LoginController.class);

	@Autowired
	SeguridadService seguridadService;
	
	@RequestMapping(path= "/getJwtToken",
			method=RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE) 
    public RespLogin getJwtToken() {
		
		LOGGER.info("LoginController :: intentamos obtener el jwtToken...");
		
		String token = seguridadService.getJWTTokenForCurrentAuth();
		RespLogin salida =  new RespLogin();
		salida.setJwtToken(token);
		salida.setIdUser(SecurityContextHolder.getContext().getAuthentication().getName());
		
		LOGGER.info("LoginController :: token generado, mostramos respuesta -> "+salida);
        return salida;
    }
}
