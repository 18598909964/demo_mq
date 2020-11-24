package com.mq.producer.config.exchange.direct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 直连交换机
 * @author xiao_wu
 * @date 2020/11/20 17:04
 */
@Configuration
public class DirectRabbitConfig {

    /**
     * 创建队列
     */
    @Bean
    public Queue testDirectQueue(){
        /*
         * 1.queue: 队列名称
         * 2.durable: 是否持久化,写入erlang语言自带数据库
         * 3.exclusive:
         *      * 是否独占,只能有一个消费者监听这个队列
         *      * 当Connection关闭时是否删除队列
         * 4.autoDelete:
         *      * 没有Connection时自动删除
         * 5.actualName: 参数
         */
        return new Queue("TestDirectRabbitQueue",true,false,false,null);
    }

    /**
     * 创建交换机
     */
    @Bean
    public DirectExchange testDirectExchange(){
        return new DirectExchange("TestDirectExchange",true,false);
    }

    /**
     * 将队列与交换机绑定
     */
    @Bean
    public Binding bindingDirect(){

        return BindingBuilder.bind(testDirectQueue()).to(testDirectExchange()).with("TestDirectRouting");
    }

    @Bean
    DirectExchange lonelyDirectExchange() {
        return new DirectExchange("lonelyDirectExchange");
    }
}
