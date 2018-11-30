package io.wooo.tensquare.qa.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 拦截器配置类
 *
 * @author: wushuaiping
 * @date: 2018/11/30 9:30 AM
 * @description:
 */
@Configuration
@AllArgsConstructor
public class InterceptorConfig extends WebMvcConfigurationSupport {

    private JwtInterceptor jwtInterceptor;

    protected void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器要声明拦截器对象和要拦截的请求
        registry.addInterceptor(jwtInterceptor) // 把我们自定义的jtw拦截器添加进去
                .addPathPatterns("/**") // 需要拦截的路径，所有路径
                .excludePathPatterns("/**/login") // 登录页面不需要拦截
                .excludePathPatterns("/**/sendsms/**") // 发送手机验证码页面不拦截登录
                .excludePathPatterns("/user/register/**"); // 用户注册页面不需要拦截
    }
}
