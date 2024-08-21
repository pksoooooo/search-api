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
@Schema(description = "카테고리 별 상품 가격 응답 dto")
public class GoodsLowestPriceByCategoryResponseDto {

    @JsonProperty("상품")
    @Schema(description = "카테고리")
    private List<GoodsPriceResponseDto> categoryList;

    @JsonProperty("총액")
    @Schema(description = "상품 가격" , example = "10000")
    private String totalAmount;

}
