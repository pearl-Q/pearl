package com.qidi.web;

import com.qidi.pojo.User;
import com.qidi.service.UserService;
import com.qidi.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.先获取请求的参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");

        //2.调用 Service 层处理业务
        //判断验证码是否正确
        if("abcde".equalsIgnoreCase(code)){
            //  正确 判断用户名是否可用
            UserService userService = new UserServiceImpl();
            boolean existsUsername = userService.existsUsername(username);
            if(existsUsername){
                request.setAttribute("errorMsg","用户名已存在");
                request.setAttribute("username",username);
                request.setAttribute("email",email);

                //      不可用 回注册页面
                System.out.println("用户名[" + username + "]已存在");
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
            }else {
                //      可用  调用 service 保存用户
                userService.regist(new User(null,username,password,email));
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request,response);
            }

        }else {
            //保存错误信息到 request 域中
            request.setAttribute("errorMsg","验证码错误");
            request.setAttribute("username",username);
            request.setAttribute("email",email);


            //  不正确 跳转到注册页面
            System.out.println("验证码[" + code + "]错误");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
        }



        //3.根据Service层的结果对应处理
    }

}
