package com.imooc.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 鉴权, 判断token是否存在
 */
@Component
public class TokenFilter extends ZuulFilter {
  
  @Override
  public String filterType() {
    return PRE_TYPE;
  }
  
  @Override
  public int filterOrder() {
    return PRE_DECORATION_FILTER_ORDER - 1;
  }
  
  @Override
  public boolean shouldFilter() {
    return true;
  }
  
  @Override
  public Object run() throws ZuulException {
  
    // RequestContext requestContext = RequestContext.getCurrentContext();
    // HttpServletRequest request = requestContext.getRequest();
    // String token = request.getParameter("token");
    // if (StringUtils.isEmpty(token)) {
    //   requestContext.setSendZuulResponse(false);
    //   requestContext.setResponseStatusCode(401);
    // }
    return null;
  }
}
