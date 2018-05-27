package com.imooc.product.service;

import com.imooc.product.common.DecreaseStockInput;
import com.imooc.product.dto.CartDTO;
import com.imooc.product.ProductApplicationTests;
import com.imooc.product.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ProductServiceTest extends ProductApplicationTests {
    
    @Autowired
    private ProductService productService;
    
    @Test
    public void findUpAll() {
        
        List<ProductInfo> productInfoList = productService.findUpAll();
        
        // Assert.assertTrue(productInfoList.size() > 0);
        
    }
    
    @Test
    public void findList() {
    
        List<ProductInfo> productInfoList = productService.findList(Arrays.asList("123456","2222"));
    
        Assert.assertTrue(productInfoList.size() > 0);
    }
    
    @Test
    public void buckleStock() {
        List<DecreaseStockInput> decreaseStockInputList = new ArrayList<>();
        DecreaseStockInput decreaseStockInput = new DecreaseStockInput();
        decreaseStockInput.setProductId("123456");
        decreaseStockInput.setProductQuantity(2);
        decreaseStockInputList.add(decreaseStockInput);
        productService.buckleStock(decreaseStockInputList);
        
    }
}
