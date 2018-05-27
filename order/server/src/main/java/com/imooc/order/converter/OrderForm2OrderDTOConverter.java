package com.imooc.order.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imooc.order.dataobject.OrderDetail;
import com.imooc.order.dto.OrderDTO;
import com.imooc.order.enums.ResultEnum;
import com.imooc.order.exception.OrderException;
import com.imooc.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单表单转 OrderDTO
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

  public static OrderDTO convert(OrderForm orderForm) {
  
    OrderDTO orderDTO = new OrderDTO();
    orderDTO.setBuyerName(orderForm.getName());
    orderDTO.setBuyerPhone(orderForm.getPhone());
    orderDTO.setBuyerAddress(orderForm.getAddress());
    orderDTO.setBuyerOpenid(orderForm.getOpenid());
  
    // items
    List<OrderDetail> orderDetails = new ArrayList<>();
  
    Gson gson = new Gson();
    try {
      orderDetails = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>(){}.getType());
    }catch (Exception e) {
      log.error("[JSON转换] 错误, String = {}", orderForm.getItems());
      throw new OrderException(ResultEnum.PARAM_ERROR);
      
    }
    orderDTO.setOrderDetailList(orderDetails);
    
    return orderDTO;
  }

}
