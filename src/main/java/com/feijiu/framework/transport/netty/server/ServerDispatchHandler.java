package com.feijiu.framework.transport.netty.server;

import com.feijiu.framework.protocol.Request;
import com.feijiu.framework.protocol.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zhangtao on 2016/8/8.
 * 服务器消息处理
 */
public class ServerDispatchHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Response response = new Response(cause);
        Logger.getLogger(ServerDispatchHandler.class.getName()).log(Level.SEVERE, null, cause);
        ctx.writeAndFlush(response);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Request request = (Request) msg;
        Response response = new Response();
        ServerInitializeTask recvTask = new ServerInitializeTask(request, response);
        ServerExecutor.submit(recvTask, ctx, request, response);
    }
}
