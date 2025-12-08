package com.learning.url_shortener.aop;

import com.learning.url_shortener.exception.CustomDbException;
import com.learning.url_shortener.models.dto.ErrorMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomDbException.class)
    public ResponseEntity<ErrorMessageDto> customDbException(CustomDbException exception) {
        return new ResponseEntity<>(new ErrorMessageDto(HttpStatus.CONFLICT.value(),
                exception.getMessage()), HttpStatus.CONFLICT);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorMessageDto> handleGlobalException(Exception exception) {
//        return new ResponseEntity<>(new ErrorMessageDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage()), HttpStatus.CONFLICT);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        // WRONG: This might be what you have now
        // return new ResponseEntity<>(error, HttpStatus.CONFLICT);

        // CORRECT: It should be 500
        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }
}
