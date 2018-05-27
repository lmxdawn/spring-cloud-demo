package com.imooc.order.service.impl;

import com.imooc.order.dataobject.OrderDetail;
import com.imooc.order.dataobject.OrderMaster;
import com.imooc.order.dto.OrderDTO;
import com.imooc.order.enums.OrderStatusEnum;
import com.imooc.order.enums.PayStatusEnum;
import com.imooc.order.enums.ResultEnum;
import com.imooc.order.exception.OrderException;
import com.imooc.order.repository.OrderDetailRepository;
import com.imooc.order.repository.OrderMasterRepository;
import com.imooc.order.service.OrderMasterService;
import com.imooc.order.utils.KeyUtil;
import com.imooc.product.client.ProductClient;
import com.imooc.product.common.DecreaseStockInput;
import com.imooc.product.common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMasterServiceImpl implements OrderMasterService {
    
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    
    @Autowired
    private ProductClient productClient;
    
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        
        // 查询商品x信息 (调用商品服务)
        List<String> productIdList = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(productIdList);
        
        // 生成订单id
        String orderId = KeyUtil.genUniqueKey();
        // 组装购物车
        List<DecreaseStockInput> decreaseStockInputList = new ArrayList<>();
        // 计算总价
        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()){
            for (ProductInfoOutput productInfo : productInfoList){
                if (productInfo.getProductId().equals(orderDetail.getProductId())){
                    // 计算总价
                    orderAmout = productInfo.getProductPrice()
                                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                                    .add(orderAmout);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    DecreaseStockInput decreaseStockInput = new DecreaseStockInput(orderDetail.getProductId(), orderDetail.getProductQuantity());
                    decreaseStockInputList.add(decreaseStockInput);
                    orderDetailRepository.save(orderDetail);
                }
            }
        }
        
        // 扣库存(调用商品服务)
        productClient.buckleStock(decreaseStockInputList);
        
        // 订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmout);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        
        return orderDTO;
    }
    
    @Override
    @Transactional
    public OrderDTO finish(String orderId) {
        
        // 查询订单
        OrderMaster orderMaster = orderMasterRepository.getOne(orderId);
        if (StringUtils.isEmpty(orderMaster)) {
            throw new OrderException(ResultEnum.ORDER_NOT_EXIST);
        }
        
        // 判断订单状态
        if (!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }
        
        // 修改状态为已完结
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderMasterRepository.save(orderMaster);
        
        // 查询订单详情
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new OrderException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        
        return orderDTO;
    }
}
