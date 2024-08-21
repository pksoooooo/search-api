package com.musinsa.api.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponse<T> {

    private Integer code = HttpStatus.BAD_REQUEST.value();
    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    private String message;
    private T data;

}
