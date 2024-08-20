package com.musinsa.api.search.controller;

import com.musinsa.api.common.dto.RestResponse;
import com.musinsa.api.search.dto.response.GoodsPriceByCategoryResponseDto;
import com.musinsa.api.search.dto.response.GoodsLowestPriceByCategoryResposeDto;
import com.musinsa.api.search.service.CategoryService;
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
@Tag(name = "SearchController", description = "Goods 관련 조회 API Document")
public class SearchController {

    private final CategoryService categoryService;

    public SearchController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/lowest-price-summary")
    @Operation(summary = "category 최저가 조회 api", description = "구현1. 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API")
    public ResponseEntity<RestResponse<Object>> getLowestPriceSummaryByCategory() {

        RestResponse<Object> restResponse = new RestResponse<>();
        Optional<GoodsLowestPriceByCategoryResposeDto> category = categoryService.fetchLowestPriceSummaryByCategory();

        if (category.isPresent()) {
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message(HttpStatus.OK.getReasonPhrase())
                    .data(category)
                    .build();
        }

        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
    }

//    @GetMapping("/lowest-price-summary")
//    @Operation(summary = "brand 최저가 category 상품 조회 api", description = "brand 최저가 category 상품 정보를 JSON으로 리턴")
//    public ResponseEntity<RestResponse<Object>> fetchLowestTotalPriceByBrand() {
//
//        RestResponse<Object> restResponse = new RestResponse<>();
//        Optional<BrandResponseDto> brand = brandService.fetchLowestTotalPriceByBrand();
//
//        if (brand.isPresent()) {
//            restResponse = RestResponse.builder()
//                    .code(HttpStatus.OK.value())
//                    .httpStatus(HttpStatus.OK)
//                    .message(HttpStatus.OK.getReasonPhrase())
//                    .data(brand)
//                    .build();
//        }
//
//        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
//    }

    @GetMapping("/{categoryName}/price-info")
    @Operation(summary = "category 명으로 brand 별 최저가 최고가 조회 api", description = "category 명으로 brand 별 최저가 최고가 정보를 JSON 리턴")
    public ResponseEntity<RestResponse<Object>> getPriceRangeByCategory(
            @PathVariable @Schema(description = "카테고리명", example = "outer") String categoryName
    ) {

        RestResponse<Object> restResponse = new RestResponse<>();
        Optional<GoodsPriceByCategoryResponseDto> category = categoryService.getGoodsPriceRangeByCategory(categoryName);

        if (category.isPresent()) {
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message(HttpStatus.OK.getReasonPhrase())
                    .data(category)
                    .build();
        }

        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
    }

}
