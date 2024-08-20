package com.musinsa.api.common.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Schema(description = "브랜드 및 상품을 추가 요청 DTO")
public class GoodsRequestDto {
    @Schema(description = "brand", example = "AAAA")
    private String brand;
    @Schema(description = "top", example = "0")
    private String top;
    @Schema(description = "outer", example = "0")
    private String outer;
    @Schema(description = "pants", example = "0")
    private String pants;
    @Schema(description = "sneakers", example = "0")
    private String sneakers;
    @Schema(description = "bag", example = "0")
    private String bag;
    @Schema(description = "hat", example = "0")
    private String hat;
    @Schema(description = "socks", example = "0")
    private String socks;
    @Schema(description = "accessory", example = "0")
    private String accessory;

}
