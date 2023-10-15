package com.salary.pojo;

import lombok.Data;

/**
 * 描述：
 */
@Data
public class PurchaseOrder {
    private String id;
    private String employeeId;
    private String productName;
    private String phone;
    private String address;
    private String date;

    public PurchaseOrder() {
    }

    public PurchaseOrder(String employeeId, String productName, String phone, String address, String date) {
        this.employeeId = employeeId;
        this.productName = productName;
        this.phone = phone;
        this.address = address;
        this.date = date;
    }
}
