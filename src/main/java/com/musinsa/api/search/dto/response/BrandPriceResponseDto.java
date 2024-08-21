package com.musinsa.api.search.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "브랜드 가격 응답 dto")
public class BrandPriceResponseDto {

    @JsonProperty("브랜드")
    @Schema(description = "브랜드 명" , example = "A")
    private String brandName;
    @JsonProperty("가격")
    @Schema(description = "가격" , example = "0")
    private String price;

}
