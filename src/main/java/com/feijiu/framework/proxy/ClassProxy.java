package com.feijiu.framework.proxy;

import com.feijiu.framework.proxy.interceptor.Interceptor;
import com.feijiu.framework.proxy.interceptor.InterceptorFactory;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.ParameterizedType;

/**
 * Created by zhangtao on 2016/8/12.
 * 接口实现类代理对象创建
 * cglib
 */
public class ClassProxy<T> implements Proxy<T> {

    @Override
    public Class<T> getInstanceClass() throws Exception {
        Class<T> tClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }

    @Override
    public T getInstance(Class<? extends Interceptor> interceptor) throws Exception {
        Class<T> clazz = getInstanceClass();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(InterceptorFactory.instance(interceptor));
        return (T) enhancer.create();
    }

}
