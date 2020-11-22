package com.apijccp.error;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.apijccp.exception.UsuarioException;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger LOGGER_F = LogManager.getFormatterLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler({ UsuarioException.class })
	 public ResponseEntity<Object> handleUsuarioException(UsuarioException ex, WebRequest request) {
		Map<String, Object> salida = new HashMap<>();
		salida.put("codErr", HttpStatus.I_AM_A_TEAPOT);
		salida.put("tipErr", "UsuarioException");
		salida.put("msg", ex.getMessage());
		
		return new ResponseEntity<Object>(salida, new HttpHeaders(), HttpStatus.I_AM_A_TEAPOT);
	}
	
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<Object> handleUsuarioException(MethodArgumentNotValidException ex, WebRequest request) {
		
		LOGGER_F.error("Error MethodArgumentNotValidException "+ex.getMessage());
		Map<String, Object> salida = new HashMap<>();
		salida.put("codErr", HttpStatus.BAD_REQUEST);
		salida.put("tipErr", "MethodArgumentNotValidException");
		
		String msgFormatter = "Valor de entrada incorrecto {0} en campo {1}";
		List<String> listaErrores =  new ArrayList<>();
		for (FieldError err : ex.getBindingResult().getFieldErrors()) {
			listaErrores.add(MessageFormat.format(msgFormatter, err.getRejectedValue(), err.getField()));
		}
		
		
		salida.put("msg", listaErrores);
		
		return new ResponseEntity<Object>(salida, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
}
