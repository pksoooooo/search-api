package com.musinsa.api.search.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(title = "카테고리 별 상품 최저가 최고가 Response dto")
public class GoodsPriceRangeResponseDto {

    @JsonProperty("카테고리")
    @Schema(description = "카테고리")
    private String categoryName;

    @JsonProperty("최저가")
    @Schema(description = "최저가")
    private List<BrandPriceResponseDto> minPrice;

    @JsonProperty("최고가")
    @Schema(description = "최고가")
    private List<BrandPriceResponseDto> maxPrice;

}
