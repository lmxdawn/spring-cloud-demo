package com.imooc.product.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 商品
 */
@Data
public class ProductVO {
  
  /**
   * 分类名称
   */
  @JsonProperty("name")
  private String categoryName;
  
  /**
   * 分类编号
   */
  @JsonProperty("type")
  private Integer categoryType;
  
  /**
   * 商品列表
   */
  @JsonProperty("foods")
  List<ProductInfoVO> productInfoVOList;
  

}
