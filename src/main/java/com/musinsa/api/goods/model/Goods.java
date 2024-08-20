package com.musinsa.api.goods.model;

import lombok.*;

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
