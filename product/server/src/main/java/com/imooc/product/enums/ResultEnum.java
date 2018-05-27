package com.imooc.product.enums;

import lombok.Getter;

/**
 * 请求类的枚举
 */
@Getter
public enum ResultEnum {
    
    PARAM_ERROR(1, "参数错误"),
    CART_EMPTY(2, "购物车为空"),
    PRODUCT_NOT_EXIST(3, "商品不存在"),
    PRODUCT_STOCK_ERROR(4, "库存不足"),
    ;
    
    private Integer code;
    
    private String message;
    
    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
