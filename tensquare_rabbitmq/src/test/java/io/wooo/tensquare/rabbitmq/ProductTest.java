package io.wooo.tensquare.rabbitmq;

import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: wushuaiping
 * @date: 2018/11/27 8:53 PM
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TensquareRabbitmqApplication.class)
public class ProductTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void send1(){
        rabbitTemplate.convertAndSend("testexchange", "itcast", "分列模式，发消息给itcast");
    }

    @Test
    public void send2(){
        rabbitTemplate.convertAndSend("testexchange", "itheima", "分列模式，发消息给itheima");
    }

    @Test
    public void send3(){
        rabbitTemplate.convertAndSend("testexchange", "kudingyu", "分列模式，发消息给kudingyu");
    }

}
