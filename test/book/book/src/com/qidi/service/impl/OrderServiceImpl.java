package com.qidi.service.impl;

import com.qidi.dao.BookDao;
import com.qidi.dao.OrderDao;
import com.qidi.dao.OrderItemDao;
import com.qidi.dao.impl.BookDaoImpl;
import com.qidi.dao.impl.OrderDaoImpl;
import com.qidi.dao.impl.OrderItemDaoImpl;
import com.qidi.pojo.*;
import com.qidi.service.OrderService;

import java.util.Date;

/**
 * @author 白世鑫
 * @title: OrderServiceImpl
 * @projectName Tomcat
 * @description:
 * @date 2020/9/9  10:31 上午
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String saveOrder(Cart cart, Integer userId) {

        System.out.println("OrderServiceImpl 在线程[" + Thread.currentThread().getName() + "]中");

        //调用 orderDao 保存订单
        String orderId = System.currentTimeMillis() + "" + userId;
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        orderDao.saveOrder(order);


//        int i = 10/0;


        //调用 orderItemDao 保存订单项
        for (CartItem cartItem : cart.getItems().values()) {
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            orderItemDao.saveOrderItem(orderItem);

            //更新图书库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            //更新
            bookDao.updateBook(book);
        }


        //清空购物车
        cart.clear();

        return orderId;
    }
}
