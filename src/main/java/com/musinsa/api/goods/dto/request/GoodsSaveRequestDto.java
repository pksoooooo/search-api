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
@Schema(description = "상품 등록 요청 DTO")
public class GoodsSaveRequestDto {

    private int brandId;
    private String categoryName;
    private int price;
}
