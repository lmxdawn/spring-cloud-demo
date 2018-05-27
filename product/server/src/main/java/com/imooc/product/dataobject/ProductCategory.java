package com.imooc.product.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class ProductCategory {
  
  // 类目 id
  @Id
  @GeneratedValue
  private Integer categoryId;
  
  // 类目名称
  private String categoryName;
  
  // 类目编号
  private Integer categoryType;
  
}
