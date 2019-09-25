package com.demo.p2.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author peishuaihui
 * @data 2018/12/14  2:21
 */
public class MqTimeUtil {


    public static String getDate() {
        return getDate(DateFormatType.YYYY_MM_DD_HH_MM_SS.getValue());
    }

    public static String getDate(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date());
    }
}
