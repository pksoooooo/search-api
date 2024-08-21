package com.musinsa.api.common.model;

import lombok.Getter;

@Getter
public enum CategoryInfo {
    TOP("상의"),
    OUTER("아우터"),
    PANTS("바지"),
    SNEAKERS("스니커즈"),
    BAG("가방"),
    HAT("모자"),
    SOCKS("양말"),
    ACCESSORY("악세서리");

    private final String categoryName;

    CategoryInfo(String categoryName) {
        this.categoryName = categoryName;
    }

}
