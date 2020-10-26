package com.qidi.service.impl;

import com.qidi.dao.BookDao;
import com.qidi.dao.impl.BookDaoImpl;
import com.qidi.pojo.Book;
import com.qidi.pojo.Page;
import com.qidi.service.BookService;

import java.util.List;

/**
 * @author 白世鑫
 * @title: BookServiceImpl
 * @projectName Tomcat
 * @description:
 * @date 2020/9/2  2:09 下午
 */
public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public int addBook(Book book) {
        return bookDao.addBook(book);
    }

    @Override
    public int updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    @Override
    public int deleteBookById(Integer id) {
        return bookDao.deleteBookById(id);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();


        //设置每页显示的数量
        page.setPageSize(pageSize);

        //求得总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);


        //求得总页数
        int pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal++;
        }


        if (pageNo < 1) {
            pageNo = 1;
        }
        if (pageNo > pageTotal) {
            pageNo = pageTotal;
        }

        //设置当前页
        page.setPageNo(pageNo);


        //设置总页数
        page.setPageTotal(pageTotal);


        //查询获取每页需要显示的数据
        int begin = (pageNo - 1) * pageSize;
        List<Book> books = bookDao.queryItems(begin, pageSize);
        //设置每页需要显示的数据
        page.setItems(books);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<>();
        //设置每页显示的数量
        page.setPageSize(pageSize);
        //求得总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);
        //求得总页数
        int pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal++;
        }
        if (pageNo < 1) {
            pageNo = 1;
        }
        if (pageNo > pageTotal) {
            pageNo = pageTotal;
        }
        //设置当前页
        page.setPageNo(pageNo);
        //设置总页数
        page.setPageTotal(pageTotal);
        //查询获取每页需要显示的数据
        int begin = (pageNo - 1) * pageSize;
        List<Book> books = bookDao.queryItemsByPrice(begin, pageSize,min,max);
        //设置每页需要显示的数据
        page.setItems(books);
        return page;
    }
}
