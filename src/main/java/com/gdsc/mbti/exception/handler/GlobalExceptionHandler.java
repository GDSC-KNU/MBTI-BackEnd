package com.gdsc.mbti.exception.handler;

import com.gdsc.mbti.exception.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<ErrorResult> handleNoSuchElementException(NoSuchElementException e) {
        final ErrorResult errorResult = ErrorResult.builder()
                .code("NoSuchElementException")
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResult);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ErrorResult> handleIllegalArgumentException(IllegalArgumentException e) {
        final ErrorResult errorResult = ErrorResult.builder()
                .code("IllegalArgumentException")
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }
}
