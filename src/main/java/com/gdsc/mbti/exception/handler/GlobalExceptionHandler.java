package com.gdsc.mbti.exception.handler;

import com.gdsc.mbti.exception.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 수정/삭제 시 대상 게시글/댓글이 없는 경우
    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<ErrorResult> handleNoSuchElementException(NoSuchElementException e) {
        final ErrorResult errorResult = ErrorResult.builder()
                .code("NoSuchElementException")
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResult);
    }

    // 비밀번호가 일치하지 않는 경우
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ErrorResult> handleIllegalArgumentException(IllegalArgumentException e) {
        final ErrorResult errorResult = ErrorResult.builder()
                .code("IllegalArgumentException")
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    // 유효하지 않은 MBTI를 입력한 경우
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResult> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        final ErrorResult errorResult = ErrorResult.builder()
                .code("MethodArgumentNotValidException")
                .message(e.getBindingResult().getFieldError().getDefaultMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }
}
