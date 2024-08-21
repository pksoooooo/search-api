package com.musinsa.api.common.util;

import java.text.NumberFormat;

public class CommonUtil {
    public static String getFormattedPrice(int price) {
        return NumberFormat.getInstance().format(price);
        }
}
