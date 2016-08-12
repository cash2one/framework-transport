package com.feijiu.framework.serializer.netty;

import com.feijiu.framework.serializer.MessageUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by zhangtao on 2016/8/8.
 * 消息编码
 */
public class MessageEncoder extends MessageToByteEncoder<Object> {

    private MessageUtil util = null;

    public MessageEncoder(final MessageUtil util) {
        this.util = util;
    }

    @Override
    protected void encode(final ChannelHandlerContext ctx, final Object msg, final ByteBuf out) throws Exception {
        util.encode(out, msg);
    }
}
