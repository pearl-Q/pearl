package test.com.qidi.dao.impl;

import com.qidi.dao.UserDao;
import com.qidi.dao.impl.UserDaoImpl;
import com.qidi.pojo.User;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * UserDaoImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>8月 27, 2020</pre>
 */
public class UserDaoImplTest {


    /**
     * Method: queryByUsername(String username)
     */
    @Test
    public void testQueryByUsername() throws Exception {
        UserDao userDao = new UserDaoImpl();
        if(userDao.queryByUsername("admin111")!=null){
            System.out.println("用户名已存在");
        }else {
            System.out.println("用户名可用");
        }
    }

    /**
     * Method: queryByUsernameAndPassword(String username, String password)
     */
    @Test
    public void testQueryByUsernameAndPassword() throws Exception {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.queryByUsernameAndPassword("admin", "adssmin");
        if(user!=null){
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }
    }

    /**
     * Method: saveUser(User user)
     */
    @Test
    public void testSaveUser() throws Exception {
        UserDao userDao = new UserDaoImpl();
        int result = userDao.saveUser(new User(null, "admiral", "admiral", "admiral@163.com"));
        if(result!=-1){
            System.out.println("用户保存成功!");
        }else {
            System.out.println("用户保存失败!");
        }
    }


} 
