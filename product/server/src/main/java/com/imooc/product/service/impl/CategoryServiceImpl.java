package com.imooc.product.service.impl;

import com.imooc.product.dataobject.ProductCategory;
import com.imooc.product.repository.ProductCategoryRepository;
import com.imooc.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
  
  @Autowired
  ProductCategoryRepository productCategoryRepository;
  
  /**
   * 查询某些分类的信息
   * @param categoryTypeList
   * @return
   */
  @Override
  public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
    return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
  }
}
