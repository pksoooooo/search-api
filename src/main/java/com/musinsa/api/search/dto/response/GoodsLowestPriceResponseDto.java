package com.musinsa.api.search.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GoodsLowestPriceResponseDto {

    @JsonProperty("최저가")
    private GoodsLowestPriceByBrandResponseDto minPrice;

}
