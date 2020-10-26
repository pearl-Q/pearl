package test.com.qidi.service.impl;

import com.qidi.pojo.User;
import com.qidi.service.UserService;
import com.qidi.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * UserServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>8月 27, 2020</pre>
 */
public class UserServiceImplTest {


    /**
     * Method: regist(User user)
     */
    @Test
    public void testRegist() throws Exception {
        UserService userService = new UserServiceImpl();
        userService.regist(new User(null,"xiaohua","xiaohua","xiaohua@qq.com"));
    }

    /**
     * Method: login(String username, String password)
     */
    @Test
    public void testLogin() throws Exception {
        UserService userService = new UserServiceImpl();
        User user = userService.login("admin", "admin1");
        if(user==null){
            System.out.println("登陆失败");
        }else {
            System.out.println("登陆成功");
        }
    }

    /**
     * Method: existsUsername(String username)
     */
    @Test
    public void testExistsUsername() throws Exception {
        UserService userService = new UserServiceImpl();
        if(userService.existsUsername("admiralxb")){
            System.out.println("用户名已存在");
        }else {
            System.out.println("用户名可用");
        }
    }


} 
