package io.wooo.tensquare.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import io.wooo.tensquare.common.util.JwtUtil;
import io.wooo.tensquare.manager.enums.IdentityEnum;
import io.wooo.tensquare.manager.model.LoginUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 管理员端zuul过滤器
 *
 * @author: wushuaiping
 * @date: 2018/12/4 3:39 PM
 * @description:
 */
@Component
public class ManagerFilter extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String filterType() {
//        return "post"; // 操作之后过滤
        return "pre"; // 操作之前过滤
    }

    /**
     * 一个项目可配置N个过滤器，该方法数字越小 越先执行
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0; // 过滤器执行顺序，数字越小越先执行
    }

    /**
     * 该过滤器是否开启
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器中执行的操作， return任何object的值都表示继续执行
     * settSendZuulResponse(false); 表示不再继续执行
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        final RequestContext currentContext = RequestContext.getCurrentContext();
        final HttpServletRequest request = currentContext.getRequest();

        // OPTIONS请求是网管路由用来找这个请求去哪个服务的，所以需要放行
        if (StringUtils.equals(request.getMethod(), "OPTIONS")){
            return null;
        }

        final String authorization = request.getHeader("Authorization");
        // 有token的请求头才去进行校验
        if (!StringUtils.isBlank(authorization)) {
            // 检查token是否正确
            if (authorization.startsWith("Bearer ")) {
                try {
                    // 获取token，并解析token
                    final String token = authorization.substring(7);
                    final Claims claims = jwtUtil.parseJWT(token);
                    final String roles = (String) claims.get("roles");

                    // 如果解析出的token里面包含管理员或用户的标识，就放到请求域中去
                    if (StringUtils.equals(roles, "admin")) {
                        currentContext.addZuulRequestHeader("Authorization", authorization);
                        return null;
                    }

                } catch (Exception e) {
                    currentContext.setSendZuulResponse(false);
                }
            }
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(403);
            currentContext.setResponseBody("invalid_token");
            currentContext.getResponse().setContentType("text/html; charset=utf-8");
        }
        return null;
    }
}
