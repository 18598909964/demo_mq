package com.mq.producer.config.exchange.fanout;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 广播式交换机配置
 *
 * @author wu-_-jia
 * @date 2020/11/25 11:48
 */
@Configuration
public class FanoutRabbitConfig {

    /**
     * 队列名称 A
     */
    public static final String FANOUT_QUEUE_NAME_A = "fanout_queue_name_a";
    /**
     * 队列名称 A
     */
    public static final String FANOUT_QUEUE_NAME_B = "fanout_queue_name_b";
    /**
     * 队列名称 A
     */
    public static final String FANOUT_QUEUE_NAME_C = "fanout_queue_name_c";

    /**
     * 交换机名称
     */
    public static final String FANOUT_EXCHANGE_NAME = "fanout_exchange_name";

    /**
     * 创建交换机
     * @return 交换机
     */
    @Bean
    public FanoutExchange fanoutExchangeMethod(){
        return new FanoutExchange(FANOUT_EXCHANGE_NAME,true,false);
    }

    /**
     * 创建队列
     * @return 队列
     */
    @Bean
    public Queue fanoutQueueMethodA(){
        return new Queue(FANOUT_QUEUE_NAME_A,true,false,false);
    }
    /**
     * 创建队列
     * @return 队列
     */
    @Bean
    public Queue fanoutQueueMethodB(){
        return new Queue(FANOUT_QUEUE_NAME_B,true,false,false);
    }
    /**
     * 创建队列
     * @return 队列
     */
    @Bean
    public Queue fanoutQueueMethodC(){
        return new Queue(FANOUT_QUEUE_NAME_C,true,false,false);
    }

    /**
     * 队列与交换机进行绑定
     * @return ret
     */
    @Bean
    Binding fanoutBindingMethodA(){
        return BindingBuilder.bind(fanoutQueueMethodA()).to(fanoutExchangeMethod());
    }
    @Bean
    Binding fanoutBindingMethodB(){
        return BindingBuilder.bind(fanoutQueueMethodB()).to(fanoutExchangeMethod());
    }
    @Bean
    Binding fanoutBindingMethodC(){
        return BindingBuilder.bind(fanoutQueueMethodC()).to(fanoutExchangeMethod());
    }

}
