package com.imooc.product.utils;

import com.imooc.product.VO.ResultVo;

import java.util.List;

/**
 * 请求返回体的工具类
 */
public class ResultVOUtil {
  
  /**
   * 请求成功返回
   * @param object
   * @return
   */
  public static ResultVo suceess(Object object) {
    ResultVo<Object> resultVo = new ResultVo<>();
    resultVo.setCode(0);
    resultVo.setMsg("success");
    resultVo.setData(object);
    return resultVo;
  }
  
}
