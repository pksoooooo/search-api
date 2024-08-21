package com.musinsa.api.common.handler;

import com.musinsa.api.common.dto.RestResponse;
import com.musinsa.api.common.exception.DataAlreadyExistsException;
import com.musinsa.api.common.exception.NoSuchDataException;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(JdbcSQLIntegrityConstraintViolationException.class)
    public ResponseEntity<RestResponse<Object>> handleJdbcSQLIntegrityConstraintViolationException(JdbcSQLIntegrityConstraintViolationException e) {
        RestResponse<Object> restResponse = RestResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message("이미 등록 된 데이터가 있습니다.")
                .build();
        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<RestResponse<Object>> handleJdbcSQLIntegrityConstraintViolationException(NumberFormatException e) {
        RestResponse<Object> restResponse = RestResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message("NumberFormatException")
                .build();
        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<RestResponse<Object>> handleNullPointerException(NullPointerException e) {
        RestResponse<Object> restResponse = RestResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message("NullPointerException")
                .build();
        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
    }

    @ExceptionHandler(NoSuchDataException.class)
    public ResponseEntity<RestResponse<Object>> handleNoSuchDataException(NoSuchDataException e) {
        RestResponse<Object> restResponse = RestResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
    }

    @ExceptionHandler(DataAlreadyExistsException.class)
    public ResponseEntity<RestResponse<Object>> handleDataAlreadyExistsException(DataAlreadyExistsException e) {
        RestResponse<Object> restResponse = RestResponse.builder()
                .code(HttpStatus.CONFLICT.value())
                .httpStatus(HttpStatus.CONFLICT)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
    }

}
