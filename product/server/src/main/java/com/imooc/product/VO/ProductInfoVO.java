package com.imooc.product.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInfoVO {
  
  @JsonProperty("id")
  private String productId;
  
  @JsonProperty("name")
  private String prodcutName;
  
  @JsonProperty("price")
  private BigDecimal productPrice;
  
  @JsonProperty("description")
  private String productDescription;
  
  @JsonProperty("icon")
  private String productIcon;
  
}
