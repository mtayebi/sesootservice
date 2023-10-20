package com.mahdi.sesootservice.core.exception.main;

import com.mahdi.sesootservice.entity.DTO.ErrorMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.function.EntityResponse;

@RestControllerAdvice
public class MainExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity finalUnhandledException(RuntimeException e){
        return new ResponseEntity<>("an unknown error occurred try again or" +
                " contact support if the error persist. "+e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
