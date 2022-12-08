package com.project.bookshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class UniqueConstarintViolationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6077174095046771677L;

	public UniqueConstarintViolationException(String message){
		super(message);
	}
}
