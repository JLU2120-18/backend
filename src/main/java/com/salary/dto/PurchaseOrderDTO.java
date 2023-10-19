package com.salary.dto;

import com.salary.pojo.PurchaseOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderDTO extends PurchaseOrder {
    private String jwt;
}
