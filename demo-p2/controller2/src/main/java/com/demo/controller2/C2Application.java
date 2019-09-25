package com.demo.controller2;

import com.dmeo.p2.service2.Impl.Test2ServiceImpl;
import com.dmeo.p2.service2.Test2Service;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication //(exclude = JtaAutoConfiguration.class)
@EnableEurekaClient
@EnableTransactionManagement
@MapperScan("com.demo.p2.mapper2")
@Configuration
public class C2Application {

    public static void main(String[] args) {
        SpringApplication.run(C2Application.class, args);
    }

    @Bean
    public Test2Service test2Service(){
        return new Test2ServiceImpl();
    }
}

