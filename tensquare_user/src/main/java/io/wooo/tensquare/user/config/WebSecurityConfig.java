package io.wooo.tensquare.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 安全配置类
 *
 * @author: wushuaiping
 * @date: 2018/11/29 2:47 PM
 * @description:
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests() // security全注解配置实现的开端
                .antMatchers("/**").permitAll() // 配置拦截的路径和任何权限都可以访问
                .anyRequest().authenticated() // 任意的请求都需要认证后才能访问
                .and().csrf().disable(); // 并且开启csrf拦截失效
    }
}
