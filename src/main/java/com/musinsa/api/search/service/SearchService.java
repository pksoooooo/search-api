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

import static com.musinsa.api.common.util.CommonUtil.getFormattedPrice;

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

            GoodsLowestPriceByBrandResponseDto goodsLowestPriceByBrandResponseDto = GoodsLowestPriceByBrandResponseDto.builder()
                    .brandName(minPriceBrand)
                    .totalAmount(getFormattedPrice(amountPrice))
                    .categoryInfo(commonConverter.convertGoodsToCategoryPriceDto(map.get(minPriceBrand)))
                    .build();

            GoodsLowestPriceResponseDto goodsLowestPriceResponseDto = GoodsLowestPriceResponseDto.builder().minPrice(goodsLowestPriceByBrandResponseDto).build();

            return Optional.ofNullable(goodsLowestPriceResponseDto);

        }

    public Optional<GoodsPriceRangeResponseDto> getGoodsPriceRangeByCategory(String categoryName) {
        List<Goods> goodsList = searchMapper.fetchBrandGoodsByCategory(categoryName);

        Map<String, List<Goods>> minMaxPriceCategories = goodsList.stream()
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

        GoodsPriceRangeResponseDto goodsPriceRangeResponseDto = GoodsPriceRangeResponseDto.builder()
                .categoryName(CategoryInfo.valueOf(categoryName).getCategoryName())
                .maxPrice(commonConverter.convertGoodsToBrandPriceDto(minMaxPriceCategories.get(MAX)))
                .minPrice(commonConverter.convertGoodsToBrandPriceDto(minMaxPriceCategories.get(MIN))).build();

        return Optional.ofNullable(goodsPriceRangeResponseDto);
    }
}
