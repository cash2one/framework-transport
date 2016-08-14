package com.feijiu.framework.proxy.interceptor;

/**
 * Created by zhangtao on 2016/8/12.
 * 代理方法执行体工厂
 */
public class InterceptorFactory {

    public static Interceptor instance(Class<? extends Interceptor> clazz) throws InstantiationException, IllegalAccessException {
        return clazz.newInstance();
    }

}
