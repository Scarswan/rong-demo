package com.demo.model.rocketmq;

import lombok.Data;

import java.util.Date;

@Data
public class RMQMessage {

    private int mID;

    private String mTopic;

    private String mTags;

    private String mKeys;

    private String mBody;

    private String mStatus;

    private String mProducerGroup;

    private int sendCount;

    private String sendResult;

    private int maxRetryCount;

    private String transactionID;

    private int isTransaction;

    private int isDelete;

    private Date createTime;

    private Date updateTime;

}
