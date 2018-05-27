package com.imooc.order.repository;

import com.imooc.order.OrderApplicationTests;
import com.imooc.order.dataobject.OrderMaster;
import com.imooc.order.enums.OrderStatusEnum;
import com.imooc.order.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderMasterRepositoryTest extends OrderApplicationTests {

  @Autowired
  private OrderMasterRepository orderMasterRepository;
  
  @Test
  public void save() {
  
    OrderMaster orderMaster = new OrderMaster();
    orderMaster.setOrderId("12345699");
    orderMaster.setBuyerName("ss");
    orderMaster.setBuyerPhone("124444");
    orderMaster.setBuyerAddress("上哈");
    orderMaster.setBuyerOpenid("ssss");
    orderMaster.setOrderAmount(new BigDecimal(33));
    orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
    orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
    OrderMaster result = orderMasterRepository.save(orderMaster);
    Assert.assertNotNull(result);
  }
  
}
