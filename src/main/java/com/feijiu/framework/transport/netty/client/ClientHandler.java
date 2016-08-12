package com.feijiu.framework.transport.netty.client;

import com.feijiu.framework.protocol.Request;
import com.feijiu.framework.protocol.Response;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.SocketAddress;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhangtao on 2016/8/11.
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    private ConcurrentHashMap<Long, Callback> mapCallBack = new ConcurrentHashMap<Long, Callback>();

    private volatile Channel channel;
    private SocketAddress remoteAddr;

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        this.channel = ctx.channel();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Response response = (Response) msg;
        Long requestId = response.getRequestId();
        Callback callBack = mapCallBack.get(requestId);
        if (callBack != null) {
            mapCallBack.remove(requestId);
            callBack.over(response);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        this.remoteAddr = this.channel.remoteAddress();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }


    public void close() {
        channel.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    public Callback sendRequest(Request request) {
        Callback callBack = new Callback(request);
        mapCallBack.put(request.getRequestId(), callBack);
        channel.writeAndFlush(request);
        return callBack;
    }
}
