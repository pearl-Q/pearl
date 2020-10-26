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
import java.util.List;

/**
 * @author 白世鑫
 * @title: BookServlet
 * @projectName Tomcat
 * @description:
 * @date 2020/9/2  2:16 下午
 */
public class BookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.查询所有图书信息
        List<Book> books = bookService.queryBooks();
        //2.将查询到的所有图书存到 request 域中
        request.setAttribute("books", books);
        //3.跳转到 图书列表页面   /pages/manager/book_manager.jsp
        //http://localhost:8080/book
        //http://localhost:8080
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取所有请求参数  封装 JavaBean
        Book book = WebUtils.copyParamToBean(request, new Book());

        int pageTotal = WebUtils.parseInt(request.getParameter("pageTotal"), 0);
        pageTotal+=1;

        //2.调用service保存图书
        bookService.addBook(book);

        //3.跳转到图书列表页面
//        request.getRequestDispatcher("/manager/bookServlet?action=list").forward(request,response);
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageTotal);

    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数,封装JavaBean
        Book book = WebUtils.copyParamToBean(request, new Book());
        //2.调用 service 修改图书
        bookService.updateBook(book);
        //3.跳转到图书信息列表页面
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + request.getParameter("pageNo"));
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数  id
        String strId = request.getParameter("id");
        String pageNo = request.getParameter("pageNo");
        //2.调用service 删除图书
        bookService.deleteBookById(WebUtils.parseInt(strId, 0));
        //3.跳转到图书列表页面
        request.getRequestDispatcher("/manager/bookServlet?action=page&pageNo="+pageNo).forward(request, response);
    }

    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数 id
        String strId = request.getParameter("id");
        //2.根据id调用 service 查询图书信息
        Book book = bookService.queryBookById(WebUtils.parseInt(strId, 0));
        //3.将查询到的图书信息保存到 request 域中
        request.setAttribute("book", book);
        //4.跳转到图书修改页面  /pages/manager/book_edit.jsp
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
    }


    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数 pageNo  pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用 service 获取 page 对象
        Page<Book> page = bookService.page(pageNo, pageSize);

        page.setUrl("manager/bookServlet?action=page");

        //3.将查询到的 page 保存到 request 域中
        request.setAttribute("page",page);
        //4.跳转到图书列表页面  /pages/manager/book_manager.jsp
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    }



}
