package com.musinsa.api.goods.controller;

import com.musinsa.api.common.dto.RestResponse;
import com.musinsa.api.goods.dto.request.GoodsSaveRequestDto;
import com.musinsa.api.goods.dto.request.GoodsUpdateRequestDto;
import com.musinsa.api.goods.dto.response.GoodsResponseDto;
import com.musinsa.api.goods.service.GoodsService;
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
@RequestMapping("/api/goods")
@Tag(name = "GoodsController", description = "상품 관련 조회 API Document")
public class GoodsController {

    private final GoodsService goodsService;

    public GoodsController(GoodsService brandService) {
        this.goodsService = brandService;
    }

    @GetMapping
    @Operation(summary = "모든 상품 조회 api", description = "모든 상품 조회 값 JSON 리턴")
    public ResponseEntity<RestResponse<Object>> getAllGoods() {
        RestResponse<Object> restResponse = new RestResponse<>();

        List<GoodsResponseDto> brandResponseDtoList = goodsService.getAllGoods();

        if (CollectionUtils.isNotEmpty(brandResponseDtoList)) {
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .data(brandResponseDtoList)
                    .build();
        }

        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
    }

    @GetMapping("/{id}")
    @Operation(summary = "상품 id 조회 api", description = "상품 id 조회 값 JSON 리턴")
    public ResponseEntity<RestResponse<Object>> getGoodsById(@PathVariable @Schema(description = "상품 id", example = "0") int id) {
        RestResponse<Object> restResponse = new RestResponse<>();

        Optional<GoodsResponseDto> goodsResponseDto = Optional.ofNullable(goodsService.getGoodsById(id));

        if (goodsResponseDto.isPresent()) {
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .data(goodsResponseDto)
                    .build();
        }

        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
    }

    @PostMapping
    @Operation(summary = "상품 등록 api", description = "상품 등록 성공 유무 JSON 리턴")
    public ResponseEntity<RestResponse<Object>> saveGoods(@Parameter @RequestBody GoodsSaveRequestDto goodsSaveRequestDto) {
        RestResponse<Object> restResponse = new RestResponse<>();

        boolean result = goodsService.saveGoods(goodsSaveRequestDto);

        if (result) {
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("상품 등록 완료")
                    .build();
        }

        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
    }

    @PutMapping
    @Operation(summary = "상품 수정 api", description = "상품 수정 성공 유무 JSON 리턴")
    public ResponseEntity<RestResponse<Object>> updateGoods(@Parameter @RequestBody GoodsUpdateRequestDto goodsUpdateRequestDto) {
        RestResponse<Object> restResponse = new RestResponse<>();

        boolean result = goodsService.updateGoods(goodsUpdateRequestDto);

        if (result) {
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("상품 수정 완료")
                    .build();
        }

        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "상품 삭제 api", description = "상품 삭제 성공 유무 JSON 리턴")
    public ResponseEntity<RestResponse<Object>> deleteGoods(
            @PathVariable @Schema(description = "상품 id", example = "0") int id
    ) {
        RestResponse<Object> restResponse = new RestResponse<>();

        boolean result = goodsService.deleteGoods(id);

        if (result) {
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("상품 삭제 완료")
                    .build();
        }

        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
    }

}
