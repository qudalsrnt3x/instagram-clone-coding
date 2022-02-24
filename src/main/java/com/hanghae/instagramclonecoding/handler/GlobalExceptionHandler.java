package com.hanghae.instagramclonecoding.handler;

import com.hanghae.instagramclonecoding.handler.ex.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateEmailException() {
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.DUPLICATE_EMAIL.getCode(), ErrorCode.DUPLICATE_EMAIL.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEmailNotFoundException() {
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.EMAIL_NOT_FOUND.getCode(), ErrorCode.EMAIL_NOT_FOUND.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordNotCollectException.class)
    public ResponseEntity<ErrorResponse> handlePasswordNotCollectException() {
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.PASSWORD_NOT_COLLECT.getCode(), ErrorCode.PASSWORD_NOT_COLLECT.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException() {
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.USER_NOT_FOUND.getCode(), ErrorCode.USER_NOT_FOUND.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

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
