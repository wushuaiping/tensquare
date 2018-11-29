package io.wooo.tensquare.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: wushuaiping
 * @date: 2018/11/27 8:57 PM
 * @description:
 */
@RabbitListener(queues = "itcast")
@Component
public class CustomerTest1 {

    @RabbitHandler
    public void getMsg(String msg) {
        System.out.println(msg);
    }
}
