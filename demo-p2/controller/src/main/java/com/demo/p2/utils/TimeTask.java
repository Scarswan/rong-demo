package com.demo.p2.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@EnableScheduling
public class TimeTask {

    private static Logger logger = LoggerFactory.getLogger(TimeTask.class);

    @Scheduled(cron = "0 0 * * * ?")   //每小时执行一次
    public void test() {
        System.err.println("*********   定时任务执行   **************");
        CopyOnWriteArraySet<MyWebSocket> webSocketSet = MyWebSocket.getWebSocketSet();
        int i = 0;
        try {
            MyWebSocket.sendMessageTo("  给id为1的定时发送  " + new Date().toLocaleString(), "1");
            webSocketSet.forEach(c -> {
                try {
                    c.sendMessage("  定时发送  " + new Date().toLocaleString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.err.println("/n 定时任务完成.......");
    }

}
