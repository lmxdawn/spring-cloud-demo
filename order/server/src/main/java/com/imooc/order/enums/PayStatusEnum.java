package com.imooc.order.enums;

import lombok.Getter;

/**
 * 支付状态
 */
@Getter
public enum PayStatusEnum {
  WAIT(0, "未支付"),
  SUCCESS(1, "完结")
  ;
  
  private Integer code;
  
  private String message;
  
  PayStatusEnum(Integer code, String message) {
    this.code = code;
    this.message = message;
  }
}
