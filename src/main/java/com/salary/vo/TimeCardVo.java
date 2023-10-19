package com.salary.vo;

import com.salary.pojo.TimeCard;
import com.salary.pojo.TimeCardProject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 描述：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeCardVo {
    private TimeCard timeCard;
    private List<TimeCardProject> data;
}
