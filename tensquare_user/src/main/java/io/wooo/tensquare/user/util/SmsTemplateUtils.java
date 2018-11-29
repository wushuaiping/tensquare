package io.wooo.tensquare.user.util;

import java.util.Random;

/**
 * @author: wushuaiping
 * @date: 2018/11/29 11:31 AM
 * @description:
 */
public class SmsTemplateUtils {

    public static String verificationCodeTemplate(String code) {
        return "【吴帅苹做测试的】兄dei，你验证码是" + code + "。有效期为5分钟，过期不候哈！";
    }

    public static String adminReg(String username) {
        return "【吴帅苹做测试的】" + username + "，欢迎加入 wooo.io ，请保管好您的账号密码哦。";
    }

    public static String userReg(String username){
        return "【吴帅苹做测试的】" + username + "，谢谢大兄dei注册成为我们的用户，我们在这里等你好久啦！请大兄dei保管好账号密码哦。";
    }

    public static String randomCode() {
        // 生成六位数字随机数
        Random random = new Random();
        int max = 999999;//最大数
        int min = 100000;//最小数
        int code = random.nextInt(max);//随机生成
        if (code < min) {
            code = code + min;
        }
        return String.valueOf(code);
    }

}
