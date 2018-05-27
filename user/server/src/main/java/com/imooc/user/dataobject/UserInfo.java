package com.imooc.user.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 用户
 */
@Data
@Entity
public class UserInfo {
  
  /**
   * id
   */
  @Id
  private String id;
  
  /**
   * 用户名
   */
  private String username;
  
  /**
   * 用户密码
   */
  private String password;
  
  /**
   * 用户的openid
   */
  private String openid;
  
  /**
   * 用户的权限
   */
  private Integer role;

}
