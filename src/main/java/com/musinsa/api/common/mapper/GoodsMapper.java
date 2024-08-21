package com.musinsa.api.common.mapper;

import com.musinsa.api.goods.dto.request.GoodsSaveRequestDto;
import com.musinsa.api.goods.dto.request.GoodsUpdateRequestDto;
import com.musinsa.api.goods.model.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper {

    List<Goods> fetchAllGoods();
    Goods fetchGoodsById(int goodsId);
    int saveGoods(GoodsSaveRequestDto goodsSaveRequestDto);
    int updateGoods(GoodsUpdateRequestDto goodsUpdateRequestDto);
    int deleteGoods(int goodsId);

}
