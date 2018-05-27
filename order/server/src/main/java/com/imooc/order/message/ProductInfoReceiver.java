package com.imooc.order.message;

import com.fasterxml.jackson.core.type.TypeReference;
import com.imooc.order.utils.JsonUtil;
import com.imooc.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 接收商品的消息
 */
@Component
@Slf4j
public class ProductInfoReceiver {
  
  private static final String PRODUCT_STOCK_TEMPLATE = "product:stock:%s";
  
  @Autowired
  private StringRedisTemplate stringRedisTemplate;
  
  /**
   * 监听商品的消息
   */
  @RabbitListener(queuesToDeclare = @Queue("productInfo"))
  public void process(String message) {
  
    List<ProductInfoOutput> productInfoOutputList = (List<ProductInfoOutput>) JsonUtil.fromJson(message,
      new TypeReference<List<ProductInfoOutput>>() {});
    
    log.info("message: {}", productInfoOutputList);
  
    assert productInfoOutputList != null;
    for (ProductInfoOutput productInfoOutput : productInfoOutputList) {
      String key = String.format(PRODUCT_STOCK_TEMPLATE, productInfoOutput.getProductId());
      String stock = productInfoOutput.getProductStock().toString();
      stringRedisTemplate.opsForValue().set(key, stock);
    }
  }

}
