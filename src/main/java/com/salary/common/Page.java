package com.salary.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jialin
 * @create 2023-10-18 19:23
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    protected List<T> data;
    protected Long total;
    protected Long size;
    protected Long current;
}
