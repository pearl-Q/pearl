package com.qidi.dao;

import com.qidi.pojo.Book;

import java.util.List;

/**
 * @author 白世鑫
 * @description
 * @date 2020/9/2
 */
public interface BookDao {

    public int addBook(Book book);

    public int updateBook(Book book);

    public int deleteBookById(Integer id);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    public Integer queryForPageTotalCount();

    public List<Book> queryItems(int begin,int pageSize);

    public Integer queryForPageTotalCountByPrice(int min,int max);

    public List<Book> queryItemsByPrice(int begin,int pageSize,int min,int max);

}
