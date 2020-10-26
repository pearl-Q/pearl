package com.qidi.utils;

import com.qidi.pojo.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 白世鑫
 * @title: WebUtils
 * @projectName Tomcat
 * @description:
 * @date 2020/9/2  9:34 上午
 */
public class WebUtils {

    public static <T>T copyParamToBean(HttpServletRequest request,T bean){
        try {
            BeanUtils.populate(bean,request.getParameterMap());
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }


    /**
     * 将字符串转换为 int
     * @param strId 字符串类型的数值
     * @param defaultValue 转换失败的默认值
     * @return
     */
    public static int parseInt(String strId,int defaultValue){
        try {
            return Integer.parseInt(strId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

}
