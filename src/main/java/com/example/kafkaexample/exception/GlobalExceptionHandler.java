package com.example.kafkaexample.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /*
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", new Date().toString());
        error.put("error", "Validation Error(s)");
        Map<String, String> validationErrors = new HashMap<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach(err ->validationErrors.put(((FieldError)err).getField(), err.getDefaultMessage()));
        error.put("errors", validationErrors);
        return ResponseEntity.badRequest().body(error);
    }*/

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", new Date().toString());
        error.put("error", "Validation Error(s)");
        Map<String, String> validationErrors = new HashMap<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach(err -> validationErrors.put(((FieldError)err).getField(), err.getDefaultMessage()));
        error.put("errors", validationErrors);
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<Object> handleTaskNotFoundException(TaskNotFoundException exception) {
        Map<String, String> error = new HashMap<>();
        error.put("timestamp", new Date().toString());
        error.put("error", "Business Error(s)");
        error.put("message", exception.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception exception) {
        Map<String, String> error = new HashMap<>();
        error.put("timestamp", new Date().toString());
        error.put("error", "Internal Server Error(s)");
        error.put("message", exception.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }

}
