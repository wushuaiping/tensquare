package io.wooo.tensquare.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;

/**
 * @author: wushuaiping
 * @date: 2018/11/29 7:46 PM
 * @description:
 */
public class ParseJwt {
    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjMiLCJzdWIiOiLlkLTluIXoi7kiLCJpYXQiOjE1NDM0OTUwNDIsImV4cCI6MTU0MzQ5NTY0Miwicm9sZSI6ImFkbWluIn0.MQJKHt9oZw9sK3wlxg3QLb4Wauk8b3wSWC5VqkI0gy0";
        final Claims parse = Jwts.parser()
                .setSigningKey("wocaonigedjnitamakendingbuzhidaoyanshishenmehahaha")
                .parseClaimsJws(token)
                .getBody();
        System.out.println("用户id：" + parse.getId());
        System.out.println("用户名：" + parse.getSubject());
        System.out.println("登录时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(parse.getIssuedAt()));
        System.out.println("token过期时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(parse.getExpiration()));
        System.out.println("用户角色：" + parse.get("role"));
    }
}
