package com.feijiu.framework.proxy.interceptor;

import com.feijiu.framework.protocol.Request;
import com.feijiu.framework.transport.netty.client.Callback;
import com.feijiu.framework.transport.netty.client.ClientHandler;
import com.feijiu.framework.transport.netty.client.ServerLoader;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Created by 涛 on 2016/8/14.
 * 客户端方法代理执行
 */
public class AbstracInterfacetInterceptorHandler implements Interceptor {


    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Request request = new Request();
        request.setRequestId(UUID.randomUUID().getLeastSignificantBits());
        request.setMethodName(method.getName());
        request.setTypeParameters(method.getParameterTypes());
        request.setParametersVal(args);
        ClientHandler handler = ServerLoader.getInstance().getClientHandler();
        Callback callBack = handler.sendRequest(request);
        return callBack.start();
    }
}
