package com.salary.service;

import com.salary.common.Page;
import com.salary.pojo.TimeCard;
import com.salary.pojo.TimeCardProjectData;
import com.salary.vo.TimeCardVo;

import java.util.List;

/**
 * 描述：
 */
public interface TimeCardService {
    Page<TimeCardVo> getTimeCard(String employeeId, Long pageIndex, Long pageSize);
    Page<TimeCard> getAvailableTimeCard(String employeeId);

    void updateTimeCard(String employeeId,String id, List<TimeCardProjectData> data);
}
