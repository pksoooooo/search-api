package com.musinsa.api.goods.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musinsa.api.brand.dto.response.BrandResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Schema(description = "Goods 응답 dto")
public class GoodsResponseDto {

    @Schema(description = "상품 id", example = "0")
    private int goodsId;

    @Schema(description = "카테고리", example = "TOP")
    private String categoryName;

    @Schema(description = "상품 가격", example = "10000")
    private String price;

    @Schema(description = "브랜드 정보")
    @JsonProperty("brandInfo")
    private BrandResponseDto brandInfo;

}
