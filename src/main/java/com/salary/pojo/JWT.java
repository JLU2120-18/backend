package com.salary.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jialin
 * @create 2023-10-16 13:00
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JWT {
    private String id;
    private String role;

}
