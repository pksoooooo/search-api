package com.musinsa.api.search.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GoodsLowestPriceByBrandResponseDto {

    @JsonProperty("브랜드")
    private String brandName;

    @JsonProperty("카테고리")
    private List<CategoryPriceResponseDto> categoryInfo;

    @JsonProperty("총액")
    private String totalAmount;

}
