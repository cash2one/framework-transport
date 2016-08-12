package com.feijiu.framework.transport.netty.client;

import com.feijiu.framework.protocol.SerializeProtocol;
import com.feijiu.framework.serializer.netty.AbstractSerializeFrame;
import com.feijiu.framework.serializer.netty.SerializeFrame;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * Created by zhangtao on 2016/8/8.
 * 客户端管道容器
 */
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    SerializeProtocol protocol;
    SerializeFrame frame = null;


    public ClientChannelInitializer buildRpcSerializeProtocol(SerializeProtocol protocol) {
        this.protocol = protocol;
        return this;
    }

    public ClientChannelInitializer() {
        frame = new AbstractSerializeFrame();
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        frame.select(protocol, pipeline);
    }
}
