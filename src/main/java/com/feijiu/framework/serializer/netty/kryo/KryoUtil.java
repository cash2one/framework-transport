package com.feijiu.framework.serializer.netty.kryo;

import com.esotericsoftware.kryo.pool.KryoPool;
import com.feijiu.framework.serializer.MessageUtil;
import com.google.common.io.Closer;
import io.netty.buffer.ByteBuf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by zhangtao on 2016/8/8.
 * Kryo编解码工具类
 */
public class KryoUtil implements MessageUtil {
    private KryoPool pool;
    private static Closer closer = Closer.create();

    public KryoUtil(KryoPool pool) {
        this.pool = pool;
    }

    @Override
    public void encode(final ByteBuf out, final Object message) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            closer.register(byteArrayOutputStream);
            KryoSerialize kryoSerialization = new KryoSerialize(pool);
            kryoSerialization.serialize(byteArrayOutputStream, message);
            byte[] body = byteArrayOutputStream.toByteArray();
            int dataLength = body.length;
            out.writeInt(dataLength);
            out.writeBytes(body);
        } finally {
            closer.close();
        }
    }

    @Override
    public Object decode(byte[] body) throws IOException {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);
            closer.register(byteArrayInputStream);
            KryoSerialize kryoSerialization = new KryoSerialize(pool);
            Object obj = kryoSerialization.deserialize(byteArrayInputStream);
            return obj;
        } finally {
            closer.close();
        }
    }
}