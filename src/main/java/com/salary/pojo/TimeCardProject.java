package com.salary.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Jialin
 * @create 2023-10-17 13:59
 */

@Data
public class TimeCardProject {
    private String id;
    private String projectName;
    private BigDecimal duration;

    public TimeCardProject() {
    }

    public TimeCardProject(String id, String projectName, BigDecimal duration) {
        this.id = id;
        this.projectName = projectName;
        this.duration = duration;
    }
}
