package com.gdsc.mbti.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResult {
    private String code;
    private String message;
}
