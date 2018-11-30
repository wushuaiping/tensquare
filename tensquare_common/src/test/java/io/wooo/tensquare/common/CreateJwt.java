package io.wooo.tensquare.common;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

/**
 * @author: wushuaiping
 * @date: 2018/11/29 7:40 PM
 * @description:
 */
public class CreateJwt {
    public static void main(String[] args) {
        final byte[] decode = Decoders.BASE64.decode("wocaonigedjnitamakendingbuzhidaoyanshishenmehahaha");
        final JwtBuilder jwtBuilder = Jwts.builder()
                .setId("123")  // 用户id
                .setSubject("吴帅苹") // 用户名
                .setIssuedAt(new Date()) // 时间戳
                .signWith(Keys.hmacShaKeyFor(decode), SignatureAlgorithm.HS256) // 加盐
                .setExpiration(new Date(System.currentTimeMillis() + 600000)) // 设置过期时间
                .claim("role", "admin"); // 自定义

        System.out.println(jwtBuilder.compact());
    }
}
