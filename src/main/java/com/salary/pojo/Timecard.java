package com.salary.pojo;

import lombok.Data;

/**
 * 描述：
 */
@Data
public class Timecard {
    private String id;
    private String employeeId;
    private String isSave;
    private String startTime;
    private String endTime;
    private Integer duration;

    public Timecard() {
    }

    public Timecard(String id, String employeeId, String isSave, String startTime, String endTime, Integer duration) {
        this.id = id;
        this.employeeId = employeeId;
        this.isSave = isSave;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }
}
