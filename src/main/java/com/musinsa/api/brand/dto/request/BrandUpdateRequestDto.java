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
@Schema(description = "브랜드 수정 요청 dto")
public class BrandUpdateRequestDto {
    @Schema(description = "브랜드 id", example = "0")
    private int brandId;

    @Schema(description = "브랜드 명", example = "STANDARD")
    private String brandName;
}
