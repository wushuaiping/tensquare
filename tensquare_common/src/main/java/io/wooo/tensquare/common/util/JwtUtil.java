package io.wooo.tensquare.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;

/**
 * Created by Administrator on 2018/4/11.
 */
@ConfigurationProperties("jwt.config")
@Setter
@Getter
public class JwtUtil {

    private String key;

    /**
     * 过期时间，毫秒单位。如果设置为负数，则永不过期
     */
    private long ttl;

    private volatile static JwtUtil jwtUtil = null;

    private JwtUtil() {
    }

    private JwtUtil(String key, long ttl) {
        this.key = key;
        this.ttl = ttl;
    }

    public static JwtUtil getInstance() {
        if (jwtUtil == null) {
            synchronized (IdWorker.class) {
                if (jwtUtil == null) {
                    jwtUtil = new JwtUtil();
                }
            }
        }
        return jwtUtil;
    }

    public static JwtUtil getInstance(String key, long ttl) {
        if (jwtUtil == null) {
            synchronized (IdWorker.class) {
                if (jwtUtil == null) {
                    jwtUtil = new JwtUtil(key, ttl);
                }
            }
        }
        return jwtUtil;
    }

    /**
     * 生成JWT
     *
     * @param id
     * @param subject
     * @return
     */
    public String createJWT(String id, String subject, String roles) {
        Date now = new Date();
        final byte[] decodeKeys = Decoders.BASE64.decode(key);
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setSubject(subject)
                .setIssuedAt(now)
                // 这个地方jjwt 0.10.0版本后更改过了
                .signWith(Keys.hmacShaKeyFor(decodeKeys), SignatureAlgorithm.HS256)
                .claim("roles", roles);
        if (ttl > 0) {
            builder.setExpiration(new Date(now.getTime() + ttl));
        }
        return "Bearer " + builder.compact();
    }

    /**
     * 解析JWT
     *
     * @param jwtStr
     * @return
     */
    public Claims parseJWT(String jwtStr) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwtStr)
                .getBody();
    }

}
