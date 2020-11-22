package com.apijccp.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.apijccp.model.response.error.ErrorResponseModel;
import com.apijccp.model.response.error.ErrorResponseModel.Builder;
import com.apijccp.prop.EnumApiErrors;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Entry Point will not redirect to any sort of Login - it will return the 401
 */
@Component
public final class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Qualifier("mapperJson")
	@Autowired
	ObjectMapper om;
	
    @Override
    public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException authException) throws IOException {
        
    		response.setContentType("application/json");
    		response.setCharacterEncoding("UTF-8");
    		response.setStatus(401);
    		PrintWriter out = response.getWriter();
    		ErrorResponseModel responseJson = getResponseByAuthException(authException);
    		String json = om.writer().writeValueAsString(responseJson);
    		out.print(json);
    		out.flush();
    }
    
    ErrorResponseModel getResponseByAuthException(AuthenticationException authException) {
    	Builder build = ErrorResponseModel.builder();
    	
    	if (authException instanceof CredentialsExpiredException) { //cred Expired
    		build.codError(EnumApiErrors.AUTH_CRED_EXPIRED.getCodError());
    		build.textoDesc("Las su clave de acceso ha expirado, por favor establece una nueva.");
    		build.tituloDesc("Error en acceso");
    	}
    	//PONER AQUI EL RESTO DE EXCEPCIONES
    	else {
    		build.codError(EnumApiErrors.AUTH_NO_IDENTIFICADO.getCodError());
    		build.textoDesc("Ha ocurrido un error al autenticar a su usuario, por favor inténtalo de nuevo.");
    		build.tituloDesc("Error de autenticacion");
    	}
    	
    	
    	return build.build();
    }

}