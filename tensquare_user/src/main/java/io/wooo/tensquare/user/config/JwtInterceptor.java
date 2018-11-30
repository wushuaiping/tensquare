package io.wooo.tensquare.user.config;

import io.jsonwebtoken.Claims;
import io.wooo.tensquare.common.exception.UnauthorizedException;
import io.wooo.tensquare.common.util.JwtUtil;
import io.wooo.tensquare.user.config.model.LoginUser;
import io.wooo.tensquare.user.enums.IdentityEnum;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt的拦截器
 *
 * @author: wushuaiping
 * @date: 2018/11/30 9:27 AM
 * @description:
 */
@Component
@AllArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private JwtUtil jwtUtil;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

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

                    LoginUser login;
                    // 如果解析出的token里面包含管理员或用户的标识，就放到请求域中去
                    if (StringUtils.equals(roles, "admin")) {
                        login = new LoginUser(claims.getId(), IdentityEnum.CLAIMS_ADMIN, claims.getSubject());
                        request.setAttribute(IdentityEnum.CLAIMS_ADMIN.getDes(), login);
                    }

                    if (StringUtils.equals(roles, "user")) {
                        login = new LoginUser(claims.getId(), IdentityEnum.CLAIMS_USER, claims.getSubject());
                        request.setAttribute(IdentityEnum.CLAIMS_USER.getDes(), login);
                    }

                } catch (Exception e) {
                    throw new UnauthorizedException("invalid_token");
                }
            }
        }

        return true;
    }

}
