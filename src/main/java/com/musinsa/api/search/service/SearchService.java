package com.musinsa.api.search.service;

import com.musinsa.api.common.convert.CommonConverter;
import com.musinsa.api.common.mapper.SearchMapper;
import com.musinsa.api.common.model.CategoryInfo;
import com.musinsa.api.goods.model.Goods;
import com.musinsa.api.search.dto.response.GoodsLowestPriceByBrandResponseDto;
import com.musinsa.api.search.dto.response.GoodsLowestPriceByCategoryResponseDto;
import com.musinsa.api.search.dto.response.GoodsLowestPriceResponseDto;
import com.musinsa.api.search.dto.response.GoodsPriceRangeResponseDto;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private final SearchMapper searchMapper;
    private final CommonConverter commonConverter;

    private static final String MAX = "max";
    private static final String MIN = "min";

    public SearchService(SearchMapper searchMapper, CommonConverter commonConverter) {
        this.searchMapper = searchMapper;
        this.commonConverter = commonConverter;
    }

    public Optional<GoodsLowestPriceByCategoryResponseDto> fetchLowestPriceSummaryByCategory() {
        return Optional.ofNullable(commonConverter.convertGoodsToResponseDto(fetchLowestPriceGoodsByCategory()));
    }

    private List<Goods> fetchLowestPriceGoodsByCategory() {
        Map<String, Goods> minPriceGoodsByCategory = searchMapper.fetchAllGoods().stream()
                .collect(Collectors.toMap(
                        Goods::getCategoryName,
                        g -> g,
                        (g1, g2) -> g1.getPrice() < g2.getPrice() ? g1 : g2
                ));

        return new ArrayList<>(minPriceGoodsByCategory.values());
    }

    public Optional<GoodsLowestPriceResponseDto> fetchLowestTotalPriceByBrand() {

        List<Goods> goods = searchMapper.fetchAllGoods();

        Map<String, List<Goods>> map = goods.stream()
                .collect(Collectors.groupingBy(
                        Goods::getBrandName
                ));

        Map<String, Integer> totalPrices = map.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream().mapToInt(Goods::getPrice).sum()
                ));

        String minPriceBrand = Collections.min(totalPrices.entrySet(), Map.Entry.comparingByValue()).getKey();
        int amountPrice = Collections.min(totalPrices.entrySet(), Map.Entry.comparingByValue()).getValue();

        GoodsLowestPriceByBrandResponseDto goodsLowestPriceByBrandResponseDto =
                commonConverter.convertGoodsLowestPriceByBrandResponseDto(minPriceBrand, amountPrice, map);

        return Optional.ofNullable(commonConverter.convertGoodsLowestPriceResponseDto(goodsLowestPriceByBrandResponseDto));

    }

    public Optional<GoodsPriceRangeResponseDto> getGoodsPriceRangeByCategory(String categoryName) {
        List<Goods> goodsList = searchMapper.fetchBrandGoodsByCategory(categoryName);

        Map<String, List<Goods>> minMaxPriceCategories = new HashMap<>();

        Optional<Map.Entry<Integer, List<Goods>>> minEntry = goodsList.stream()
                .collect(Collectors.groupingBy(Goods::getPrice))
                .entrySet()
                .stream()
                .min(Map.Entry.comparingByKey());

        Optional<Map.Entry<Integer, List<Goods>>> maxEntry = goodsList.stream()
                .collect(Collectors.groupingBy(Goods::getPrice))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByKey());

        minEntry.ifPresent(entry -> minMaxPriceCategories.put(MIN, entry.getValue()));
        maxEntry.ifPresent(entry -> minMaxPriceCategories.put(MAX, entry.getValue()));

        GoodsPriceRangeResponseDto goodsPriceRangeResponseDto = GoodsPriceRangeResponseDto.builder()
                .categoryName(CategoryInfo.valueOf(categoryName).getCategoryName())
                .maxPrice(commonConverter.convertGoodsToBrandPriceDto(minMaxPriceCategories.get(MAX)))
                .minPrice(commonConverter.convertGoodsToBrandPriceDto(minMaxPriceCategories.get(MIN))).build();

        return Optional.ofNullable(goodsPriceRangeResponseDto);
    }
}
