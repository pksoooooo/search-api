package com.musinsa.api.common.convert;

import com.musinsa.api.brand.dto.response.BrandResponseDto;
import com.musinsa.api.brand.model.Brand;
import com.musinsa.api.common.model.CategoryInfo;
import com.musinsa.api.goods.dto.response.GoodsResponseDto;
import com.musinsa.api.goods.model.Goods;
import com.musinsa.api.search.dto.response.BrandPriceResponseDto;
import com.musinsa.api.search.dto.response.CategoryPriceResponseDto;
import com.musinsa.api.search.dto.response.GoodsLowestPriceByCategoryResponseDto;
import com.musinsa.api.search.dto.response.GoodsPriceResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.musinsa.api.common.util.CommonUtil.getFormattedPrice;

@Component
public class CommonConverter {

    public GoodsLowestPriceByCategoryResponseDto convertGoodsToResponseDto(List<Goods> goodsList) {

        List<GoodsPriceResponseDto> goodsPriceResponseDtoList = convertGoodsToGoodsPriceDto(goodsList);

        int totalAmount = goodsPriceResponseDtoList.stream()
                .mapToInt(dto -> Integer.parseInt(dto.getPrice().replace(",", "")))
                .sum();

        return GoodsLowestPriceByCategoryResponseDto.builder()
                .categoryList(goodsPriceResponseDtoList)
                .totalAmount(getFormattedPrice(totalAmount))
                .build();
    }

    public List<GoodsPriceResponseDto> convertGoodsToGoodsPriceDto(List<Goods> goodsList) {

        List<GoodsPriceResponseDto> categoryPriceResponseDtoList = new ArrayList<>();

        goodsList.forEach(goods -> categoryPriceResponseDtoList.add(GoodsPriceResponseDto.builder()
                .categoryName(CategoryInfo.valueOf(goods.getCategoryName()).getCategoryName())
                .brandName(goods.getBrandName())
                .price(getFormattedPrice(goods.getPrice()))
                .build())
        );

        return categoryPriceResponseDtoList;

    }

    public List<CategoryPriceResponseDto> convertGoodsToCategoryPriceDto(List<Goods> goodsList) {

        List<CategoryPriceResponseDto> categoryPriceResponseDtoList = new ArrayList<>();

        goodsList.forEach(goods -> categoryPriceResponseDtoList.add(CategoryPriceResponseDto.builder()
                .categoryName(CategoryInfo.valueOf(goods.getCategoryName()).getCategoryName())
                .price(getFormattedPrice(goods.getPrice()))
                .build())
        );

        return categoryPriceResponseDtoList;

    }

    public List<BrandPriceResponseDto> convertGoodsToBrandPriceDto(List<Goods> goodsList) {

        List<BrandPriceResponseDto> brandPriceResponseDtoList = new ArrayList<>();

        goodsList.forEach(goods -> brandPriceResponseDtoList.add(BrandPriceResponseDto.builder()
                .brandName(goods.getBrandName())
                .price(getFormattedPrice(goods.getPrice()))
                .build())
        );

        return brandPriceResponseDtoList;

    }

    public List<BrandResponseDto> convertBrandListToBrandListResponseDto(List<Brand> brandList) {

        List<BrandResponseDto> brandResponseDtoList = new ArrayList<>();

        brandList.forEach(brand ->
                brandResponseDtoList.add(
                        convertBrandToBrandResponseDto(brand)
                )
        );

        return brandResponseDtoList;

    }

    public BrandResponseDto convertBrandToBrandResponseDto(Brand brand) {
        return BrandResponseDto.builder()
                .brandId(brand.getBrandId())
                .brandName(brand.getBrandName())
                .build();

    }

    public GoodsResponseDto convertGoodsToGoodsResponseDto(Goods goods) {

        BrandResponseDto brandResponseDto = BrandResponseDto.builder().brandId(goods.getBrandId()).brandName(goods.getBrandName()).build();

        return GoodsResponseDto.builder()
                .goodsId(goods.getGoodsId())
                .categoryName(CategoryInfo.valueOf(goods.getCategoryName()).getCategoryName())
                .price(getFormattedPrice(goods.getPrice()))
                .brandInfo(brandResponseDto)
                .build();

    }

    public List<GoodsResponseDto> convertGoodsListToGoodsListResponseDto(List<Goods> goodsList) {

        List<GoodsResponseDto> goodsResponseDto = new ArrayList<>();

        goodsList.forEach(goods ->
                goodsResponseDto.add(
                        convertGoodsToGoodsResponseDto(goods)
                )
        );

        return goodsResponseDto;

    }
}
