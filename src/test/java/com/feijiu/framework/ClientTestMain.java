package com.feijiu.framework;

import com.feijiu.framework.protocol.SerializeProtocol;
import com.feijiu.framework.transport.netty.client.ClientChannelInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by zhangtao on 2016/8/11.
 */
public class ClientTestMain {

    public String HOST = "127.0.0.1";
    public int PORT = 58888;

    public Bootstrap bootstrap = getBootstrap();
    public Channel channel = getChannel(HOST,PORT);
    /**
     * 初始化Bootstrap
     * @return
     */
    public final Bootstrap getBootstrap(){
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class);
        b.handler(new ClientChannelInitializer().buildRpcSerializeProtocol(Enum.valueOf(SerializeProtocol.class, "KRYOSERIALIZE")));
        b.option(ChannelOption.SO_KEEPALIVE, true);
        return b;
    }

    public final Channel getChannel(String host,int port){
        Channel channel = null;
        try {
            channel = bootstrap.connect(host, port).sync().channel();
        } catch (Exception e) {
            return null;
        }
        return channel;
    }

    public void sendMsg(String msg) throws Exception {
        if(channel!=null){
            channel.writeAndFlush(msg).sync();
        }else{
        }
    }

    public static void main(String[] args) {
        ClientTestMain main = new ClientTestMain();
        try {
            for (int i = 0; i < 100000; i++) {
                main.sendMsg(i+"你好1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
