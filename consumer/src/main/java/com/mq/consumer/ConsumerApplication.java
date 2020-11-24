package com.mq.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @since 2020/9/14 9:55
 * @author JTX@jtxyh.top
 **/
@SpringBootApplication
@ComponentScan("com.mq")
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class,args);
    }
}
