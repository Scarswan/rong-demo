package com.demo.model.rocketmq;

import lombok.Data;

import java.util.Date;

@Data
public class RMQConsumer {

    private int cID;

    private int cMID;

    private int cCount;

    private int cMaxCount;

    private String cStatus;

    private String transactionID;

    private String mTopic;

    private String mConsumerGroup;

    private String mTags;

    private int isDelete;

    private Date createTime;

    private Date updateTime;

}
