package com.imooc.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

import java.util.UUID;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_FORWARD_FILTER_ORDER;

/**
 * 向结果添加 header 头
 */
@Component
public class AddResponseHeaderFilter extends ZuulFilter {
  @Override
  public String filterType() {
    return POST_TYPE;
  }
  
  @Override
  public int filterOrder() {
    return SEND_FORWARD_FILTER_ORDER;
  }
  
  @Override
  public boolean shouldFilter() {
    return true;
  }
  
  @Override
  public Object run() throws ZuulException {
  
    RequestContext requestContext = RequestContext.getCurrentContext();
    HttpServletResponse response = requestContext.getResponse();
    response.setHeader("X-FOO", UUID.randomUUID().toString());
    return null;
  }
}
