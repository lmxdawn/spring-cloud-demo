package com.imooc.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * 解决跨域
 */
@Configuration
public class CorsConfig {
  
  @Bean
  public CorsFilter corsFilter() {
  
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    final CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true); // 是否支持Cookie 跨域
    config.setAllowedOrigins(Arrays.asList("*")); // 允许的原始域 List 例如:http://baidu.com
    config.setAllowedMethods(Arrays.asList("*")); // 允许的header头
    config.setAllowedMethods(Arrays.asList("*")); // 允许的方法 如:GET
    config.setMaxAge(300L); // 设置缓存时间, 意思:某个时间内相同的就不在进行检查
    
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }
  
}
