package com.feijiu.framework.transport.netty;

import com.feijiu.framework.protocol.SerializeProtocol;
import com.feijiu.framework.transport.TransportConfig;
import com.feijiu.framework.transport.netty.server.ServerChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * Created by zhangtao on 2016/8/10.
 */
public class ServerBoot {

    private TransportConfig config ;
    private SerializeProtocol serializeProtocol ;

    public ServerBoot(TransportConfig config){
        this.config = config ;
        this.serializeProtocol =Enum.valueOf(SerializeProtocol.class, config.getProtocol());
    }

    protected final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors()*2;	//默认
    /** 业务出现线程大小*/
    protected final int BIZTHREADSIZE = 4;
    private final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
    private final EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);


    public void run() throws Exception{
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(new ServerChannelInitializer().buildRpcSerializeProtocol(serializeProtocol)).option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture future = b.bind(config.getHost(), config.getPort()).sync();
            future.channel().closeFuture().sync();
        }finally{
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


}
