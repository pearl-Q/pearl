package test.com.qidi.service.impl;

import com.qidi.pojo.Cart;
import com.qidi.pojo.CartItem;
import com.qidi.service.OrderService;
import com.qidi.service.impl.OrderServiceImpl;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.math.BigDecimal;

/**
 * OrderServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>9月 9, 2020</pre>
 */
public class OrderServiceImplTest {

    /**
     * Method: saveOrder(Cart cart, Integer userId)
     */
    @Test
    public void testSaveOrder() throws Exception {
        OrderService orderService = new OrderServiceImpl();

        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"天才在左疯子在右",1,new BigDecimal(200),new BigDecimal(200)));
        cart.addItem(new CartItem(1,"金瓶梅",3,new BigDecimal(100),new BigDecimal(300)));
        cart.addItem(new CartItem(2,"肉蒲团",1,new BigDecimal(1000),new BigDecimal(1000)));

        String orderId = orderService.saveOrder(cart, 1);
        System.out.println(orderId);


    }


} 
