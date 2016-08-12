package com.feijiu.framework.transport.netty.server;

import com.feijiu.framework.protocol.Request;
import com.feijiu.framework.protocol.Response;

import java.util.concurrent.Callable;

/**
 * Created by zhangtao on 2016/8/10.
 * 服务器消息线程任务处理
 */
public class ServerInitializeTask implements Callable<Boolean> {

    private Request request = null;
    private Response response = null;


    ServerInitializeTask(Request request, Response response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public Boolean call() throws Exception {
        response.setRequestId(request.getRequestId());
        try {
            Object result = reflect(request);
            response.setResultDesc(result);
            return Boolean.TRUE;
        } catch (Throwable t) {
            response.setCause(t);
            t.printStackTrace();
            return Boolean.FALSE;
        }
    }

    private Object reflect(Request request) throws Throwable {
        //执行代理
        return null ;
    }
}
