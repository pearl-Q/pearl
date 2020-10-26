package com.qidi.web;

import com.google.gson.Gson;
import com.qidi.pojo.User;
import com.qidi.service.UserService;
import com.qidi.service.impl.UserServiceImpl;
import com.qidi.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    UserService userService = new UserServiceImpl();

    protected void ajaxExistsUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求的参数
        String username = request.getParameter("username");
        // 判断用户名是否存在
        boolean existsUsername = userService.existsUsername(username);

        // 将查询到的结果封装到 Map 中
        Map<String,Object> map = new HashMap<>();
        map.put("existsUsername",existsUsername);

        Gson gson = new Gson();
        String mapJsonString = gson.toJson(map);

        response.getWriter().write(mapJsonString);

    }
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User login = userService.login(username, password);

        if (login != null) {
            //登陆成功

            //登陆成功,将用户名保存到
            HttpSession session = request.getSession();
            session.setAttribute("user",login);

            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
        } else {
            //将错误信息保存到 request 域中
            request.setAttribute("errorMsg","用户名或密码错误!");

            //登陆失败
            System.out.println("用户名或密码错");

            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }
    }

    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取服务器端的验证码
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //立即销毁验证码
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);


        //1.先获取请求的参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");




        try {

            User user = WebUtils.copyParamToBean(request,new User());
            System.out.println("注入之后:" + user);

        } catch (Exception e) {
            e.printStackTrace();
        }


        //2.调用 Service 层处理业务
        //判断验证码是否正确
        if (token.equalsIgnoreCase(code)) {
            //  正确 判断用户名是否可用
            UserService userService = new UserServiceImpl();
            boolean existsUsername = userService.existsUsername(username);
            if (existsUsername) {
                request.setAttribute("errorMsg","用户名已存在");
                request.setAttribute("username",username);
                request.setAttribute("email",email);

                //      不可用 调回注册页面
                System.out.println("用户名[" + username + "]已存在");
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            } else {
                //      可用  调用 service 保存用户
                userService.regist(new User(null, username, password, email));
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
            }

        } else {

            request.setAttribute("errorMsg","验证码错误");
            request.setAttribute("username",username);
            request.setAttribute("email",email);

            //  不正确 跳转到注册页面
            System.out.println("验证码[" + code + "]错误");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }
    }

}
