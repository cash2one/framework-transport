package com.feijiu.framework.proxy.interceptor;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by zhangtao on 2016/8/11.
 */
public interface Interceptor extends MethodInterceptor {

    @Override
    Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable;
}
