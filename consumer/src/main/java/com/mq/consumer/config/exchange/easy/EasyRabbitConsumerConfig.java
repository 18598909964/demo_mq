package com.mq.consumer.config.exchange.easy;

import com.rabbitmq.client.*;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xiao_wu
 * @date 2020/11/24 11:54
 */
@Configuration
public class EasyRabbitConsumerConfig {
    /**
     * 队列名称
     */
    public static final String EASY_QUEUE_NAME = "easy_queue_name";

    /**
     * 学习版本
     * @throws IOException
     * @throws TimeoutException
     */
    public static void easyRabbitConsumer() throws IOException, TimeoutException {
        //1.创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //2.设置参数
//        factory.setHost("192.168.0.13"); //存在默认值 本机地址
//        factory.setPort(5672);  //存在默认端口
//        factory.setVirtualHost("/itcast");    //选择虚拟机,存在默认虚拟机
//        factory.setUsername("wu-_-jia"); //存在默认用户名 guest
//        factory.setPassword("980307");  //存在默认密码 guest
        //3.创建连接
        Connection connection = factory.newConnection();
        //4.创建Channel
        Channel channel = connection.createChannel();
        //5.创建队列
        channel.queueDeclare(EASY_QUEUE_NAME,true,false,false,null);
        //6.接收消息
        /**
         * 1.exchange: 交换机
         * 2.routingKey: 路由名称(一般与队列名称一样)
         * 3.props: 配置信息
         * 4.body: 发送数据
         */
        Consumer consumer = new DefaultConsumer(channel){
            /**
             * 回调方法
             * @param consumerTag 消息标签
             * @param envelope 获取对应信息(交换机,路由名称等)
             * @param properties 配置信息
             * @param body 数据体
             * @throws IOException
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumerTag" + consumerTag);
                System.out.println("envelope:" + envelope.getExchange());
                System.out.println("envelope:" + envelope.getRoutingKey());
                System.out.println("properties" + properties);
                System.out.println("body" + new String(body));
            }
        };
        channel.basicConsume(EASY_QUEUE_NAME,true,consumer);
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("这是消费者");
        easyRabbitConsumer();
    }
}
