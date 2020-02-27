package net.spring.kyao233.api_gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author K.Yao
 * @date 2020/1/29 17:39
 */
@Component
public class LoginFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 4;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        System.out.println(request.getRequestURI());
        System.out.println(request.getRequestURL());
//        if ("/apigateway/order/api/api/v1/order/save".equals(request.getRequestURI())) {
//            return true;
//        } else if("/apigateway/order/api/v1/order/save/ribbon".equals(request.getRequestURI())) {
//            return true;
//        } else if("/apigateway/order/api/v1/order/save/feign".equals(request.getRequestURI())) {
//            return true;
//        }
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("entering filter..");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = null;
        token = request.getHeader("token");
        if(StringUtils.isBlank(token)) {
            token = request.getParameter("token");
        }
        if(StringUtils.isBlank(token)) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }

        return null;
    }
}
