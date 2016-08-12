package com.feijiu.framework.transport.netty.client;

import com.feijiu.framework.protocol.Request;
import com.feijiu.framework.protocol.Response;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhangtao on 2016/8/11.
 * 消息回调
 */
public class Callback {

    private Request request;
    private Response response;
    private Lock lock = new ReentrantLock();
    private Condition finish = lock.newCondition();

    public Callback(Request request) {
        this.request = request;
    }

    public Object start() throws InterruptedException {
        try {
            lock.lock();
            finish.await(10*1000, TimeUnit.MILLISECONDS);
            if (this.response != null) {
                return this.response.getResultDesc();
            } else {
                return null;
            }
        } finally {
            lock.unlock();
        }
    }

    public void over(Response reponse) {
        try {
            lock.lock();
            finish.signal();
            this.response = reponse;
        } finally {
            lock.unlock();
        }
    }


    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
