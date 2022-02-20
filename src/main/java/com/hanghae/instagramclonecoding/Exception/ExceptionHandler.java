package com.hanghae.instagramclonecoding.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = { IllegalArgumentException.class })
    public ResponseEntity<Object> handleApiRequestException(IllegalArgumentException ex) {
        Exception exception = new Exception();
        exception.setCode(HttpStatus.BAD_REQUEST);
        exception.setMessage(ex.getMessage());

        return new ResponseEntity(
                exception,
                HttpStatus.BAD_REQUEST
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = { NullPointerException.class })
    public ResponseEntity<Object> handleApiRequestException(NullPointerException ex) {
        Exception exception = new Exception();
        exception.setCode(HttpStatus.BAD_REQUEST);
        exception.setMessage(ex.getMessage());

        return new ResponseEntity(
                exception,
                HttpStatus.BAD_REQUEST
        );
    }
}
