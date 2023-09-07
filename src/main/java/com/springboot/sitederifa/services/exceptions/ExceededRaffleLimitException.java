package com.springboot.sitederifa.services.exceptions;

public class ExceededRaffleLimitException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ExceededRaffleLimitException(String message) {
        super(message);
    }

}
