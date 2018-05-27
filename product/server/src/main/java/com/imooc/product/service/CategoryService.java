package com.imooc.product.service;

import com.imooc.product.dataobject.ProductCategory;

import java.util.List;

/**
 * 商品分类
 */
public interface CategoryService {
  
  List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
  
}
