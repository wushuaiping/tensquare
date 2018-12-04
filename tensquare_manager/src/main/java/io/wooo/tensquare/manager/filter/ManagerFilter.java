package io.wooo.tensquare.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

/**
 *  管理员端zuul过滤器
 * @author: wushuaiping
 * @date: 2018/12/4 3:39 PM
 * @description:
 */
@Component
public class ManagerFilter extends ZuulFilter {

    @Override
    public String filterType() {
//        return "post"; // 操作之后过滤
        return "pre"; // 操作之前过滤
    }

    /**
     * 一个项目可配置N个过滤器，该方法数字越小 越先执行
     * @return
     */
    @Override
    public int filterOrder() {
        return 0; // 过滤器执行顺序，数字越小越先执行
    }

    /**
     * 该过滤器是否开启
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器中执行的操作， return任何object的值都表示继续执行
     * settSendZuulResponse(false); 表示不再继续执行
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("执行了过滤器");
        return null;
    }
}
