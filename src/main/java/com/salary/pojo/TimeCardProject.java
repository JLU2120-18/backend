package com.salary.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Jialin
 * @create 2023-10-17 13:59
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeCardProject implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String projectName;
    private BigDecimal duration;
}
