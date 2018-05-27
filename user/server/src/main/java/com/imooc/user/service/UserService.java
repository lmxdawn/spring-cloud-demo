package com.imooc.user.service;

import com.imooc.user.dataobject.UserInfo;

/**
 * 用户相关
 */
public interface UserService {
  
  /**
   * 通过openid 查询用户信息
   * @param openid
   * @return
   */
  UserInfo findByOpenid(String openid);

}
