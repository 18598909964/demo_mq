package com.mq.producer.config.exchange.easy;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 创建简单模式的消息队列
 * @author xiao_wu
 * @date 2020/11/24 9:58
 */
@Configuration
public class EasyRabbitConfig {
    /**
     * 队列名称
     */
    public static final String EASY_QUEUE_NAME = "easy.queue.name";

    @Bean
    public Queue easyRabbitQueue(){
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
        return new Queue(EASY_QUEUE_NAME,true,false,false,null);
    }

    public static void easyRabbitMq(String params) throws IOException, TimeoutException {
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
        //6.发送消息
        /**
         * 1.exchange: 交换机
         * 2.routingKey: 路由名称(一般与队列名称一样)
         * 3.props: 配置信息
         * 4.body: 发送数据
         */
        channel.basicPublish("",EASY_QUEUE_NAME,null,params.getBytes());
        //7.释放资源
        channel.close();
        connection.close();
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        for (int i = 0; i < 10; i++) {
            easyRabbitMq("这是简单交换机,第:"+(i+1)+"次!");
        }
    }

}
