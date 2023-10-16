package com.salary.dao;

import com.salary.pojo.Timecard;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 描述：薪资管理员dao层
 *
 */
@Mapper
public interface AdminReportMapper {
    List<Timecard> selectTimeCard(String employeeId);

    String selectName(String employeeId);
}
