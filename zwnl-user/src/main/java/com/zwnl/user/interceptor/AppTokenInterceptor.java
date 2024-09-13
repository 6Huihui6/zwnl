package com.zwnl.user.interceptor;


import com.zwnl.common.domain.po.Users;
import com.zwnl.common.thread.AppThreadLocalUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AppTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String userId = request.getHeader("userId");
        if(userId != null){
            //存入到当前线程中
            Users users = new Users();
            users.setUserId(Integer.valueOf(userId));
            AppThreadLocalUtil.setUser(users);

        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AppThreadLocalUtil.clear();
    }
}
