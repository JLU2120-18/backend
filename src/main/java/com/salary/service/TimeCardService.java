package com.salary.service;

import com.salary.common.Page;
import com.salary.pojo.TimeCard;
import com.salary.pojo.TimeCardProjectData;

import java.util.List;

public interface TimeCardService {
    Page<TimeCard> getTimeCard(String employeeId, Long pageIndex, Long pageSize);
    Page<TimeCard> getAvailableTimeCard(String employeeId);

    void updateTimeCard(String employeeId,String id, List<TimeCardProjectData> data);
}
