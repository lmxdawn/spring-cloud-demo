package com.imooc.order.exception;

import com.imooc.order.enums.ResultEnum;

/**
 * 错误类
 */
public class OrderException extends RuntimeException {
  
  private Integer code;
  
  public OrderException(Integer code, String message) {
    super(message);
    this.code = code;
  }
  
  public OrderException(ResultEnum resultEnum) {
    this(resultEnum.getCode(), resultEnum.getMessage());
  }
}
