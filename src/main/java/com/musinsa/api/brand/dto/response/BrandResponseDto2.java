package com.musinsa.api.brand.dto.response;

import com.musinsa.api.goods.dto.GoodsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BrandResponseDto2 {

    private String brandName;
    private List<GoodsDto> categoryInfo;
    private int totalAmount;

}
