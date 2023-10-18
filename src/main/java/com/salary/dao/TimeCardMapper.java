package com.salary.dao;

import com.salary.pojo.TimeCard;
import com.salary.pojo.TimeCardProject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 描述：
 */
@Mapper
public interface TimeCardMapper {

    List<TimeCard> selectTimeCardsById(String employeeId,String startTime,String endTime);

    List<TimeCardProject> selectProjByTimeCardId(String timeCardId);

    String selectEmployeeIdByTimeCardId(String timeCard);

//    TimeCard selectFirstById(String employeeId,String startTime,String endTime);
//    TimeCard selectLastById(String employeeId, String startTime, String endTime);

}
