package com.applyjobsmartly.ajs.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.applyjobsmartly.ajs.util.ResponseModel;
import com.applyjobsmartly.ajs.util.StatusCodeEnum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseModel<Object>> handleNotFound(
            ResourceNotFoundException ex) {

        log.error("Resource not found", ex);

        ResponseModel<Object> response = new ResponseModel<>(
                null,
                ex.getMessage(),
                StatusCodeEnum.NOT_FOUND.getCode(),
                null,
                null,
                null
        );

        return ResponseEntity
                .status(StatusCodeEnum.NOT_FOUND.getCode())
                .body(response);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseModel<Object>> handleCustomException(
            CustomException ex) {

        log.error("Business exception", ex);

        ResponseModel<Object> response = new ResponseModel<>(
                null,
                ex.getMessage(),
                StatusCodeEnum.BAD_REQUEST.getCode(),
                null,
                null,
                null
        );

        return ResponseEntity
                .status(StatusCodeEnum.BAD_REQUEST.getCode())
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseModel<Object>> handleValidationException(
            MethodArgumentNotValidException ex) {

        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getField() + " " + error.getDefaultMessage())
                .orElse("Validation error");

        log.error("Validation error: {}", errorMessage);

        ResponseModel<Object> response = new ResponseModel<>(
                null,
                errorMessage,
                StatusCodeEnum.BAD_REQUEST.getCode(),
                null,
                null,
                null
        );

        return ResponseEntity
                .status(StatusCodeEnum.BAD_REQUEST.getCode())
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseModel<Object>> handleGlobalException(
            Exception ex) {

        log.error("Unhandled exception", ex);

        ResponseModel<Object> response = new ResponseModel<>(
                null,
                StatusCodeEnum.INTERNAL_ERROR.getMessage(),
                StatusCodeEnum.INTERNAL_ERROR.getCode(),
                null,
                null,
                null
        );

        return ResponseEntity
                .status(StatusCodeEnum.INTERNAL_ERROR.getCode())
                .body(response);
    }
}
