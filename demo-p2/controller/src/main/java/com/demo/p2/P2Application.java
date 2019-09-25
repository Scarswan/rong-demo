package com.demo.p2;

import com.demo.fallBack.Test2ClientFallBack;
import com.demo.feign.Test2Client;
import com.demo.p2.mq.consumer.IMqConsumer;
import com.demo.p2.mq.consumer.RocketMQConsumerA;
import com.demo.p2.mq.producer.IMqProducer;
import com.demo.p2.mq.producer.RocketMQProducer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication // base sage 事务需要加上这个(exclude = JtaAutoConfiguration.class)
@EnableEurekaClient
@EnableTransactionManagement
@MapperScan("com.demo.p2.mapper")
@ComponentScan({"com.demo.feign", "com.demo.p2"})
@Configuration
public class P2Application {

    public static void main(String[] args) {
        SpringApplication.run(P2Application.class, args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();

        DataSize maxFileSize = DataSize.ofMegabytes(500);
        factory.setMaxFileSize(maxFileSize);

        DataSize maxRequestSize = DataSize.ofMegabytes(500);
        factory.setMaxRequestSize(maxRequestSize);

        return factory.createMultipartConfig();
    }

    @Bean
    public Test2Client test2Client() {
        return new Test2ClientFallBack();
    }

    @Bean
    public IMqProducer iMqProducer() {
        return new RocketMQProducer();
    }

    @Bean
    public IMqConsumer iMqConsumer() {
        return new RocketMQConsumerA();
    }

}
