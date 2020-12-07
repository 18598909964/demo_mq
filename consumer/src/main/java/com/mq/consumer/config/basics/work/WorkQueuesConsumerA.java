package com.mq.consumer.config.basics.work;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 工作队列模式,A
 *
 * @author xiao-_-wu
 * @date 2020/12/7 13:53
 */
public class WorkQueuesConsumerA {

    public static final String WORK_QUEUE = "work-queue";

    public static void workModeConsumerA() throws IOException, TimeoutException {
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
//                System.out.println("consumerTag:" + consumerTag);
//                System.out.println("envelope:" + envelope.toString());
//                System.out.println("properties:" + properties);
                System.out.println("body:" + new String(body));
            }
        };
        channel.basicConsume(WORK_QUEUE,true,consumer);
    }

    public static void main(String[] args) {
        try {
            workModeConsumerA();
        } catch (Exception e) {
            System.out.println("标准异常");
        }
    }
}
