package com.musinsa.api.common.util;

import java.text.NumberFormat;

public class CommonUtil {

    private CommonUtil() {
        throw new IllegalStateException("CommonUtil class");
    }

    public static String getFormattedPrice(int price) {
        return NumberFormat.getInstance().format(price);
    }

}
