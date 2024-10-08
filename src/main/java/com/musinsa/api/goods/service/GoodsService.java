package com.musinsa.api.goods.service;

import com.musinsa.api.brand.service.BrandService;
import com.musinsa.api.common.convert.CommonConverter;
import com.musinsa.api.common.exception.NoSuchDataException;
import com.musinsa.api.common.mapper.GoodsMapper;
import com.musinsa.api.common.model.CategoryInfo;
import com.musinsa.api.goods.dto.request.GoodsSaveRequestDto;
import com.musinsa.api.goods.dto.request.GoodsUpdateRequestDto;
import com.musinsa.api.goods.dto.response.GoodsResponseDto;
import com.musinsa.api.goods.model.Goods;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class GoodsService {

    private final GoodsMapper goodsMapper;
    private final BrandService brandService;
    private final CommonConverter commonConverter;

    public GoodsService(GoodsMapper goodsMapper, CommonConverter commonConverter, BrandService brandService) {
        this.goodsMapper = goodsMapper;
        this.brandService = brandService;
        this.commonConverter = commonConverter;
    }

    public boolean saveGoods(GoodsSaveRequestDto goodsSaveRequestDto) {

        checkCategoryValueFromEnum(goodsSaveRequestDto.getCategoryName());

        if (isNotValidBrandId(goodsSaveRequestDto.getBrandId())) {
            return false;
        }

        return goodsMapper.saveGoods(goodsSaveRequestDto) > 0;
    }

    private boolean isNotValidBrandId(int brandId) {
        return brandId > 0 && brandService.getBrandById(brandId) == null;
    }

    public boolean updateGoods(GoodsUpdateRequestDto goodsUpdateRequestDto) {

        try {
            getGoodsById(goodsUpdateRequestDto.getGoodsId());
        } catch (NoSuchDataException e) {
            throw new NoSuchDataException("수정하려는 상품이 없습니다.");
        }

        checkCategoryValueFromEnum(goodsUpdateRequestDto.getCategoryName());

        if (isNotValidBrandId(goodsUpdateRequestDto.getBrandId())) {
            return false;
        }

        return goodsMapper.updateGoods(goodsUpdateRequestDto) > 0;
    }

    public List<GoodsResponseDto> getAllGoods() {
        return commonConverter.convertGoodsListToGoodsListResponseDto(goodsMapper.fetchAllGoods());
    }

    public GoodsResponseDto getGoodsById(int goodsId) {
        Goods goods = Optional.ofNullable(goodsMapper.fetchGoodsById(goodsId))
                .orElseThrow(() -> new NoSuchDataException("조회하려는 데이터가 없습니다."));

        return commonConverter.convertGoodsToGoodsResponseDto(goods);
    }

    public boolean deleteGoods(int goodsId) {

        try {
            getGoodsById(goodsId);
        } catch (NoSuchDataException e) {
            throw new NoSuchDataException("삭제하려는 데이터가 없습니다.");
        }

        return goodsMapper.deleteGoods(goodsId) > 0;
    }

    private void checkCategoryValueFromEnum(String categoryName) {

        if (StringUtils.isNotEmpty(categoryName)) {

            Optional<CategoryInfo> categoryInfo = Arrays.stream(CategoryInfo.values())
                    .filter(category -> category.name().equalsIgnoreCase(categoryName))
                    .findFirst();

            if (categoryInfo.isEmpty()) {
                throw new NoSuchDataException("카테고리가 등록 되어 있지 않습니다.");
            }

        }
    }
}
