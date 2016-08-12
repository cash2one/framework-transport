package com.feijiu.framework.transport.netty.server;

import com.feijiu.framework.protocol.SerializeProtocol;
import com.feijiu.framework.serializer.netty.AbstractSerializeFrame;
import com.feijiu.framework.serializer.netty.SerializeFrame;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * Created by zhangtao on 2016/8/8.
 * 服务端管道容器
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    SerializeProtocol protocol;
    SerializeFrame frame = null;

    public ServerChannelInitializer buildRpcSerializeProtocol(SerializeProtocol protocol) {
        this.protocol = protocol;
        return this;
    }

    public ServerChannelInitializer() {
        frame = new AbstractSerializeFrame();
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        frame.select(protocol, pipeline);
        pipeline.addLast(new ServerDispatchHandler());
    }
}
