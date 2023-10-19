package com.salary.form;

import com.salary.pojo.TimeCardProjectData;
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
public class TimeCardUpdateForm {
    private String jwt;
    private String id;
    private List<TimeCardProjectData> data;
}
