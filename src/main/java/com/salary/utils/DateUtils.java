package com.salary.utils;

import com.salary.pojo.TimeCard;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 描述：
 */
public class DateUtils {

    public static String getYearInit(String d){
        return d.substring(0,4) + "-00-00 00:00:00";
    }

    public static int getMonths(String d){
        return Integer.parseInt(d.substring(5,7));
    }

    public static int getDays(List<TimeCard> timeCards,String startTime,String endTime){
        Set<String> set = new HashSet<>();
        // 定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 解析字符串为LocalDateTime对象
        LocalDateTime startDate = LocalDateTime.parse(startTime, formatter);
        LocalDateTime endDate = LocalDateTime.parse(endTime, formatter);

        // 计算天数差
        Duration duration = Duration.between(startDate, endDate);
        long days = duration.toDays();
        for (TimeCard timeCard : timeCards){
            String s = timeCard.getStartTime().substring(0,11);
            set.add(s);
        }

        return (int)(days - set.size() * 7);
    }
}
