package com.musinsa.api.brand.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Schema(description = "브랜드 등록 요청 dto")
public class BrandSaveRequestDto {
    @Schema(description = "브랜드 명" , example = "STANDARD")
    private String brandName;
}
