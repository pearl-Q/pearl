package com.qidi.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

/**
 * @author 白世鑫
 * @title: JdbcUtils
 * @projectName Tomcat
 * @description:
 * @date 2020/8/27  9:42 上午
 */
public class JdbcUtils {

    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<>();

    static {

        try {
            Properties properties = new Properties();
            properties.load(JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从数据库连接池中获取数据库连接
     * @return
     */
    public static Connection getConn(){
        Connection conn = conns.get();
        if (conn == null) {
            try {
                conn = dataSource.getConnection();
                conns.set(conn);
                //设置事务手动提交
                conn.setAutoCommit(false);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 提交事务,并关闭释放连接
     */
    public static void commitAndClose(){
        Connection conn = conns.get();
        if (conn != null) {
            try {
                //提交事务
                conn.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    //关闭连接
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        }
        conns.remove();
    }

    /**
     * 回滚事务,并关闭释放连接
     */
    public static void rollbackAndClose(){
        Connection conn = conns.get();
        if (conn != null) {
            try {
                //回滚事务
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    //关闭连接
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        }
        conns.remove();
    }


//    /**
//     * 关闭数据库连接
//     */
//    public static void close(Connection conn){
//        if (conn != null) {
//            try {
//                conn.close();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//    }

}
