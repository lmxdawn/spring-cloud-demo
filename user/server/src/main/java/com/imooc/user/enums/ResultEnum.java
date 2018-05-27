package com.imooc.user.enums;

import lombok.Getter;

/**
 * 请求类的枚举
 */
@Getter
public enum ResultEnum {

  PARAM_ERROR(1, "参数错误"),
  LOGIN_FAIL(2, "登录失败"),
  ROLE_ERROR(3, "角色错误"),
  ;
  
  private Integer code;
  
  private String message;
  
  ResultEnum(Integer code, String message) {
    this.code = code;
    this.message = message;
  }
}
