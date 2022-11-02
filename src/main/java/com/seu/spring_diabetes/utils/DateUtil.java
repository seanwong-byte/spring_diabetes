package com.seu.spring_diabetes.utils;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/***
 * 时间格式化工具类
 */

public class DateUtil {

    public static final String STANDARD="yyyy-MM-dd HH:mm:ss";
    //将字符串类型的时间转成Date类型
    public static Date string2Date(String strDate){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD);
        DateTime dateTime = dateTimeFormatter.parseDateTime(strDate);
        return  dateTime.toDate();
    }


    //将Date类型时间转成字符串时间
    public static String date2String(Date date){
        if(date == null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime=new DateTime();
        return dateTime.toString(STANDARD);
    }




}
