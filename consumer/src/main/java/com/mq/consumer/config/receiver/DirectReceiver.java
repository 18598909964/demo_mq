package com.mq.consumer.config.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author xiao_wu
 * @date 2020/11/20 17:46
 */
@Component
public class DirectReceiver {

    @RabbitHandler
    @RabbitListener(queues = "TestDirectRabbitQueue")
    public void process(Map map){
        System.out.println("这里是消费者");
        System.out.println(map.toString());
    }
}
