package com.feijiu.framework;

import com.feijiu.framework.proxy.rpc.AbstractRPCProxy;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by zhangtao on 2016/8/11.
 */
public class RPCProxy<T> extends AbstractRPCProxy<T> {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Exception {
        System.out.println("----------------------------------------------------");
        return "1231232133";
    }
}
