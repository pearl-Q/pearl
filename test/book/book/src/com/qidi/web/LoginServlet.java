package com.qidi.web;

import com.qidi.pojo.User;
import com.qidi.service.UserService;
import com.qidi.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User login = userService.login(username, password);

        if (login != null) {
            //登陆成功
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
        }else {
            //登陆失败
            System.out.println("用户名或密码错");
        }

    }
}
