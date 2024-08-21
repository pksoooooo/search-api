package com.musinsa.api.search.dto.response;

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
@Schema(description = "상품 최저가 응답 dto")
public class GoodsLowestPriceResponseDto {

    @JsonProperty("최저가")
    @Schema(description = "최저가")
    private GoodsLowestPriceByBrandResponseDto minPrice;

}
