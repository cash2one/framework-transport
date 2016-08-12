package com.feijiu.framework.serializer;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

/**
 * Created by zhangtao on 2016/8/8.
 * 消息编解码接口
 */
public interface MessageUtil {

    final public static int MESSAGE_LENGTH = 4;

    void encode(final ByteBuf out, final Object message) throws IOException;

    Object decode(byte[] body) throws IOException;
}
