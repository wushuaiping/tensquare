package io.wooo.tensquare.sms.listener;

import io.wooo.tensquare.sms.sms.SendSmsApi;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: wushuaiping
 * @date: 2018/11/28 5:40 PM
 * @description:
 */
@Component
@AllArgsConstructor
public class SmsListener {

    private final SendSmsApi sendSmsApi;

    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(value = @Queue("sendVerificationCodeSms"), exchange = @Exchange("sms")))
    public void sendSms(Map<String, String> map) {
        final String mobile = map.get("mobile");
        final String smsContent = map.get("smsContent");
        sendSmsApi.sendSms(smsContent, mobile);
    }
}
