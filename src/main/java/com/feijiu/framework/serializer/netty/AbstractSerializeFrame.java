package com.feijiu.framework.serializer.netty;

import com.feijiu.framework.protocol.SerializeProtocol;
import com.feijiu.framework.serializer.MessageUtil;
import com.feijiu.framework.serializer.netty.kryo.KryoDecoder;
import com.feijiu.framework.serializer.netty.kryo.KryoEncoder;
import com.feijiu.framework.serializer.netty.kryo.KryoPoolFactory;
import com.feijiu.framework.serializer.netty.kryo.KryoUtil;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * Created by zhangtao on 2016/8/8.
 * 消息序列化协议框架
 */
public class AbstractSerializeFrame implements SerializeFrame{


    @Override
    public void select(SerializeProtocol protocol, ChannelPipeline pipeline) {
        switch (protocol) {
            case JDKSERIALIZE: {
                pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, MessageUtil.MESSAGE_LENGTH, 0, MessageUtil.MESSAGE_LENGTH));
                pipeline.addLast(new LengthFieldPrepender(MessageUtil.MESSAGE_LENGTH));
                pipeline.addLast(new ObjectEncoder());
                pipeline.addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
                break;
            }
            default: KRYOSERIALIZE: {
                KryoUtil util = new KryoUtil(KryoPoolFactory.getKryoPoolInstance());
                pipeline.addLast(new KryoEncoder(util));
                pipeline.addLast(new KryoDecoder(util));
                break;
            }
        }
    }
}
