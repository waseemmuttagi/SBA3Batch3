package com.wellsfargo.fsd.intrtracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.wellsfargo.fsd.intrtracker.exception.InterviewTrackerException;

@RestControllerAdvice
public class GlobalExceptionController {

	@ExceptionHandler(InterviewTrackerException.class)
	public ResponseEntity<String> handlException(InterviewTrackerException exp) {
		return new ResponseEntity<String>(exp.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handlException(Exception exp) {
		return new ResponseEntity<String>(exp.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public static String errMsgFrom(BindingResult result) {
		List<FieldError> errors = result.getFieldErrors();
		List<String> errMsgs = new ArrayList<>();		
		for (FieldError e : errors) {
			errMsgs.add("@" + e.getField().toUpperCase() + ":" + e.getDefaultMessage());
		}
		return errMsgs.toString();
	}
}
