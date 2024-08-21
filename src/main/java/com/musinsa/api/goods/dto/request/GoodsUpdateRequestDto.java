package com.musinsa.api.goods.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@Schema(description = "상품 수정 요청 DTO")
public class GoodsUpdateRequestDto {

    @Schema(description = "상품 아이디", example = "0")
    private int goodsId;

    @Schema(description = "카테고리", example = "TOP")
    private String categoryName;

    @Schema(description = "브랜드 아이디", example = "0")
    private int brandId;

    @Schema(description = "상품 가격", example = "10000")
    private Integer price;
}
