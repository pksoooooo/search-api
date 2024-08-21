package com.musinsa.api.search.controller;

import com.musinsa.api.common.dto.RestResponse;
import com.musinsa.api.search.dto.response.GoodsLowestPriceByCategoryResponseDto;
import com.musinsa.api.search.dto.response.GoodsLowestPriceResponseDto;
import com.musinsa.api.search.dto.response.GoodsPriceRangeResponseDto;
import com.musinsa.api.search.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController()
@RequestMapping("/api/search")
@Tag(name = "SearchController", description = "Search 관련 조회 API Document")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/category/lowest-price")
    @Operation(summary = "카테고리 별 최저 가격 브랜드와 상품 가격, 총액을 조회하는 API",
            description = "카테고리 별 최저 가격 브랜드와 상품 가격을 JSON으로 리턴")
    public ResponseEntity<RestResponse<Object>> getLowestPriceSummaryByCategory() {

        RestResponse<Object> restResponse = new RestResponse<>();
        Optional<GoodsLowestPriceByCategoryResponseDto> category = searchService.fetchLowestPriceSummaryByCategory();

        if (category.isPresent()) {
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .data(category)
                    .build();
        }

        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
    }

    @GetMapping("/brand/lowest-price")
    @Operation(summary = "브랜드기준 카테고리의 상품가격, 총액을 조회하는 API",
            description = "단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격을 JSON으로 리턴")
    public ResponseEntity<RestResponse<Object>> fetchLowestTotalPriceByBrand() {

        RestResponse<Object> restResponse = new RestResponse<>();
        Optional<GoodsLowestPriceResponseDto> brand = searchService.fetchLowestTotalPriceByBrand();

        if (brand.isPresent()) {
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .data(brand)
                    .build();
        }

        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
    }

    @GetMapping("/category/price-range/{categoryName}")
    @Operation(summary = "category 명으로 brand 별 최저가 최고가 조회 api", description = "category 명으로 brand 별 최저가 최고가 정보를 JSON 리턴")
    public ResponseEntity<RestResponse<Object>> getPriceRangeByCategory(
            @PathVariable @Schema(description = "카테고리", example = "TOP") String categoryName
    ) {

        RestResponse<Object> restResponse = new RestResponse<>();
        Optional<GoodsPriceRangeResponseDto> category = searchService.getGoodsPriceRangeByCategory(categoryName.toUpperCase());

        if (category.isPresent()) {
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .data(category)
                    .build();
        }

        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
    }

}
