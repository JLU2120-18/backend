package com.salary.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 描述：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeCardProjectData implements Serializable {
    private static final long serialVersionUID = 1L;
    private String projectName;
    private BigDecimal duration;
}
