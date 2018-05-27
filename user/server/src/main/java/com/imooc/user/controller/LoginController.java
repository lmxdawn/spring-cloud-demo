package com.imooc.user.controller;

import com.imooc.user.VO.ResultVO;
import com.imooc.user.constant.CookieConstant;
import com.imooc.user.constant.RedisConstant;
import com.imooc.user.dataobject.UserInfo;
import com.imooc.user.enums.ResultEnum;
import com.imooc.user.enums.RoleEnum;
import com.imooc.user.exception.UserException;
import com.imooc.user.service.UserService;
import com.imooc.user.utils.CookieUtil;
import com.imooc.user.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/login")
public class LoginController {
  
  @Autowired
  private UserService userService;
  
  @Autowired
  private StringRedisTemplate stringRedisTemplate;
  
  /**
   * 买家登录
   * @param openid
   * @param response
   * @return
   */
  @GetMapping("/buyer")
  public ResultVO buyer(@RequestParam("openid") String openid,
                        HttpServletResponse response){
    if (StringUtils.isEmpty(openid)) {
      throw new UserException(ResultEnum.PARAM_ERROR);
    }
    UserInfo userInfo = userService.findByOpenid(openid);
    if (userInfo == null) {
      throw new UserException(ResultEnum.LOGIN_FAIL);
    }
    
    // 判断角色
    if (!userInfo.getRole().equals(RoleEnum.BUYER.getCode())) {
      throw new UserException(ResultEnum.ROLE_ERROR );
    }
    
    // 设置cookie
    CookieUtil.set(response, CookieConstant.OPENID, userInfo.getOpenid(), CookieConstant.expire);
    
    return ResultVOUtil.success();
  }
  
  /**
   * 卖家家登录
   * @param openid
   * @param request
   * @param response
   * @return
   */
  @GetMapping("/seller")
  public ResultVO seller(@RequestParam("openid") String openid,
                         HttpServletRequest request,
                         HttpServletResponse response){
  
    String cToken = CookieConstant.TOKEN;
    Cookie cookie = CookieUtil.get(request, cToken);
    String isKey = cookie != null ? String.format(RedisConstant.USER_SELLER_TOKEN_TEMPLATE, cookie.getValue()) : "";
    if (cookie != null && !StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(isKey))) {
      return ResultVOUtil.success();
    }
    
    if (StringUtils.isEmpty(openid)) {
      throw new UserException(ResultEnum.PARAM_ERROR);
    }
    UserInfo userInfo = userService.findByOpenid(openid);
    if (userInfo == null) {
      throw new UserException(ResultEnum.LOGIN_FAIL);
    }
  
    // 判断角色
    if (!userInfo.getRole().equals(RoleEnum.SELLER.getCode())) {
      throw new UserException(ResultEnum.ROLE_ERROR );
    }
    // 过期时间
    Integer expire = CookieConstant.expire;
    // 设置Redis
    String token = UUID.randomUUID().toString();
    String key = String.format(RedisConstant.USER_SELLER_TOKEN_TEMPLATE, token);
    stringRedisTemplate.opsForValue().set(key, userInfo.getOpenid(), expire, TimeUnit.SECONDS);
  
    // 设置cookie
    CookieUtil.set(response, cToken, token, expire);
  
    return ResultVOUtil.success();
  }
  
  
}
