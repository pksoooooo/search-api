package com.musinsa.api.search.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(description = "브랜드 별 상품 최저가 응답 dto")
public class GoodsLowestPriceByBrandResponseDto {

    @JsonProperty("브랜드")
    @Schema(description = "브랜드 명" , example = "A")
    private String brandName;

    @JsonProperty("카테고리")
    @Schema(description = "카테고리" , example = "TOP")
    private List<CategoryPriceResponseDto> categoryInfo;

    @JsonProperty("총액")
    @Schema(description = "가격" , example = "10000")
    private String totalAmount;

}
