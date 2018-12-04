package io.wooo.tensquare.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.util.RequestContentDataExtractor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 *  该过滤器处理经过zuul后，请求头header丢失的问题，
 *  可以在配置文件zuul.sensitive-headers解决此问题
 * @author: wushuaiping
 * @date: 2018/12/4 3:56 PM
 * @description:
 */
@Component
@AllArgsConstructor
@Deprecated
public class WebFilter extends ZuulFilter {

    /**
     * pre ：可以在请求被路由之前调用
     * route ：在路由请求时候被调用
     * post ：在route和error过滤器之后被调用
     * error ：处理请求时发生错误时被调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }


    @Override
    public Object run() throws ZuulException {


//        // 从request域中得到Authorization头
//        final RequestContext currentContext = RequestContext.getCurrentContext();
//        final HttpServletRequest request = currentContext.getRequest();
//        final String authorization = request.getHeader("Authorization");
//        if (StringUtils.isNotBlank(authorization)){
//            // 将头信息继续往下传
//            currentContext.addZuulRequestHeader("Authorization", authorization);
//        }
        return null;
    }
}
