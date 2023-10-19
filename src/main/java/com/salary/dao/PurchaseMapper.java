package com.salary.dao;

import com.salary.dto.PurchaseOrderDTO;
import com.salary.pojo.PurchaseOrder;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface PurchaseMapper {
    List<BigDecimal> selectAllPay(String employeeId);
    int insert(PurchaseOrder createdPurchaseOrder);
    List<PurchaseOrder> pagePurchaseOrderPrefixWithId(String id, long pageBegin, long pageSize);
    long countPrefixWithId(String id);
    PurchaseOrder selectPurchaseOrderById(String id);
    int updatePurchaseOrderById(PurchaseOrderDTO purchaseOrder);
    int deletePurchaseOrderById(String id);
}