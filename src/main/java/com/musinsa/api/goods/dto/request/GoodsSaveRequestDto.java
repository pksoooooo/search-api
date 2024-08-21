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
@Schema(description = "상품 등록 요청 dto")
public class GoodsSaveRequestDto {

    @Schema(description = "브랜드 id" , example = "0")
    private int brandId;

    @Schema(description = "카테고리" , example = "TOP")
    private String categoryName;

    @Schema(description = "상품 가격" , example = "1000")
    private int price;

}
