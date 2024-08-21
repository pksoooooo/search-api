package com.musinsa.api.common.mapper;

import com.musinsa.api.goods.model.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMapper {

    List<Goods> fetchAllGoods();
    List<Goods> fetchBrandGoodsByCategory(String categoryName);

}
