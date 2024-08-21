package com.musinsa.api.brand.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Schema(description = "브랜드 응답 dto")
public class BrandResponseDto {

    @Schema(description = "브랜드 id", example = "0")
    private int brandId;

    @Schema(description = "브랜드 명", example = "STANDARD")
    private String brandName;

}
