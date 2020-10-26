package com.qidi.web;

import com.qidi.pojo.Book;
import com.qidi.pojo.Page;
import com.qidi.service.BookService;
import com.qidi.service.impl.BookServiceImpl;
import com.qidi.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 白世鑫
 * @title: ClientBookServlet
 * @projectName Tomcat
 * @description:
 * @date 2020/9/3  4:26 下午
 */
public class ClientBookServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数 pageNo  pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用 service 获取 page 对象
        Page<Book> page = bookService.page(pageNo, pageSize);

        page.setUrl("client/bookServlet?action=page");

        //3.将查询到的 page 保存到 request 域中
        request.setAttribute("page",page);
        //4.跳转到图书列表页面  /pages/manager/book_manager.jsp
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
    }

    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数 pageNo  pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);

        int min = WebUtils.parseInt(request.getParameter("min"), 0);
        int max = WebUtils.parseInt(request.getParameter("max"), Integer.MAX_VALUE);

        //2.调用 service 获取 page 对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize,min,max);

        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        //如果 request 中有最小价格就将最小价格追加到 地址请求的参数中.
        if(request.getParameter("min")!=null){
            sb.append("&min=").append(request.getParameter("min"));
        }
        //如果 request 中有最大价格就将最小价格追加到 地址请求的参数中.
        if(request.getParameter("max")!=null){
            sb.append("&max=").append(request.getParameter("max"));
        }

        page.setUrl(sb.toString());

        //3.将查询到的 page 保存到 request 域中
        request.setAttribute("page",page);
        //4.跳转到图书列表页面  /pages/manager/book_manager.jsp
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
    }

}
