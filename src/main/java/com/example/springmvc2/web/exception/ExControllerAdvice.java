package com.example.springmvc2.web.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandler(IllegalArgumentException e) {
        log.error("[exceptionHandler]", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResult> userExHandler(UserException e) {
        log.error("[exceptionHandler]", e);
        return new ResponseEntity(new ErrorResult("USER-ex", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e) {
        return new ErrorResult("EX", e.getMessage());
    }

    @Data
    @AllArgsConstructor
    static class ErrorResult {

        private String code;
        private String message;
    }

}
