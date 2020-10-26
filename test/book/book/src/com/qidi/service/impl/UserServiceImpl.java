package com.qidi.service.impl;

import com.qidi.dao.UserDao;
import com.qidi.dao.impl.UserDaoImpl;
import com.qidi.pojo.User;
import com.qidi.service.UserService;

/**
 * @author 白世鑫
 * @title: UserServiceImpl
 * @projectName Tomcat
 * @description:
 * @date 2020/8/27  3:14 下午
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void regist(User user) {
        int result = userDao.saveUser(user);
    }

    @Override
    public User login(String username, String password) {
        return userDao.queryByUsernameAndPassword(username,password);
    }

    @Override
    public boolean existsUsername(String username) {
        if(userDao.queryByUsername(username)==null){
            //如果返回 null ,说明不存在.
            return false;
        }
        return true;
    }
}
