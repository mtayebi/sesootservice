package com.mahdi.sesootservice.core.exception.main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice
public class MainExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity finalUnhandledException(RuntimeException e){
        return new ResponseEntity<>("an unknown error occurred try again or" +
                " contact support if the error persist. "+e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> hibernateValidationError(MethodArgumentNotValidException e){
        return ResponseEntity.ok("check your inputs and try again!!!");
    }
}
