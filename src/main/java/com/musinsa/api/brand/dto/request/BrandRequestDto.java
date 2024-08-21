package com.musinsa.api.brand.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@Schema(description = "브랜드 및 상품을 추가 요청 DTO")
public class BrandRequestDto {
    @Schema(description = "브랜드 id" , example = "0")
    private String brandId;
}
