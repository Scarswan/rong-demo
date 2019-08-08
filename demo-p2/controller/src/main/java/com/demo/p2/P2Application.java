package com.demo.p2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@EnableEurekaClient
@EnableTransactionManagement
@MapperScan("com.demo.p2.mapper")
@Configuration
public class P2Application {

    public static void main(String[] args){
        SpringApplication.run(P2Application.class,args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();

        DataSize maxFileSize = DataSize.ofMegabytes(500);
        factory.setMaxFileSize(maxFileSize);

        DataSize maxRequestSize = DataSize.ofMegabytes(500);
        factory.setMaxRequestSize(maxRequestSize);

        return factory.createMultipartConfig();
    }

}
