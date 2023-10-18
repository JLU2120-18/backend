package com.salary.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {
    private String id;
    private String employeeId;
    private String productName;
    private String phone;
    private String address;
    private String date;
}
