package com.qidi.service;

import com.qidi.pojo.User;

/**
 * @author 白世鑫
 * @description
 * @date 2020/8/27
 */
public interface UserService {

    /**
     * 用户注册
     * @param user
     */
    public void regist(User user);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return 如果返回 User 说明登录成功,如果返回null说明登录失败
     */
    public User login(String username,String password);

    /**
     * 判断用户名是否存在
     * @param username
     * @return 如果返回 null 说明用户名可用
     */
    public boolean existsUsername(String username);
}
