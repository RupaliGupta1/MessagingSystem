package com.comment.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.comment.exceptions.InValidNameException;
import com.comment.exceptions.NoCommentException;

@ControllerAdvice
public class MyControllerAdvice 
{
	@ExceptionHandler(InValidNameException.class)
	
	public ResponseEntity<String> handleInvalidNameException(InValidNameException exception)
	{
		return new ResponseEntity<String>("Invalid Request \n name should not contain any number or special character",HttpStatus.BAD_REQUEST);
	}
	
@ExceptionHandler(NoCommentException.class)
	
	public ResponseEntity<String> handleInvalidNameException(NoCommentException exception)
	{
		return new ResponseEntity<String>("You have not any comment till date.....",HttpStatus.BAD_REQUEST);
	}
	

}
