package com.salary.service.impl;

import com.salary.common.Page;
import com.salary.dao.AuthMapper;
import com.salary.dao.PurchaseMapper;
import com.salary.dto.PurchaseOrderDTO;
import com.salary.pojo.PurchaseOrder;
import com.salary.service.PurchaseService;
import com.salary.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Resource
    private PurchaseMapper purchaseMapper;
    @Resource
    private AuthMapper authMapper;

    /**
     * 判断是否有权限访问
     *
     * @param jwt
     * @return
     */
    private String hasPermissionToAccess(String jwt, String role) {
        // 1.解析jwt
        Claims claims = JwtUtils.parseToken(jwt);
        String userId = claims.get("id").toString();
        String userRole = claims.get("role").toString();

        // 2.判断当前操作用户权限是否为role
        if (userRole == null || !userRole.equals(role)) {
            return null;
        }

        // 3.查询数据库中的role和传过来的role是否一致
        String selectedRole = authMapper.selectRoleById(userId);
        if (selectedRole == null || !selectedRole.equals(userRole)) {
            return null;
        }

        return userId;
    }

    /**
     * 创建采购订单
     *
     * @param purchaseOrder
     * @return
     */
    @Override
    public PurchaseOrder createPurchaseOrder(PurchaseOrderDTO purchaseOrder) {
        // 1.获取jwt并解析权限
        String jwt = purchaseOrder.getJwt();
        String userId = hasPermissionToAccess(jwt, "commission");
        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 2.当前操作用户具有权限, 获取其他属性
        String phone = purchaseOrder.getPhone();
        String address = purchaseOrder.getAddress();
        String productName = purchaseOrder.getProductName();
        String date = purchaseOrder.getDate();
        BigDecimal pay = purchaseOrder.getPay();

        // 3.插入新的purchaseOrder
        PurchaseOrder createdPurchaseOrder = new PurchaseOrder();
        String id = "order_" + userId + "_" + System.currentTimeMillis();
        createdPurchaseOrder.setId(id);
        createdPurchaseOrder.setEmployeeId(userId);
        createdPurchaseOrder.setPhone(phone);
        createdPurchaseOrder.setAddress(address);
        createdPurchaseOrder.setProductName(productName);
        createdPurchaseOrder.setDate(date);
        createdPurchaseOrder.setPay(pay);
        int success = purchaseMapper.insert(createdPurchaseOrder);

        // 6.插入失败, 抛出异常
        if (success < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return createdPurchaseOrder;
    }

    /**
     * 查询以id为前缀的采购工单信息分页
     *
     * @param jwt
     * @param id
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public Page<PurchaseOrder> getPurchaseOrders(String jwt, String id, long pageIndex, long pageSize) {
        // 1.判断是否有权限
        if (hasPermissionToAccess(jwt, "commission") == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 2.分页查询以id为前缀的采购工单信息
        pageIndex = (pageIndex > 0) ? pageIndex : 1L;
        pageSize = (pageSize > 0) ? pageSize : 10L;
        Page<PurchaseOrder> page = new Page<>();
        long total = purchaseMapper.countPrefixWithId(id);
        long pageEnd = (total % pageSize == 0) ? (total / pageSize) : (total / pageSize + 1);
        pageIndex = Math.min(pageIndex, pageEnd);
        page.setData(purchaseMapper.pagePurchaseOrderPrefixWithId(id, (pageIndex - 1) * pageSize, pageSize));
        page.setTotal(total);
        page.setCurrent(pageIndex);
        page.setSize(pageSize);
        return page;
    }

    /**
     * 查询单个采购工单信息
     *
     * @param jwt
     * @param id
     * @return
     */
    @Override
    public PurchaseOrder getPurchaseOrder(String jwt, String id) {
        // 1.判断是否有权限
        if (hasPermissionToAccess(jwt, "commission") == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 2.查询采购工单信息
        return purchaseMapper.selectPurchaseOrderById(id);
    }

    /**
     * 更新采购工单信息
     *
     * @param purchaseOrder
     */
    @Override
    public void updatePurchaseOrder(PurchaseOrderDTO purchaseOrder) {
        // 1.获取jwt并解析, 判断是否有权限
        String jwt = purchaseOrder.getJwt();
        if (hasPermissionToAccess(jwt, "commission") == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 2.判断id是否合法
        String id = purchaseOrder.getId();
        if(id == null || id.length() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 3.根据id更新采购工单信息
        int success = purchaseMapper.updatePurchaseOrderById(purchaseOrder);

        // 4.更新失败, 抛出异常
        if(success < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 删除采购工单
     *
     * @param purchaseOrder
     */
    @Override
    public void deletePurchaseOrder(PurchaseOrderDTO purchaseOrder) {
        // 1.获取jwt并解析, 判断是否有权限
        String jwt = purchaseOrder.getJwt();
        if (hasPermissionToAccess(jwt, "commission") == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 2.判断id是否合法
        String id = purchaseOrder.getId();
        if(id == null || id.length() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 3.根据id删除采购工单
        int success = purchaseMapper.deletePurchaseOrderById(id);

        // 4.删除失败, 抛出异常
        if (success < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
