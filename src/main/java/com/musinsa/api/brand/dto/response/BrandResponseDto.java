package com.musinsa.api.brand.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Schema(description = "브랜드 및 상품을 추가 요청 DTO")
public class BrandResponseDto {

    private int brandId;
    private String brandName;

}
