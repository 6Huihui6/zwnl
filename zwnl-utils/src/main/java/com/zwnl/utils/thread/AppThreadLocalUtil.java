package com.zwnl.utils.thread;


import com.zwnl.model.user.pos.Users;

public class AppThreadLocalUtil {

    private final static ThreadLocal<Users> WM_USER_THREAD_LOCAL = new ThreadLocal<>();

    //存入线程中
    public static void setUser(Users user){
        WM_USER_THREAD_LOCAL.set(user);
    }

    //从线程中获取
    public static Users getUser(){
        return WM_USER_THREAD_LOCAL.get();
    }

    //清理
    public static void clear(){
        WM_USER_THREAD_LOCAL.remove();
    }

}
