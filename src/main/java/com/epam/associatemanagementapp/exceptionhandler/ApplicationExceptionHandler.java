package com.epam.associatemanagementapp.exceptionhandler;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.epam.associatemanagementapp.exceptions.AssociateException;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleMethodArgumentsNotValid(MethodArgumentNotValidException e, WebRequest request) {
        List<String> errors = e.getAllErrors().stream().map(error -> error.getDefaultMessage()).toList();
        return new ExceptionResponse(new Date().toString(), HttpStatus.BAD_REQUEST.toString(), errors.toString(), request.getDescription(false));
    }
    @ExceptionHandler(AssociateException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse AssociateException(AssociateException e, WebRequest request) {
        return new ExceptionResponse(new Date().toString(), HttpStatus.NOT_FOUND.toString(), e.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleRuntimeException(RuntimeException e, WebRequest request) {
        return new ExceptionResponse(new Date().toString(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), request.getDescription(false));
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handleDataIntegrityViolationException(DataIntegrityViolationException e, WebRequest request) {
        return new ExceptionResponse(new Date().toString(), HttpStatus.CONFLICT.toString(), e.getMessage(), request.getDescription(false));
    }
    
}
