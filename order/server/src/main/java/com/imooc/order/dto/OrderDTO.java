package com.imooc.order.dto;

import com.imooc.order.dataobject.OrderDetail;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

/**
 * 订单数据传输层
 */
@Data
public class OrderDTO {
  
  /**
   * 订单id
   */
  private String orderId;
  
  /**
   * 买家名称
   */
  private String buyerName;
  
  /**
   * 买家电话
   */
  private String buyerPhone;
  
  /**
   * 买家地址
   */
  private String buyerAddress;
  
  /**
   * 买家openid
   */
  private String buyerOpenid;
  
  /**
   * 订单金额
   */
  private BigDecimal orderAmount;
  
  /**
   * 订单状态
   */
  private Integer orderStatus;
  
  /**
   * 支付状态
   */
  private Integer payStatus;
  
  /**
   * 订单商品详细
   */
  List<OrderDetail> orderDetailList;
  

}
