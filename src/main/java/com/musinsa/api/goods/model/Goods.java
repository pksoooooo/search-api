package com.musinsa.api.goods.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Goods {

    private int brandId;
    private int goodsId;
    private String brandName;
    private String categoryName;
    private int price;

}
