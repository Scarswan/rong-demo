package com.demo.p2.mq.consumer;

import com.demo.p2.annotation.ShardingSphereMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoConsumer implements CommandLineRunner {

    @Autowired
    private IMqConsumer iMqConsumer;

    @Override
    @ShardingSphereMaster
    public void run(String... args) throws Exception {
        iMqConsumer.start("demo-topic", "demo_consumer_group_rocketMQ");

        // iMqConsumer.shutDown();
    }
}
