package io.wooo.tensquare.user.service;

import io.wooo.tensquare.common.exception.BadRequestException;
import io.wooo.tensquare.user.entity.User;
import io.wooo.tensquare.user.rabbitmq.SmsMessage;
import io.wooo.tensquare.user.repository.UserRepository;
import io.wooo.tensquare.user.util.SmsTemplateUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 用户service
 *
 * @author: wushuaiping
 * @date: 2018/11/28 3:23 PM
 * @description:
 */
@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private RedisTemplate<String, String> redisTemplate;

    private SmsMessage smsMessage;

    public void sendSms(String mobile) {

        // 构造缓存的key
        final String key = "register_" + mobile;

        // 用户过度点击了发送
        final String expiration = redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(expiration)) {
            throw new BadRequestException("请一分钟后重试");
        }

        // 设置一个缓冲，过期
        redisTemplate.opsForValue().set(key, mobile, 1, TimeUnit.MINUTES);

        // 生成六位数字验证码
        final String code = SmsTemplateUtils.randomCode();

        // 把验证码存缓存中，5分钟过期时间
        redisTemplate.opsForValue().set(key + ":" + code, code, 5, TimeUnit.MINUTES);

        // 发送验证码给手机
        final String smsContent = SmsTemplateUtils.verificationCodeTemplate(code);
        smsMessage.sendMobileSms(mobile, smsContent);

    }

    public void register(String code, User user) {

        // 构造缓存key
        final String key = "register_" + user.getMobile() + ":" + code;

        // 验证验证码是否正确
        final String randomCode = redisTemplate.opsForValue().get(key);
        if (!StringUtils.equals(code, randomCode)) {
            throw new BadRequestException("你这验证码不对，兄dei。");
        }

        // 初始化用户的一些信息
        user.initUser(user);
        userRepository.save(user);
        // 验证码用完后。将缓存中的验证码删除
        redisTemplate.delete(key);
    }
}
