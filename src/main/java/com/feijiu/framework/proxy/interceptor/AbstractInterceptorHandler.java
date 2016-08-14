package com.feijiu.framework.proxy.interceptor;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by zhangtao on 2016/8/11.
 * 服务端代理方法执行
 */
public class AbstractInterceptorHandler implements Interceptor {


    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        return proxy.invokeSuper(obj,args);
    }
}
