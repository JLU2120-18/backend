package com.salary.controller;

import com.salary.common.Page;
import com.salary.dto.PurchaseOrderDTO;
import com.salary.service.PurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/napi/purchase")
public class PurchaseController {
    @Resource
    private PurchaseService purchaseService;

    /**
     * 创建采购订单
     * @param purchaseOrder
     * @return
     */
    @PostMapping("/create")
    public PurchaseOrder create(@RequestBody PurchaseOrderDTO purchaseOrder) {
        return purchaseService.createPurchaseOrder(purchaseOrder);
    }

    /**
     * 查询以id为前缀的采购工单信息分页
     * @param jwt
     * @param id
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/gets")
    public Page<PurchaseOrder> gets(@RequestParam("jwt") String jwt, @RequestParam("id") String id, @RequestParam("pageIndex") long pageIndex, @RequestParam("pageSize") long pageSize) {
        return purchaseService.getPurchaseOrders(jwt, id, pageIndex, pageSize);
    }

    /**
     * 查询单个采购工单信息
     * @param jwt
     * @param id
     * @return
     */
    @GetMapping("/get")
    public PurchaseOrder get(@RequestParam("jwt") String jwt, @RequestParam("id") String id) {
        return purchaseService.getPurchaseOrder(jwt, id);
    }

    /**
     * 更新采购工单信息
     * @param purchaseOrder
     */
    @PostMapping("/update")
    public void update(@RequestBody PurchaseOrderDTO purchaseOrder) {
        purchaseService.updatePurchaseOrder(purchaseOrder);
    }

    /**
     * 删除采购工单
     * @param purchaseOrder
     */
    @PostMapping("/delete")
    public void delete(@RequestBody PurchaseOrderDTO purchaseOrder) {
        purchaseService.deletePurchaseOrder(purchaseOrder);
    }
}
