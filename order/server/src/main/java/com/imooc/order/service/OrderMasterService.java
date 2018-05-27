package com.imooc.order.service;

import com.imooc.order.dto.OrderDTO;

public interface OrderMasterService {
  
  /**
   * 创建订单
   * @param orderDTO
   * @return
   */
  OrderDTO create(OrderDTO orderDTO);
  
  
  /**
   * 完结订单
   * @param orderId
   * @return
   */
  OrderDTO finish(String orderId);
  
}
