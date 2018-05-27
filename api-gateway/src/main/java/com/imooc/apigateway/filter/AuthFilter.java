package com.imooc.apigateway.filter;

import com.imooc.apigateway.constant.CookieConstant;
import com.imooc.apigateway.constant.RedisConstant;
import com.imooc.apigateway.utils.CookieUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 权限
 */
@Component
public class AuthFilter extends ZuulFilter {
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
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
        
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        
        /**
         * /order/order/create 只能买家访问
         * /order/order/finish 只能卖家访问
         * /product/list 都可以访问
         */
        if ("/order/order/create".equals(request.getRequestURI())) {
            Cookie cookie = CookieUtil.get(request, CookieConstant.OPENID);
            if (cookie == null || StringUtils.isEmpty(cookie.getValue())) {
                requestContext.setSendZuulResponse(false);
                requestContext.setResponseStatusCode(401);
            }
        }
        
        if ("/order/order/finish".equals(request.getRequestURI())) {
            Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
            Boolean emptyCookie = cookie == null || StringUtils.isEmpty(cookie.getValue());
            String rKey = !emptyCookie ? String.format(RedisConstant.USER_SELLER_TOKEN_TEMPLATE, cookie.getValue()) : "";
            if (emptyCookie
                    || StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(rKey))) {
                requestContext.setSendZuulResponse(false);
                requestContext.setResponseStatusCode(401);
            }
        }
        
        
        return null;
    }
}
