package com.mq.consumer.config.basics;

import com.rabbitmq.client.*;
import com.rabbitmq.client.impl.AMQConnection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 基础方式使用mq-消费者
 *
 * @author xiao-_-wu
 * @date 2020/12/6 16:24
 */
public class ConsumerMqBasics {

    public static final String USERNAME = "wujia";
    public static final String PASSWORD = "980307";
    public static final String EASY_QUEUE = "easy-mode";
    public static final String VIRTUAL_MACHINE = "mqhost";

    public static void easyModeConsumer() throws IOException, TimeoutException {
        //1.创建connection
        ConnectionFactory factory = new ConnectionFactory();
        //2.设置参数
        /*factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        factory.setVirtualHost(VIRTUAL_MACHINE);*/
        //3.创建连接
        Connection connection = factory.newConnection();
        //4.创建channel
        Channel channel = connection.createChannel();
        //5.获取消息
        /**
         * 1.队列名称 queue
         * 2.是否自动确认 autoAck
         * 3.回调函数 callback
         */
        Consumer consumer = new DefaultConsumer(channel){
            /**
             * 重写处理交货方法
             * @param consumerTag 消费者标签
             * @param envelope 获取交换机路由等信息
             * @param properties 属性
             * @param body 数据体
             * @throws IOException
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumerTag:" + consumerTag);
                System.out.println("envelope:" + envelope.toString());
                System.out.println("properties:" + properties);
                System.out.println("body:" + new String(body));
            }
        };
        channel.basicConsume(EASY_QUEUE,true,consumer);
    }

    public static void main(String[] args) {
        try {
            easyModeConsumer();
        } catch (Exception e) {
            System.out.println("标准异常");
        }
    }
}
