package com.salary.service.impl;

import com.salary.common.Page;
import com.salary.dao.TimeCardMapper;
import com.salary.dao.UserMapper;
import com.salary.pojo.TimeCard;
import com.salary.pojo.TimeCardProjectData;
import com.salary.service.TimeCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

/**
 * 描述：
 */
@Service
public class TimeCardServiceImpl implements TimeCardService {

    @Autowired
    TimeCardMapper timeCardMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public Page<TimeCard> getTimeCard(String employeeId, Long pageIndex, Long pageSize){
        //先判断有没有该周工时卡
        // 获取当前日期时间
        LocalDateTime currentDateTime = LocalDateTime.now();
        // 计算本周的起始时间和结束时间
        LocalDateTime startOfWeek = currentDateTime.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfWeek = currentDateTime.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).withHour(23).withMinute(59).withSecond(59);
        // 定义日期时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 格式化起始时间和结束时间
        String formattedStartOfWeek = startOfWeek.format(formatter);
        String formattedEndOfWeek = endOfWeek.format(formatter);
        //查询最后一个判断是否有该周的，且id用于计算需要插入的下一条数据的id
        TimeCard timeCard = timeCardMapper.selectLast();
        //没有就插入
        if (timeCard.getStartTime().compareTo(formattedStartOfWeek) < 0){
            //计算id
            String id = String.valueOf(Integer.parseInt(timeCard.getId()) + 1);
            timeCard = new TimeCard(id,employeeId,false,
                    formattedStartOfWeek,formattedEndOfWeek, BigDecimal.valueOf(0));
            timeCardMapper.insertTimeCard(timeCard);
        }
        pageIndex = pageIndex > 0 ? pageIndex : 1L;
        pageSize = pageSize > 0 ? pageSize : 10L;
        //根据用户id查询timeCard
        //查询总数
        long total = timeCardMapper.selectSumById(employeeId);
        long current = (total % pageSize == 0) ? total / pageSize : total / pageSize +1;
        current = Math.min(current,pageIndex);
        //查询该页数据
        long offset = (pageIndex - 1) * pageSize;
        List<TimeCard> timeCards = timeCardMapper.selectPageTimeCardById(employeeId);
        System.out.println(employeeId+" "+timeCards.size()+" "+offset);
        return new Page<>(timeCards,total,pageSize,current);

    }

    @Override
    public Page<TimeCard> getAvailableTimeCard(String employeeId){

        // 获取当前日期时间
        LocalDateTime currentDateTime = LocalDateTime.now();
        // 计算本周的起始时间和结束时间
        LocalDateTime startOfWeek = currentDateTime.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfWeek = currentDateTime.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).withHour(23).withMinute(59).withSecond(59);
        // 定义日期时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 格式化起始时间和结束时间
        String formattedStartOfWeek = startOfWeek.format(formatter);
        String formattedEndOfWeek = endOfWeek.format(formatter);
        //查询最后一个
        TimeCard timeCard = timeCardMapper.selectLast();
        //没有就插入
        if (timeCard.getStartTime().compareTo(formattedStartOfWeek) < 0){
            //计算id
            String id = String.valueOf(Integer.parseInt(timeCard.getId()) + 1);
            timeCard = new TimeCard(id,employeeId,false,
                    formattedStartOfWeek,formattedEndOfWeek, BigDecimal.valueOf(0));
            timeCardMapper.insertTimeCard(timeCard);
        }
        //根据用户id查询有效timeCard
        List<TimeCard> timeCards = timeCardMapper.selectAvailableTimeCardById(employeeId);
        return new Page<>(timeCards,null,null,null);

    }

    @Override
    public void updateTimeCard(String employeeId,String id, List<TimeCardProjectData> data){
        //查询对应timeCard
        TimeCard timeCard = timeCardMapper.selectTimeCardsById(id);
        //已保存不许更改，报错
        if (timeCard.getIsSave())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        //遍历计算duration
        BigDecimal duration = new BigDecimal(0);
        for(TimeCardProjectData timeCardProjectData : data){
            duration = duration.add(timeCardProjectData.getDuration());
        }
        duration = duration.add(timeCard.getDuration());
        //判断工时限制
        //查询用户工时
        int durationLimit = userMapper.selectUserById(employeeId).getDurationLimit();
        duration = BigDecimal.valueOf(Math.min(duration.intValue(),durationLimit));
        //更新
        timeCardMapper.updateTimeCard(id,duration,true);
        //插入timeCardProject
        timeCardMapper.insertTimeCardProject(id,data);
    }

}
