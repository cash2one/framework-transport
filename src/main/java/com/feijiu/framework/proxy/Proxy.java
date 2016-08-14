package com.feijiu.framework.proxy;


import com.feijiu.framework.proxy.interceptor.Interceptor;

/**
 * Created by zhangtao on 2016/8/10.
 * 动态代理接口
 */
public interface Proxy<T> {


    /**
     * 获取代理对象类型
     * @return
     * @throws Exception
     */
    Class<T> getInstanceClass() throws Exception ;

    /**
     * 获取代理对象实例
     * @return
     * @throws Exception
     */
    T getInstance(Class<? extends Interceptor> interceptor) throws Exception ;



}
