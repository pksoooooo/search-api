package com.musinsa.api.search.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoodsPriceResponseDto {

    @JsonProperty("브랜드")
    private String brandName;

    @JsonProperty("카테고리")
    private String categoryName;

    @JsonProperty("가격")
    private String price;

}
