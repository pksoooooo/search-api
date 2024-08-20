package com.musinsa.api.brand.service;

import com.musinsa.api.brand.dto.request.BrandRequestDto;
import com.musinsa.api.brand.dto.response.BrandResponseDto2;
import com.musinsa.api.brand.dto.response.BrandResponseDto;
import com.musinsa.api.goods.dto.GoodsDto;
import com.musinsa.api.goods.model.Goods;
import com.musinsa.api.search.model.CategoryInfo;
import com.musinsa.api.common.mapper.BrandMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BrandService {

    private final BrandMapper brandMapper;

    public BrandService(BrandMapper brandMapper) {
        this.brandMapper = brandMapper;
    }

    public Optional<BrandResponseDto> fetchLowestTotalPriceByBrand() {

        List<Goods> goods = brandMapper.fetchAllCategoryGoodsInfo();

        //List를 brand명으로 그룹화 해서 map으로 변환
        Map<String, List<Goods>> map = goods.stream()
                .collect(Collectors.groupingBy(
                        Goods::getBrandName
                ));

        //map value에서 price sum 구하기
        Map<String, Integer> totalPrices = map.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream().mapToInt(Goods::getPrice).sum()
                ));

        //최소 브랜드와 최소 브랜드의 카테고리 총 합 구하기
        String minPriceBrand = Collections.min(totalPrices.entrySet(), Map.Entry.comparingByValue()).getKey();
        int amountPrice = Collections.min(totalPrices.entrySet(), Map.Entry.comparingByValue()).getValue();

        //dto 담기
        BrandResponseDto2 brandResponseDto2 = BrandResponseDto2.builder()
                .brandName(minPriceBrand)
                .totalAmount(amountPrice)
                .categoryInfo(convertCategoryToDto(map.get(minPriceBrand)))
                .build();

        //최종 response에 담기
        BrandResponseDto brandResponseDto = BrandResponseDto.builder().minPrice(brandResponseDto2).build();

        return Optional.ofNullable(brandResponseDto);

    }

    private List<GoodsDto> convertCategoryToDto(List<Goods> goodsList) {

        List<GoodsDto> goodsDtoList = new ArrayList<>();

        goodsList.forEach(a -> goodsDtoList.add(GoodsDto.builder()
                .categoryName(CategoryInfo.valueOf(a.getCategoryName().toUpperCase()).getCategoryName())
                .brandName(a.getBrandName())
                .price(a.getPrice())
                .build())
        );

        return goodsDtoList;

    }

    public boolean isGoodsRegistrable(BrandRequestDto brandRequestDto) {
        return !brandMapper.findGoodsFromDB(brandRequestDto);
    }

    public boolean saveGoods(BrandRequestDto brandRequestDto) {
        return brandMapper.saveGoods(brandRequestDto) > 0;
    }

    public boolean updateGoods(BrandRequestDto brandRequestDto) {
        return brandMapper.updateGoods(brandRequestDto) > 0;
    }

    public boolean deleteGoods(BrandRequestDto brandRequestDto) {
        return brandMapper.deleteGoods(brandRequestDto) > 0;
    }
}
