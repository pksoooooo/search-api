package com.musinsa.api.brand.controller;

import com.musinsa.api.brand.dto.request.BrandRequestDto;
import com.musinsa.api.brand.dto.response.BrandResponseDto;
import com.musinsa.api.brand.service.BrandService;
import com.musinsa.api.common.dto.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/brand")
@Tag(name = "BrandController", description = "Brand 관련 조회 API Document")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping("/goods")
    @Operation(summary = " 브랜드 및 상품 추가 api", description = "상품 등록 성공 유무 JSON 리턴")
    public ResponseEntity<RestResponse<Object>> saveGoods(@Parameter @RequestBody BrandRequestDto brandRequestDto) {
        RestResponse<Object> restResponse = new RestResponse<>();

        boolean result = false;

        if (brandService.isGoodsRegistrable(brandRequestDto)) {
            System.out.println("상품등록 가능");
            result = brandService.saveGoods(brandRequestDto);
        }

        if (result) {
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message(HttpStatus.OK.getReasonPhrase())
                    .build();
        }

        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
    }

    @PutMapping("/goods")
    @Operation(summary = " 브랜드 및 상품 수정 api", description = "상품 수정 성공 유무 JSON 리턴")
    public ResponseEntity<RestResponse<Object>> updateGoods(@Parameter @RequestBody BrandRequestDto brandRequestDto) {
        RestResponse<Object> restResponse = new RestResponse<>();

        boolean result = false;

        if (!brandService.isGoodsRegistrable(brandRequestDto)) {
            System.out.println("상품수정 가능");
            result = brandService.updateGoods(brandRequestDto);
        }

        if (result) {
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message(HttpStatus.OK.getReasonPhrase())
                    .build();
        }

        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
    }

    @DeleteMapping("/{brandName}/{categoryName}/goods")
    @Operation(summary = " 브랜드 및 상품 수정 api", description = "상품 수정 성공 유무 JSON 리턴")
    public ResponseEntity<RestResponse<Object>> deleteGoods(
            @PathVariable @Schema(description = "브랜드명", example = "A") String brandName,
            @PathVariable @Schema(description = "카테고리명", example = "outer") String categoryName
    ) {
        RestResponse<Object> restResponse = new RestResponse<>();

        boolean result = false;

        BrandRequestDto brandRequestDto = BrandRequestDto.builder()
                .brandName(brandName)
                .categoryName(categoryName)
                .build();

        if (!brandService.isGoodsRegistrable(brandRequestDto)) {
            System.out.println("상품삭제 가능");
            result = brandService.deleteGoods(brandRequestDto);
        }

        if (result) {
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message(HttpStatus.OK.getReasonPhrase())
                    .build();
        }

        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
    }

}
