package com.salary.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 描述：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String employeeId;
    private String productName;
    private String phone;
    private String address;
    private String date;
}
