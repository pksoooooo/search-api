package com.musinsa.api.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RestResponse<T> {

    private Integer code = HttpStatus.BAD_GATEWAY.value();
    private HttpStatus httpStatus = HttpStatus.BAD_GATEWAY;
    private String message;
    private T data;

}
