package com.imooc.user.exception;

import com.imooc.user.enums.ResultEnum;

public class UserException extends RuntimeException {
  
  private Integer code;
  
  public UserException(Integer code, String message) {
    super(message);
    this.code = code;
  }
  
  public UserException(ResultEnum resultEnum) {
    this(resultEnum.getCode(), resultEnum.getMessage());
  }
}
