package com.starvey.interceptor;

import org.casbin.casdoor.entity.CasdoorUser;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Yu yaodong
 */
public class UserLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        CasdoorUser user = (CasdoorUser) session.getAttribute("casdoorUser");
        if (user != null) {
            return true;
        }
        response.sendRedirect(request.getContextPath() + "toLogin");
        return false;
    }
}
