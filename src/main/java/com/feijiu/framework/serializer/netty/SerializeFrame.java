package com.feijiu.framework.serializer.netty;


import com.feijiu.framework.protocol.SerializeProtocol;
import io.netty.channel.ChannelPipeline;

/**
 * Created by zhangtao on 2016/8/8.
 * 消息序序列化协议选择器接口
 */
public interface SerializeFrame {

    void select(SerializeProtocol protocol, ChannelPipeline pipeline);
}
