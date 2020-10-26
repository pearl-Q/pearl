package com.qidi.service;

import com.qidi.pojo.Cart;

/**
 * @author 白世鑫
 * @description
 * @date 2020/9/9
 */
public interface OrderService {

    public String saveOrder(Cart cart,Integer userId);

}
