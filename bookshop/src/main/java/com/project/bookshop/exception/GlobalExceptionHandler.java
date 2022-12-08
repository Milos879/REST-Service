package com.project.bookshop.exception;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	
	
@ExceptionHandler(NoSuchElementFoundException.class)	
public ResponseEntity<Object> handleNoSuchElementFound(NoSuchElementFoundException exception){
	
	return new  ResponseEntity<Object>(exception.getMessage()+"....",HttpStatus.NOT_FOUND);
}


@ExceptionHandler(UniqueConstarintViolationException.class)
public ResponseEntity<Object> handleUniqConstraintViolation(UniqueConstarintViolationException e){
	return new ResponseEntity<Object>(e.getMessage(),HttpStatus.CONFLICT);
}



@ExceptionHandler(IllegalArgumentException.class)
public ResponseEntity<Object> invalidInput(IllegalArgumentException e){
	return new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
}

@ExceptionHandler(BadCredentialsException.class)
public ResponseEntity<Object> authException(BadCredentialsException e){
	System.out.println("ssssssssssssssss");
	return new ResponseEntity<Object>(e.getMessage(),HttpStatus.UNAUTHORIZED);
}





@Override
protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
		HttpStatus status, WebRequest request) {
	
	Map<String, String> body = new LinkedHashMap<>();
	
	
 ex.getBindingResult().getFieldErrors()
	.stream().forEach(e-> body.put(e.getField(), e.getDefaultMessage()));
	
   // body.put("erorr",);
	return new ResponseEntity<Object>(body,HttpStatus.BAD_REQUEST);

}


}



