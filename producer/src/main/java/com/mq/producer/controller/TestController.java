package com.mq.producer.controller;

import com.mq.producer.config.exchange.easy.EasyRabbitConfig;
import com.mq.producer.config.exchange.fanout.FanoutRabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author xiao-_-wu
 * @date 2020/11/20 17:19
 */
@RestController
@RequestMapping("/test/mq")
public class TestController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send/fanout/{params}")
    public String sendFanoutMessage(
            @PathVariable("params") String params
    ){
        System.out.println("参数:"+params);
        rabbitTemplate.convertAndSend(FanoutRabbitConfig.FANOUT_EXCHANGE_NAME,null,params);
        return "ok";
    }

    /**
     * 简单交换机
     *
     * @param params 参数
     * @return ret
     */
    @GetMapping("/send/easy/{params}")
    public String sendEasyMessage(
            @PathVariable("params") String params
    ) {
        rabbitTemplate.convertAndSend("",EasyRabbitConfig.EASY_QUEUE_NAME,"Hello Word easyRabbitMq"+params);
        return "ok";
    }

    /**
     * 直连交换机
     *
     * @param params 参数
     * @return ret
     */
    @GetMapping("/send/direct/{params}")
    public String sendDirectMessage(
            @PathVariable("params") String params
    ) {
        System.out.println("name:" + params);
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String createTime = sdf.format(new Date());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        map.put("name", params);
        //把信息存入消息队列

        /**
         * 1.exchange: 交换机
         * 2.routingKey: 路由名称
         * 3.message: 消息
         * 4.messagePostProcessor: 完成后的处理器
         * 5.correlationData: 相关data数据体
         */
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
        return "ok";
    }
}
