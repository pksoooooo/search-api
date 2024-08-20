package com.musinsa.api.common.handler;

import com.musinsa.api.common.dto.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<RestResponse<Object>> handleJdbcSQLIntegrityConstraintViolationException(SQLException e) {
        RestResponse<Object> restResponse = RestResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
    }
}
