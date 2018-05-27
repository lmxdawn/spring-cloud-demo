package com.imooc.user.utils;

import com.imooc.user.VO.ResultVO;
import com.imooc.user.enums.ResultEnum;

/**
 * http 请求的操作类
 */
public class ResultVOUtil {
  
  /**
   * 请求成功的处理
   * @param object
   * @return
   */
  public static ResultVO success(Object object) {
    ResultVO<Object> resultVo = new ResultVO<>();
    resultVo.setCode(0);
    resultVo.setMsg("success");
    resultVo.setData(object);
    return resultVo;
  }
  public static ResultVO success(){
    return success(null);
  }
  
  /**
   * 请求错误的返回
   * @param errcode
   * @param errMsg
   * @return
   */
  public static ResultVO error(Integer errcode, String errMsg){
    ResultVO<Object> resultVo = new ResultVO<>();
    resultVo.setCode(errcode);
    resultVo.setMsg(errMsg);
    return resultVo;
  }
  
  public static ResultVO error(ResultEnum ResultEnum) {
    return error(ResultEnum.getCode(), ResultEnum.getMessage());
  }
}
