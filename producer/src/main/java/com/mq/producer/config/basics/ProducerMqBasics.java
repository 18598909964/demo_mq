package com.mq.producer.config.basics;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 基础方式使用mq-生产者
 * 
 * @author xiao-_-wu
 * @date 2020/12/6 13:19
 */
public class ProducerMqBasics {

    public static final String USERNAME = "wujia";
    public static final String PASSWORD = "980307";
    public static final String EASY_QUEUE = "easy-mode";
    public static final String VIRTUAL_MACHINE = "mqhost";

    /**
     * 生产者简单模式
     * @param str 参数
     */
    public static void easyMode(String str) throws IOException, TimeoutException {
        //1.创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //2.设置参数
        //存在默认值 本机地址
        /*factory.setHost("192.168.31.228");
        //存在默认端口
        factory.setPort(5672);
        //选择虚拟机,存在默认虚拟机
        factory.setVirtualHost(VIRTUAL_MACHINE);
        //存在默认用户名 guest
        factory.setUsername(USERNAME);
        //存在默认密码 guest
        factory.setPassword(PASSWORD);*/
        //3.创建连接
        Connection connection = factory.newConnection();
        //4.创建Channel
        Channel channel = connection.createChannel();
        //5.创建队列
        /**
         * 1.队列名称 queue
         * 2.是否持久化 durable
         * 3.是否独占 exclusive
         *     只允许一个消费者占用这个队列
         *     关闭Connection时,是否删除队列
         * 4.是否自动删除 autoDelete
         * 5.其他配置 arguments
         */
        channel.queueDeclare(EASY_QUEUE,true,false,false,null);
        //6.发送消息
        /**
         * 1.exchange: 交换机
         *      不设置,则是默认交换机
         * 2.routingKey: 路由名称(一般与队列名称一样)
         * 3.props: 配置信息
         * 4.body: 发送数据
         */
        channel.basicPublish("",EASY_QUEUE,null,str.getBytes());
        //7.释放资源
        channel.close();
        connection.close();
    }

    public static void main(String[] args) {
        try {
            easyMode("这是一个简单的交换机测试1111");
        } catch (Exception e) {
            System.out.println("标准异常");
        }
    }
}
