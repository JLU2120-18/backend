package com.salary.pojo;

import lombok.Data;

/**
 * 描述：
 */
@Data
public class User {
    private String id;
    private String username;
    private String address;
    private String socsecId;
    private Integer taxRate;
    private Integer otherCast;
    private String phone;
    private Integer hourWage;
    private Integer  salary;
    private Integer  commissionRate;
    private Integer  durationLimit; // 0 代表可以加班
    private String type;    //'salary' | 'commission' | 'wage'
    private String payment;   //'mail' | 'receive' | 'bank'
    private String  mailAddress;
    private String  bankName;
    private String bankAccount;

    public User() {
    }

    public User(String id, String username, String address, String socsecId, Integer taxRate, Integer otherCast, String phone, Integer hourWage, Integer salary, Integer commissionRate, Integer durationLimit, String type) {
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
