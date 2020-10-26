package com.qidi.web;

import com.google.gson.Gson;
import com.qidi.pojo.Book;
import com.qidi.pojo.Cart;
import com.qidi.pojo.CartItem;
import com.qidi.service.BookService;
import com.qidi.service.impl.BookServiceImpl;
import com.qidi.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 白世鑫
 * @title: CartServlet
 * @projectName Tomcat
 * @description:
 * @date 2020/9/8  2:40 下午
 */
public class CartServlet extends BaseServlet {

    BookService bookService = new BookServiceImpl();

    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数 id  count
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        int count = WebUtils.parseInt(request.getParameter("count"), 1);

        //先后去购物车
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
            cart.updateCount(id, count);
            //重定向到原来的页面
            response.sendRedirect(request.getHeader("Referer"));
        }


    }

    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
            cart.clear();
            request.getSession().setAttribute("cart", cart);
            //重定向到原来的页面
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数  id
        int id = WebUtils.parseInt(request.getParameter("id"), 0);

        //先获取购物车
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
            cart.deleteItem(id);
            request.getSession().setAttribute("cart", cart);
            //重定向到原来的页面
            response.sendRedirect(request.getHeader("Referer"));
        }

    }

    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数 商品ID
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        //调用 Service 查询图书信息
        Book book = bookService.queryBookById(id);
        //将 book 转换为 CartItem
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //将 CartItem 添加到购物车对象中
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }

        cart.addItem(cartItem);

        //记录最后一次添加的书名
        request.getSession().setAttribute("lastName", cartItem.getName());

        //重定向
//        response.sendRedirect("index.jsp");
        //重定向到原来的页面
        response.sendRedirect(request.getHeader("Referer"));

    }

    protected void ajaxAddItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数 商品ID
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        //调用 Service 查询图书信息
        Book book = bookService.queryBookById(id);
        //将 book 转换为 CartItem
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //将 CartItem 添加到购物车对象中
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }

        cart.addItem(cartItem);

        //记录最后一次添加的书名
        request.getSession().setAttribute("lastName", cartItem.getName());

        Map<String,Object> map = new HashMap<>();
        map.put("totalCount",cart.getTotalCount());
        map.put("lastName",cartItem.getName());

        Gson gson = new Gson();
        String result = gson.toJson(map);

        response.getWriter().write(result);


    }
}
