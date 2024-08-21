package com.musinsa.api.brand.controller;

import com.musinsa.api.brand.dto.request.BrandSaveRequestDto;
import com.musinsa.api.brand.dto.request.BrandUpdateRequestDto;
import com.musinsa.api.brand.dto.response.BrandResponseDto;
import com.musinsa.api.brand.service.BrandService;
import com.musinsa.api.common.dto.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/brand")
@Tag(name = "BrandController", description = "Brand 관련 조회 API Document")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    @Operation(summary = "모든 브랜드 조회 api", description = "모든 브랜드 조회 값 JSON 리턴")
    public ResponseEntity<RestResponse<Object>> getAllBrand() {
        RestResponse<Object> restResponse = new RestResponse<>();

        List<BrandResponseDto> brandResponseDtoList = brandService.getAllBrand();

        if (CollectionUtils.isNotEmpty(brandResponseDtoList)) {
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message(HttpStatus.OK.getReasonPhrase())
                    .data(brandResponseDtoList)
                    .build();
        }

        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "브랜드 id 조회 api", description = "브랜드 id 조회 값 JSON 리턴")
    public ResponseEntity<RestResponse<Object>> getBrandById(@PathVariable @Schema(description = "브랜드 id", example = "1") int id) {
        RestResponse<Object> restResponse = new RestResponse<>();

        Optional<BrandResponseDto> brandResponseDto = Optional.ofNullable(brandService.getBrandById(id));

        if (brandResponseDto.isPresent()) {
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .data(brandResponseDto)
                    .build();
        }

        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "브랜드 명 조회 api", description = "브랜드 명 조회 값 JSON 리턴")
    public ResponseEntity<RestResponse<Object>> getBrandByName(@PathVariable @Schema(description = "브랜드 명", example = "A") String name) {
        RestResponse<Object> restResponse = new RestResponse<>();

        Optional<BrandResponseDto> brandResponseDto = Optional.ofNullable(brandService.getBrandByName(name));

        if (brandResponseDto.isPresent()) {
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .data(brandResponseDto)
                    .build();
        }

        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
    }

    @PostMapping
    @Operation(summary = "브랜드 등록 api", description = "브랜드 등록 성공 유무 JSON 리턴")
    public ResponseEntity<RestResponse<Object>> saveBrand(@Parameter @RequestBody BrandSaveRequestDto brandSaveRequestDto) {
        RestResponse<Object> restResponse = new RestResponse<>();

        boolean result = brandService.saveBrand(brandSaveRequestDto);

        if (result) {
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("브랜드 등록 완료")
                    .build();
        }

        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
    }

    @PutMapping
    @Operation(summary = "브랜드 수정 api", description = "브랜드 수정 성공 유무 JSON 리턴")
    public ResponseEntity<RestResponse<Object>> updateGoods(@Parameter @RequestBody BrandUpdateRequestDto brandUpdateRequestDto) {
        RestResponse<Object> restResponse = new RestResponse<>();

        boolean result = brandService.updateBrand(brandUpdateRequestDto);

        if (result) {
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("브랜드 수정 완료")
                    .build();
        }

        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "브랜드 삭제 api", description = "브랜드 삭제 성공 유무 JSON 리턴")
    public ResponseEntity<RestResponse<Object>> deleteGoods(
            @PathVariable @Schema(description = "brand id", example = "0") int id
    ) {
        RestResponse<Object> restResponse = new RestResponse<>();

        boolean result = brandService.deleteBrand(id);

        if (result) {
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("브랜드 삭제 완료")
                    .build();
        }

        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
    }

}
