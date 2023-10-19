package com.salary.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
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
    private Integer  durationLimit; // 0 代表可以加班
    private String type;    //'salary' | 'commission' | 'wage'
    private String payment;   //'mail' | 'receive' | 'bank'
    private String  mailAddress;
    private String  bankName;
    private String bankAccount;

}
