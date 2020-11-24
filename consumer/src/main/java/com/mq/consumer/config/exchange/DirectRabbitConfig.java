package com.mq.consumer.config.exchange;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiao_wu
 * @date 2020/11/20 17:45
 */
@Configuration
public class DirectRabbitConfig {

    /**
     * 创建队列
     */
    @Bean
    public Queue testDirectQueue(){
        return new Queue("TestDirectRabbitQueue",true);
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
}
