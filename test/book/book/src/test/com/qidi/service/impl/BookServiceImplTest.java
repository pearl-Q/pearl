package test.com.qidi.service.impl;

import com.qidi.pojo.Book;
import com.qidi.pojo.Page;
import com.qidi.service.BookService;
import com.qidi.service.impl.BookServiceImpl;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.math.BigDecimal;
import java.util.List;

/**
 * BookServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>9月 2, 2020</pre>
 */
public class BookServiceImplTest {

    private BookService bookService = new BookServiceImpl();

    /**
     * Method: addBook(Book book)
     */
    @Test
    public void testAddBook() throws Exception {
        Book book = new Book(null,"那小子真帅",new BigDecimal(89),"小红",1,999999,null);
        bookService.addBook(book);
    }

    /**
     * Method: updateBook(Book book)
     */
    @Test
    public void testUpdateBook() throws Exception {
        Book book = new Book(26,"那姑娘真美",new BigDecimal(89),"婷婷",10,9999999,null);
        bookService.updateBook(book);
    }

    /**
     * Method: deleteBookById(Integer id)
     */
    @Test
    public void testDeleteBookById() throws Exception {
        bookService.deleteBookById(26);
    }

    /**
     * Method: queryBookById(Integer id)
     */
    @Test
    public void testQueryBookById() throws Exception {
        System.out.println(bookService.queryBookById(26));
    }

    /**
     * Method: queryBooks()
     */
    @Test
    public void testQueryBooks() throws Exception {
        List<Book> books = bookService.queryBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void testPageByPrice() throws Exception {
        Page<Book> page = bookService.pageByPrice(0, 4, 10, 50);
        System.out.println(page);
    }

} 
