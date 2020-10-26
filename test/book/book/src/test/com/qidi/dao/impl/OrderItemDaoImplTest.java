package test.com.qidi.dao.impl;

import com.qidi.dao.OrderItemDao;
import com.qidi.dao.impl.OrderItemDaoImpl;
import com.qidi.pojo.OrderItem;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.math.BigDecimal;

/**
 * OrderItemDaoImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>9月 9, 2020</pre>
 */
public class OrderItemDaoImplTest {

    /**
     * Method: saveOrderItem(OrderItem orderItem)
     */
    @Test
    public void testSaveOrderItem() throws Exception {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        orderItemDao.saveOrderItem(new OrderItem(null,"钢铁是怎样炼成的",2,new BigDecimal(50),new BigDecimal(100),"1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(null,"金鳞岂是池中物",1,new BigDecimal(98),new BigDecimal(50),"1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(null,"一遇风云变化龙",1,new BigDecimal(100),new BigDecimal(100),"1234567890"));
    }


} 
