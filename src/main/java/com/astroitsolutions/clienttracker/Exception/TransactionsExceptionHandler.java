package com.astroitsolutions.clienttracker.Exception;

import java.text.ParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class TransactionsExceptionHandler {
    
    @ExceptionHandler(ParseException.class)
    public ResponseEntity<String> handleParseException(ParseException ex) {
        log.error("An error occurred when parsing date: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred when parsing date");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnknownExceptions(Exception ex) {
        log.error("An error occurred when processing request: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred when processing request");
    }
}
