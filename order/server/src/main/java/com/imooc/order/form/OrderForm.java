package com.imooc.order.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderForm {
  
  /**
   * 买家姓名
   */
  @NotEmpty(message = "姓名必填")
  private  String name;
  
  /**
   * 买家电话
   */
  @NotEmpty(message = "电话必填")
  private String phone;
  
  /**
   * 地址
   */
  @NotEmpty(message = "地址必填")
  private String address;
  
  /**
   * 买家openid
   */
  @NotEmpty(message = "openid必填")
  private String openid;
  
  /**
   * g购物车
   */
  @NotEmpty(message = "购物车不能为空")
  private String items;
  
}
