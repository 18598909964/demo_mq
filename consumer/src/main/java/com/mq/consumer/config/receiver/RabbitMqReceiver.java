package com.mq.consumer.config.receiver;

import com.mq.consumer.config.exchange.easy.EasyRabbitConsumerConfig;
import com.mq.consumer.config.exchange.fanout.FanoutRabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author xiao_wu
 * @date 2020/11/20 17:46
 */
@Component
public class RabbitMqReceiver {

    /**
     * 这是直连模式的消费者
     * @param map
     */
    @RabbitHandler
    @RabbitListener(queues = "TestDirectRabbitQueue")
    public void process(Map map){
        System.out.println("这里是消费者");
        System.out.println(map.toString());
    }

    /**
     * 这是简单模式的消费者
     * @param o
     */
    @RabbitHandler
    @RabbitListener(queues = EasyRabbitConsumerConfig.EASY_QUEUE_NAME)
    public void easyReceiver(Object o){
        System.out.println("这是简单模式下的消费者");
        System.out.println(o.toString());
    }

    @RabbitHandler
    @RabbitListener(queues = FanoutRabbitConfig.FANOUT_QUEUE_NAME_A)
    public void fanoutReceiverA(Object o){
        System.out.println("这是扇形(广播)消费者--A");
        System.out.println("message:" + o.toString());
    }
    @RabbitHandler
    @RabbitListener(queues = FanoutRabbitConfig.FANOUT_QUEUE_NAME_B)
    public void fanoutReceiverB(Object o){
        System.out.println("这是扇形(广播)消费者--B");
        System.out.println("message:" + o.toString());
    }
    @RabbitHandler
    @RabbitListener(queues = FanoutRabbitConfig.FANOUT_QUEUE_NAME_C)
    public void fanoutReceiverC(Object o){
        System.out.println("这是扇形(广播)消费者--C");
        System.out.println("message:" + o.toString());
    }

    public static void main(String[] args) {
        System.out.println("ceshi");
    }
}
