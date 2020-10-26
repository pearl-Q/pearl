package com.qidi.web;

import com.qidi.pojo.Cart;
import com.qidi.pojo.User;
import com.qidi.service.OrderService;
import com.qidi.service.impl.OrderServiceImpl;
import com.qidi.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 白世鑫
 * @title: OrderServlet
 * @projectName Tomcat
 * @description:
 * @date 2020/9/9  3:13 下午
 */
public class OrderServlet extends BaseServlet{

    private OrderService orderService = new OrderServiceImpl();

    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("OrderServlet 在线程[" + Thread.currentThread().getName() + "]中");

        //先获取购物车
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        //再获取userId
        User login = (User) request.getSession().getAttribute("user");
        if (login == null) {
            //跳转到登陆页面
            request.getRequestDispatcher("pages/user/login.jsp").forward(request,response);
            return;
        }

        //调用 service 生成订单
        String orderId = orderService.saveOrder(cart, login.getId());

        //保存订单号
//        request.setAttribute("orderId",orderId);
        request.getSession().setAttribute("orderId",orderId);

        //跳转
//        request.getRequestDispatcher("pages/cart/checkout.jsp").forward(request,response);
        response.sendRedirect(request.getContextPath() + "/pages/cart/checkout.jsp");
    }
}









