package com.imooc.product.service.impl;

import com.imooc.product.common.DecreaseStockInput;
import com.imooc.product.common.ProductInfoOutput;
import com.imooc.product.dto.CartDTO;
import com.imooc.product.dataobject.ProductInfo;
import com.imooc.product.enums.ResultEnum;
import com.imooc.product.exception.ProductException;
import com.imooc.product.repository.ProductInfoRepository;
import com.imooc.product.service.ProductService;
import com.imooc.product.utils.JsonUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品相关
 */
@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductInfoRepository productInfoRepository;
    
    @Autowired
    private AmqpTemplate amqpTemplate;
    
    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(0);
    }
    
    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }
    
    @Override
    public void buckleStock(List<DecreaseStockInput> decreaseStockInputList) {
    
        List<ProductInfo> productInfoList = buckleStockProcess(decreaseStockInputList);
        
        List<ProductInfoOutput> productInfoOutputList = productInfoList.stream().map(e -> {
            // 发送消息MQ
            ProductInfoOutput productInfoOutput = new ProductInfoOutput();
            BeanUtils.copyProperties(e, productInfoOutput);
            return productInfoOutput;
        }).collect(Collectors.toList());
    
        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoOutputList));
        
    }
    
    @Transactional
    public List<ProductInfo> buckleStockProcess(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = new ArrayList<>();
        for (DecreaseStockInput decreaseStockInput : decreaseStockInputList) {
            ProductInfo productInfo = productInfoRepository.getOne(decreaseStockInput.getProductId());
            if (StringUtils.isEmpty(productInfo)) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer stock = productInfo.getProductStock() - decreaseStockInput.getProductQuantity();
            if (stock < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            
            productInfo.setProductStock(stock);
            productInfoRepository.save(productInfo);
            productInfoList.add(productInfo);
        }
        return productInfoList;
    }
}
