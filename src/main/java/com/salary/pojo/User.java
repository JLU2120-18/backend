package com.salary.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 描述：
 */
@Data
public class User {
    private String id;
    private String username;
    private String address;
    private String socsecId;
    private BigDecimal taxRate;
    private BigDecimal otherCast;
    private String phone;
    private BigDecimal hourWage;
    private BigDecimal  salary;
    private BigDecimal  commissionRate;
    private BigDecimal  durationLimit; // 0 代表可以加班
    private String type;    //'salary' | 'commission' | 'wage'
    private String payment;   //'mail' | 'receive' | 'bank'
    private String  mailAddress;
    private String  bankName;
    private String bankAccount;

    public User() {
    }

    public User(String id, String username, String address, String socsecId, BigDecimal taxRate, BigDecimal otherCast, String phone, BigDecimal hourWage, BigDecimal salary, BigDecimal commissionRate, BigDecimal durationLimit, String type) {
        this.id = id;
        this.username = username;
        this.address = address;
        this.socsecId = socsecId;
        this.taxRate = taxRate;
        this.otherCast = otherCast;
        this.phone = phone;
        this.hourWage = hourWage;
        this.salary = salary;
        this.commissionRate = commissionRate;
        this.durationLimit = durationLimit;
        this.type = type;
    }

    public User(String id, String payment, String mailAddress, String bankName, String bankAccount) {
        this.id = id;
        this.payment = payment;
        this.mailAddress = mailAddress;
        this.bankName = bankName;
        this.bankAccount = bankAccount;
    }
}
