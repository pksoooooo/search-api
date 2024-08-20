package com.musinsa.api.search.dto.response;

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
public class GoodsLowestPriceByCategoryResposeDto {

    private List<GoodsDto> categoryList;
    private int totalAmount;

}
