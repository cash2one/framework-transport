package com.feijiu.framework.proxy.rpc;

import com.feijiu.framework.proxy.interceptor.AbstractInterceptorHandler;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by zhangtao on 2016/8/11.
 */
public class ClassInterceptorHandler extends AbstractInterceptorHandler {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("接口实现类代理");
        return null;
    }
}
