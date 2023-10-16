package com.salary.pojo;

import lombok.Data;

import java.math.BigDecimal;

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
    private BigDecimal pay;

    public PurchaseOrder() {
    }

    public PurchaseOrder(String id, String employeeId, String productName, String phone, String address, String date, BigDecimal pay) {
        this.id = id;
        this.employeeId = employeeId;
        this.productName = productName;
        this.phone = phone;
        this.address = address;
        this.date = date;
        this.pay = pay;
    }
}
