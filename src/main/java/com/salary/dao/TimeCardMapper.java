package com.salary.dao;

import com.salary.pojo.TimeCard;
import com.salary.pojo.TimeCardProject;
import com.salary.pojo.TimeCardProjectData;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

/**
 * 描述：
 */
@Mapper
public interface TimeCardMapper {

    List<TimeCard> selectTimeCardsByEmployeeId(String employeeId, String startTime, String endTime);

    List<TimeCardProject> selectProjByTimeCardId(String timeCardId);

    String selectEmployeeIdByTimeCardId(String timeCard);

    Long selectSumById(String employeeId);

    List<TimeCard> selectPageTimeCardById(String employeeId);

    List<TimeCard> selectAvailableTimeCardById(String employeeId);

    Integer selectCntById(String id);

    void insertTimeCardProject(String id, List<TimeCardProjectData> data);

    TimeCard selectTimeCardsById(String id);

    void insertTimeCard(TimeCard timeCard);

    void updateTimeCard(String id,BigDecimal duration,Boolean isSave);
//    TimeCard selectFirstById(String employeeId,String startTime,String endTime);
    TimeCard selectLast();

}
