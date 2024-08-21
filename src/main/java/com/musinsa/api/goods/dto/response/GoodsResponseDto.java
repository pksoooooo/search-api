package com.musinsa.api.goods.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musinsa.api.brand.dto.response.BrandResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Schema(description = "Goods 응답 DTO")
public class GoodsResponseDto {

    private int goodsId;
    private String categoryName;
    private String price;

    @JsonProperty("brandInfo")
    private BrandResponseDto brandInfo;

}
