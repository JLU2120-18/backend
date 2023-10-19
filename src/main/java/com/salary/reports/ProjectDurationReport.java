package com.salary.reports;

import com.salary.pojo.TimeCardProjectData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDurationReport implements Serializable {
    private static final long serialVersionUID = 1L;
    private String employeeId;
    private String employeeName;
    private String startTime;
    private String endTime;
    private TimeCardProjectData[] data;

}

