package com.musinsa.api.brand.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@Schema(description = "브랜드 및 상품을 추가 요청 DTO")
public class BrandRequestDto {
    @Schema(description = "브랜드 명" , example = "AAAA")
    private String brandName;
    @Schema(description = "카테고리 명" , example = "top")
    private String categoryName;
    @Schema(description = "price" , example = "0")
    private String price;
}
