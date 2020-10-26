package com.qidi.filter;

import com.qidi.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

public class TracsactionFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {


        try {
            chain.doFilter(req, resp);
            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }

    public void init(FilterConfig config) throws ServletException {

    }

}
