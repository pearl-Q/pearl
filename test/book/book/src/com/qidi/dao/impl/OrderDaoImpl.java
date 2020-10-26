package com.qidi.dao.impl;

import com.qidi.dao.OrderDao;
import com.qidi.pojo.Order;

/**
 * @author 白世鑫
 * @title: OrderDaoImpl
 * @projectName Tomcat
 * @description:
 * @date 2020/9/9  10:19 上午
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {

        System.out.println("OrderDaoImpl 在线程[" + Thread.currentThread().getName() + "]中");

        String sql = "insert into t_order(order_id,create_time,price,status,user_id) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateDate(),order.getPrice(),order.getStatus(),order.getUserId());
    }
}
