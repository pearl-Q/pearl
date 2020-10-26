package com.qidi.dao.impl;

import com.qidi.dao.OrderItemDao;
import com.qidi.pojo.OrderItem;

/**
 * @author 白世鑫
 * @title: OrderItemDaoImpl
 * @projectName Tomcat
 * @description:
 * @date 2020/9/9  10:22 上午
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {

        System.out.println("OrderItemDaoImpl 在线程[" + Thread.currentThread().getName() + "]中");

        String sql = "insert into t_order_item(name,count,price,total_price,order_id) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }
}
