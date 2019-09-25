package com.demo.p2.controller;

import com.demo.model.JsonResult;
import com.demo.p2.mq.consumer.IMqConsumer;
import com.demo.p2.mq.producer.IMqProducer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/rocketmq")
public class RocketMQController {

    @Autowired
    private IMqProducer iMqProducer;

    @Autowired
    private IMqConsumer iMqConsumer;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public JsonResult send(@RequestBody Map<String, String> map) {
        JsonResult jr = new JsonResult();

        try {
            iMqProducer.start(map.get("producerGroup"));

            SendResult result = iMqProducer.sendMsg(map.get("topic"), map.get("msg"));
            iMqProducer.shutDown();

            jr.setStateCode(1);
            jr.setData(result);
        } catch (MQClientException e) {
            jr.setStateCode(2);
            e.printStackTrace();
        }
        return jr;
    }

    @RequestMapping(value = "/consumer", method = RequestMethod.POST)
    public JsonResult consumer(@RequestBody Map<String, String> map) {
        JsonResult jr = new JsonResult();

        try {
            iMqConsumer.start(map.get("topic"), map.get("consumerGroup"));
            iMqConsumer.shutDown();

            jr.setStateCode(1);
        } catch (Exception e) {
            jr.setStateCode(2);
            e.printStackTrace();
        }

        return jr;
    }


}
