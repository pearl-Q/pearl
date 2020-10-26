package com.qidi.dao.impl;

import com.qidi.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 白世鑫
 * @title: BaseDao
 * @projectName Tomcat
 * @description:
 * @date 2020/8/27  10:28 上午
 */
public abstract class BaseDao {

    private QueryRunner queryRunner = new QueryRunner();

    /**
     * 执行 inset update delete 语句的方法
     * @param sql       要执行的 sql 语句
     * @param args      要执行的 sql 语句的参数值
     * @return          受影响的行数
     */
    public int update(String sql,Object... args){

        System.out.println("BaseDao 在线程[" + Thread.currentThread().getName() + "]中");

        Connection conn = JdbcUtils.getConn();
        try {
            return queryRunner.update(conn, sql, args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }

    /**
     * 返回查询 JavaBean 的方法
     * @param type
     * @param sql       要执行的 sql 语句
     * @param args      执行的 sql 语句的参数值
     * @param <T>       返回 JavaBean 的泛型
     * @return          返回 null 说明查询失败
     */
    public <T>T queryForOne(Class<T> type,String sql,Object... args){
        Connection conn = JdbcUtils.getConn();
        try {
            return queryRunner.query(conn,sql,new BeanHandler<T>(type),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }

    /**
     * 返回多个 JavaBean 的方法
     * @param type
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T>List<T> queryForList(Class<T> type, String sql, Object... args){
        Connection conn = JdbcUtils.getConn();
        try {
            return queryRunner.query(conn,sql,new BeanListHandler<>(type),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }


    public Object queryForSingleValue(String sql,Object... args){
        Connection conn = JdbcUtils.getConn();

        try {
            return queryRunner.query(conn,sql,new ScalarHandler(),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }



}
