package com.apijccp.exception;


public class NoUserAuthenticatedException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NoUserAuthenticatedException(String string) {
		super(string);
	}
	
}
