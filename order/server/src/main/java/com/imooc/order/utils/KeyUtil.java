package com.imooc.order.utils;

import java.util.Random;

/**
 * UUID 生成工具类
 */
public class KeyUtil {
  
  /**
   * 生成唯一的主键
   */
  public static synchronized String genUniqueKey() {
    Random random = new Random();
    Integer number = random.nextInt(900000) + 100000;
    return System.currentTimeMillis() + String.valueOf(number);
  }

}
