package com.salary.dao;

import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

/**
 * 描述：
 */
@Mapper
public interface PurchaseMapper {
    List<BigDecimal> selectAllPay(String employeeId);
}
