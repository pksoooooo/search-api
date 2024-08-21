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
public class GoodsLowestPriceByCategoryResponseDto {

    @JsonProperty("상품")
    private List<GoodsPriceResponseDto> categoryList;

    @JsonProperty("총액")
    private String totalAmount;

}
