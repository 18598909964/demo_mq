package com.mq.consumer.config.receiver.easy;

import com.mq.consumer.config.exchange.easy.EasyRabbitConsumerConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 简单模式
 * @author xiao_wu
 * @date 2020/11/24 13:24
 */
@Component
public class EasyReceiver {

/*    @RabbitHandler
    @RabbitListener(queues = EasyRabbitConsumerConfig.EASY_QUEUE_NAME)
    public void process(byte[] map){
        System.out.println("这里是消费者");
        System.out.println(map.toString());
    }*/
}
