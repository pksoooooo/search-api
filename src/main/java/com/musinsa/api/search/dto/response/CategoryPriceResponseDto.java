package com.musinsa.api.search.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "카테고 가격 응답 dto")
public class CategoryPriceResponseDto {

    @JsonProperty("카테고리")
    @Schema(description = "카테고리" , example = "TOP")
    private String categoryName;
    @JsonProperty("가격")
    @Schema(description = "상품 가격" , example = "10000")
    private String price;

}
