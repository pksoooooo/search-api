package com.musinsa.api.search.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.musinsa.api.goods.dto.GoodsDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(title = "카테고리 별 상품 최저가/최고가 Response Dto")
public class GoodsPriceByCategoryResponseDto {
    @JsonProperty("카테고리")
    private String categoryName;
    @JsonProperty("최저가")
    private List<GoodsDto> minPrice;
    @JsonProperty("최고가")
    private List<GoodsDto> maxPrice;
}
