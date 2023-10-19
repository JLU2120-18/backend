package com.salary.service;

import com.salary.common.Page;
import com.salary.dto.PurchaseOrderDTO;
import com.salary.pojo.PurchaseOrder;

public interface PurchaseService {
    PurchaseOrder createPurchaseOrder(PurchaseOrderDTO purchaseOrder);
    Page<PurchaseOrder> getPurchaseOrders(String jwt, String id, long pageIndex, long pageSize);
    PurchaseOrder getPurchaseOrder(String jwt, String id);
    void updatePurchaseOrder(PurchaseOrderDTO purchaseOrder);
    void deletePurchaseOrder(PurchaseOrderDTO purchaseOrder);
}
