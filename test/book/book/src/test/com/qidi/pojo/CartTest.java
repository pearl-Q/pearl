package test.com.qidi.pojo;

import com.qidi.pojo.Cart;
import com.qidi.pojo.CartItem;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.math.BigDecimal;

/**
 * Cart Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>9月 8, 2020</pre>
 */
public class CartTest {


    /**
     * Method: addItem(CartItem cartItem)
     */
    @Test
    public void testAddItem() throws Exception {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"金瓶梅",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(1,"金瓶梅",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"金瓶梅",1,new BigDecimal(1000),new BigDecimal(1000)));

        System.out.println(cart);
    }

    /**
     * Method: deleteItem(Integer id)
     */
    @Test
    public void testDeleteItem() throws Exception {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"金瓶梅",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(1,"金瓶梅",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"肉蒲团",1,new BigDecimal(1000),new BigDecimal(1000)));

        cart.deleteItem(1);

        System.out.println(cart);
    }

    /**
     * Method: clear()
     */
    @Test
    public void testClear() throws Exception {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"金瓶梅",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(1,"金瓶梅",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"肉蒲团",1,new BigDecimal(1000),new BigDecimal(1000)));

        cart.deleteItem(1);

        cart.clear();

        System.out.println(cart);
    }

    /**
     * Method: updateCount(Integer id, Integer count)
     */
    @Test
    public void testUpdateCount() throws Exception {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"金瓶梅",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(1,"金瓶梅",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"肉蒲团",1,new BigDecimal(1000),new BigDecimal(1000)));

        cart.deleteItem(1);

        cart.clear();

        cart.addItem(new CartItem(1,"金瓶梅",1,new BigDecimal(100),new BigDecimal(100)));
        cart.updateCount(1,10);

        System.out.println(cart);
    }


} 
