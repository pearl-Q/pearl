package com.qidi.dao.impl;

import com.qidi.dao.UserDao;
import com.qidi.pojo.User;

/**
 * @author 白世鑫
 * @title: UserDaoImpl
 * @projectName Tomcat
 * @description:
 * @date 2020/8/27  10:46 上午
 */
public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public User queryByUsername(String username) {
        String sql = "select id,username,password,email from t_user where username=?";
        return queryForOne(User.class, sql, username);
    }

    @Override
    public User queryByUsernameAndPassword(String username, String password) {
        String sql = "select id,username,password,email from t_user where username=? and password=?";
        return queryForOne(User.class,sql,username,password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(username,password,email) values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
