package com.demo.p2.utils;

/**
 * @author peishuaihui
 * @data 2018/12/14  13:01   消费状态补充
 */
public enum ConsumeStatusAppend {

    //消费成功
    CONSUME_SUCCESS,
    //过一段时间再次投递该消息
    RECONSUME_LATER,
    //已消费过 放弃消费
    CONSUME_CONSUMED,
    //消费次数达到次数上限 放弃消费
    CONSUME_REACH_MAX;

    private ConsumeStatusAppend() {
    }
}
