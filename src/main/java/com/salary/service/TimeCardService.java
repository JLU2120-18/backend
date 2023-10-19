package com.salary.service;

import com.salary.pojo.Page;
import com.salary.pojo.TimeCard;
import com.salary.pojo.TimeCardProjectData;

import java.util.List;

/**
 * 描述：
 */
public interface TimeCardService {
    Page<TimeCard> getTimeCard(String employeeId, Long pageIndex, Long pageSize);
    Page<TimeCard> getAvailableTimeCard(String employeeId);

    void updateTimeCard(String employeeId,String id, List<TimeCardProjectData> data);
}
