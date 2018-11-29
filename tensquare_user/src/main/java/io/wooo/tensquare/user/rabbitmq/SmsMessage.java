package io.wooo.tensquare.user.rabbitmq;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wushuaiping
 * @date: 2018/11/29 11:52 AM
 * @description:
 */
@Component
@AllArgsConstructor
public class SmsMessage {

    private RabbitTemplate rabbitTemplate;

    private static final String sendVerificationCodeSms = "sendVerificationCodeSms";

    /**
     * 发送消息给指定手机号码
     *
     * @param mobile
     * @param smsContent
     */
    public void sendMobileSms(String mobile, String smsContent) {
        Map<String, String> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put("smsContent", smsContent);
        // 将验证码发送给用户
        rabbitTemplate.convertAndSend(sendVerificationCodeSms, map);
    }
}
