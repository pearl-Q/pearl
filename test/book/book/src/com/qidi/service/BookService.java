package com.qidi.service;

import com.qidi.pojo.Book;
import com.qidi.pojo.Page;

import java.util.List;

/**
 * @author 白世鑫
 * @description
 * @date 2020/9/2
 */
public interface BookService {

    public int addBook(Book book);

    public int updateBook(Book book);

    public int deleteBookById(Integer id);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    public Page<Book> page(int pageNo,int pageSize);

    public Page<Book> pageByPrice(int pageNo,int pageSize,int min,int max);

}
