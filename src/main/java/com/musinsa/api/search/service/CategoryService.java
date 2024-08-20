package com.musinsa.api.search.service;

import com.musinsa.api.common.mapper.CategoryMapper;
import com.musinsa.api.goods.dto.GoodsDto;
import com.musinsa.api.goods.model.Goods;
import com.musinsa.api.search.dto.response.GoodsLowestPriceByCategoryResposeDto;
import com.musinsa.api.search.dto.response.GoodsPriceByCategoryResponseDto;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryMapper categoryMapper;

    private static final String MAX = "max";
    private static final String MIN = "min";

    public CategoryService(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public Optional<GoodsLowestPriceByCategoryResposeDto> fetchLowestPriceSummaryByCategory() {

        List<GoodsDto> goodsDtoList = convertGoodsToDto(categoryMapper.fetchLowestPriceSummaryByCategory());

        return Optional.ofNullable(convertCategoryToResponseDto(goodsDtoList));
        //        return Optional.ofNullable(null);
    }

    private GoodsLowestPriceByCategoryResposeDto convertCategoryToResponseDto(List<GoodsDto> categories) {

        return GoodsLowestPriceByCategoryResposeDto.builder()
                .categoryList(categories)
                .totalAmount(categories.stream()
                        .mapToInt(GoodsDto::getPrice)
                        .sum()).build();
    }

    private List<GoodsDto> convertGoodsToDto(List<Goods> categories) {

        List<GoodsDto> goodsDtoList = new ArrayList<>();

        //category별로 최저가 가져오기
        Map<String, Goods> minPriceGoodsByCategory = categories.stream()
                .collect(Collectors.toMap(
                        Goods::getCategoryName,
                        g -> g,
                        (g1, g2) -> g1.getPrice() < g2.getPrice() ? g1 : g2
                ));

        //Goods -> GoodsDto convert
        minPriceGoodsByCategory.values().forEach(a -> goodsDtoList.add(GoodsDto.builder()
                .categoryName(a.getCategoryName())
                .brandName(a.getBrandName())
                .price(a.getPrice())
                .build())
        );

        return goodsDtoList;

    }

    public Optional<GoodsPriceByCategoryResponseDto> getGoodsPriceRangeByCategory(String categoryName) {
        List<Goods> categories = categoryMapper.fetchGoodsPriceRangeForCategory(categoryName);

        Map<String, List<Goods>> minMaxPriceCategories = categories.stream()
                .collect(Collectors.groupingBy(Goods::getPrice))
                .entrySet().stream()
                .collect(Collectors.teeing(
                        Collectors.minBy(Comparator.comparingInt(Map.Entry::getKey)),
                        Collectors.maxBy(Comparator.comparingInt(Map.Entry::getKey)),
                        (minEntry, maxEntry) -> Map.of(
                                MAX, maxEntry.map(Map.Entry::getValue).orElseThrow(() -> new IllegalStateException("List is empty")),
                                MIN, minEntry.map(Map.Entry::getValue).orElseThrow(() -> new IllegalStateException("List is empty"))
                        )
                ));

        GoodsPriceByCategoryResponseDto goodsPriceByCategoryResponseDto = GoodsPriceByCategoryResponseDto.builder()
                .categoryName(categoryName)
                .maxPrice(convertGoodsToDto(minMaxPriceCategories.get(MAX)))
                .minPrice(convertGoodsToDto(minMaxPriceCategories.get(MIN))).build();

        return Optional.ofNullable(goodsPriceByCategoryResponseDto);
    }
}
