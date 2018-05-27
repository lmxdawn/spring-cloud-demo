package com.imooc.product.VO;

import lombok.Data;

/**
 * 请求的 value Object
 */
@Data
public class ResultVo<T> {
  
  /**
   * 错误码
   */
  private Integer code;
  
  /**
   * 提示信息
   */
  private String msg;
  
  /**
   * 具体内容
   */
  private T data;
  
}
