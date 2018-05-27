package com.imooc.order.repository;

import com.imooc.order.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@Component
public class OrderDetailRepositoryTest extends OrderMasterRepositoryTest {

  @Autowired
  private OrderDetailRepository orderDetailRepository;
  
  @Test
  public void save() {
  
    OrderDetail orderDetail = new OrderDetail();
    orderDetail.setDetailId("111111");
    orderDetail.setOrderId("12345699");
    orderDetail.setProductId("1");
    orderDetail.setProductName("上哈");
    orderDetail.setProductPrice(new BigDecimal(44));
    orderDetail.setProductQuantity(2);
    orderDetail.setProductIcon("sss");
    OrderDetail result = orderDetailRepository.save(orderDetail);
    Assert.assertNotNull(result);
  }
  
}
