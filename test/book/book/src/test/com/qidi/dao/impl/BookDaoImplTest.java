package test.com.qidi.dao.impl;

import com.qidi.dao.BookDao;
import com.qidi.dao.impl.BookDaoImpl;
import com.qidi.pojo.Book;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.math.BigDecimal;
import java.util.List;

/**
 * BookDaoImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>9月 2, 2020</pre>
 */
public class BookDaoImplTest {
    private BookDao bookDao = new BookDaoImpl();

    /**
     * Method: addBook(Book book)
     */
    @Test
    public void testAddBook() throws Exception {
        Book book = new Book(null,"玉蒲团",new BigDecimal(999),"白哥",99999,0,null);
        bookDao.addBook(book);
    }

    /**
     * Method: updateBook(Book book)
     */
    @Test
    public void testUpdateBook() throws Exception {
        Book book = new Book(25,"金瓶梅彩色高清插图版",new BigDecimal(99999),"崀哥",9999999,10000,null);
        bookDao.updateBook(book);
    }

    /**
     * Method: deleteBookById(Integer id)
     */
    @Test
    public void testDeleteBookById() throws Exception {
        bookDao.deleteBookById(25);
    }

    /**
     * Method: queryBookById(Integer id)
     */
    @Test
    public void testQueryBookById() throws Exception {
        System.out.println(bookDao.queryBookById(25));
    }

    /**
     * Method: queryBooks()
     */
    @Test
    public void testQueryBooks() throws Exception {
        List<Book> books = bookDao.queryBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void testQueryForPageTotalCountByPrice() throws Exception {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10, 50));
    }

    @Test
    public void testQueryForItemsByPrice(){
        List<Book> books = bookDao.queryItemsByPrice(0, 4, 10, 50);
        for (Book book : books) {
            System.out.println(book);
        }

    }

} 
