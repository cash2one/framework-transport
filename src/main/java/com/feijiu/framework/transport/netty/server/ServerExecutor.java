package com.feijiu.framework.transport.netty.server;

import com.feijiu.framework.protocol.Request;
import com.feijiu.framework.protocol.Response;
import com.google.common.util.concurrent.*;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by zhangtao on 2016/8/10.
 */
public class ServerExecutor {

    private static ListeningExecutorService threadPoolExecutor;

    public static void submit(Callable<Boolean> task, ChannelHandlerContext ctx, Request request, Response response){
        if (threadPoolExecutor == null) {
            synchronized (ServerExecutor.class) {
                if (threadPoolExecutor == null) {
                    threadPoolExecutor = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
                }
            }
        }
        final ListenableFuture<Boolean> listenableFuture = threadPoolExecutor.submit(task);
        Futures.addCallback(listenableFuture, new FutureCallback<Boolean>() {
            public void onSuccess(Boolean result) {
                ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
            }
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        }, threadPoolExecutor);
    }
}
