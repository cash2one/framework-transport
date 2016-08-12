package com.feijiu.framework.transport.netty.client;

import com.feijiu.framework.protocol.Request;
import com.feijiu.framework.proxy.rpc.AbstractRPCProxy;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Created by zhangtao on 2016/8/11.
 */
public class ClientProxy<T> extends AbstractRPCProxy {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Exception {
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
