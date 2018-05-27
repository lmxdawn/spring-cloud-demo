package com.imooc.product.repository;

import com.imooc.product.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
  
  /**
   * 查询某个状态下的所有商品
   * @param productStatus
   * @return
   */
  List<ProductInfo> findByProductStatus(Integer productStatus);
  
  
  List<ProductInfo> findByProductIdIn(List<String> productIdList);

}
