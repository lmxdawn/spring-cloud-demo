package com.imooc.order.repository;

import com.imooc.order.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 订单详情
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    
    /**
     * 查询订单的详情
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);
    
}
