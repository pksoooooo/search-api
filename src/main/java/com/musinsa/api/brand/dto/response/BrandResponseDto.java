package com.musinsa.api.brand.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BrandResponseDto {
    private BrandResponseDto2 minPrice;
}
