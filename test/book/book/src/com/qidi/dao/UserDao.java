package com.qidi.dao;

import com.qidi.pojo.User;

/**
 * @author 白世鑫
 * @description
 * @date 2020/8/27
 */
public interface UserDao {

    /**
     * 通过用户名查询用户信息
     * @param username
     * @return 如果正常返回,说明用户名已存在,如果返回null,说明用户名可用.
     */
    public User queryByUsername(String username);

    /**
     * 通过用户名和密码查询用户信息
     * @param username
     * @param password
     * @return  如果正常返回,说明可以登录,否则登录失败.
     */
    public User queryByUsernameAndPassword(String username,String password);

    /**
     * 保存用户信息
     * @param user
     * @return  如果返回-1说明保存失败
     */
    public int saveUser(User user);

}
