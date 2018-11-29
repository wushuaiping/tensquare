package io.wooo.tensquare.rabbitmq.customer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: wushuaiping
 * @date: 2018/11/27 9:38 PM
 * @description:
 */
@Component
public class Customer {

    @RabbitListener(queues = "itheima")
    public void getMsg1(String msg) {
        System.out.println(msg);
        System.out.println("这是itheima");
    }

    @RabbitListener(queues = "itcast")
    public void getMsg2(String msg) {
        System.out.println(msg);
        System.out.println("这是itcast");
    }

    @RabbitListener(queues = "kudingyu")
    public void getMsg3(String msg) {
        System.out.println(msg);
        System.out.println("这是kudingyu");
    }
}
