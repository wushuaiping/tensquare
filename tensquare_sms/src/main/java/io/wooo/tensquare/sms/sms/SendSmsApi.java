package io.wooo.tensquare.sms.sms;

import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: wushuaiping
 * @date: 2018/11/29 11:02 AM
 * @description:
 */
@Component
public class SendSmsApi {

    @Value("${yunpian.sms.apikey}")
    private String apikey;

    /**
     * 发送验证码短信
     *
     * @param smsContent
     * @param mobile
     */
    public void sendSms(String smsContent, String mobile) {
        YunpianClient yunpianClient = new YunpianClient(apikey).init();
        final Map<String, String> param = yunpianClient.newParam(2);
        param.put(YunpianClient.MOBILE, mobile);
        param.put(YunpianClient.TEXT, smsContent);
        Result<SmsSingleSend> result = yunpianClient.sms().single_send(param);
        if (result.getCode() != 0) {
            retrySendSms(yunpianClient, param, 0, 9);
        }
        yunpianClient.close();
    }

    /**
     * 重试
     *
     * @param yunpianClient
     * @param param
     * @param i
     * @param max
     */
    public void retrySendSms(YunpianClient yunpianClient, Map<String, String> param, int i, int max) {

        if (i == max) {
            return;
        }

        final Result<SmsSingleSend> result = yunpianClient.sms().single_send(param);
        if (result.getCode() != 0) {
            i++;
            retrySendSms(yunpianClient, param, i, max);
        }
    }
}
