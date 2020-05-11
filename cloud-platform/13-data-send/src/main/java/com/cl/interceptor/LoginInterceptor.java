package com.cl.interceptor;


import com.cl.common.Contants;
import com.cl.model.UserDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * by cl at 2020/3/30 0030
 */
public class LoginInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("进行用户登录拦截");
        //获取session
        HttpSession session = request.getSession();
        UserDO user = (UserDO) session.getAttribute(Contants.USER_SESSION);
        //判断session中是否有用户数据，如果有，则返回true，继续向下执行
        if (user != null) {
            return true;
        }
        //不符合条件的给出提示信息，并转发到登录页面
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        return false;
    }

}
