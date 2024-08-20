package com.musinsa.api.common.mapper;

import com.musinsa.api.brand.dto.request.BrandRequestDto;
import com.musinsa.api.goods.model.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BrandMapper {
    List<Goods> fetchAllCategoryGoodsInfo();


    boolean findGoodsFromDB(BrandRequestDto brandRequestDto);

    int saveGoods(BrandRequestDto brandRequestDto);

    int updateGoods(BrandRequestDto brandRequestDto);

    int deleteGoods(BrandRequestDto brandRequestDto);
}
