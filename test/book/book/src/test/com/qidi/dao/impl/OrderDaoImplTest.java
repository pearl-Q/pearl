package test.com.qidi.dao.impl;

import com.qidi.dao.OrderDao;
import com.qidi.dao.impl.OrderDaoImpl;
import com.qidi.pojo.Order;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.math.BigDecimal;
import java.util.Date;

/**
 * OrderDaoImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>9月 9, 2020</pre>
 */
public class OrderDaoImplTest {


    /**
     * Method: saveOrder(Order order)
     */
    @Test
    public void testSaveOrder() throws Exception {
        OrderDao orderDao = new OrderDaoImpl();
        orderDao.saveOrder(new Order("1234567890",new Date(),new BigDecimal(100),0,1));
    }


} 
