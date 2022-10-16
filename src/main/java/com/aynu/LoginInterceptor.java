package com.aynu;

import com.aynu.bean.User;
import com.aynu.bean.UserInfo;
import com.aynu.controller.IndexController;
import com.aynu.controller.LoginController;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/4/12 16:56
 * @description
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        //包括login的，都不需要拦截
        if (url.indexOf("/page/index")>=0){
            return true;
        }
        HttpSession session = request.getSession();
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        //判断是否存在
        if (user!=null){
//            System.out.println("存在user故放行"+user);
            return true;
        }
        //不符合条件的 转发到登录页面
//        new IndexController().ToLogin();
//        request.getRequestDispatcher("/page/index").forward(request, response);
        response.sendRedirect("/approval/page/index");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
