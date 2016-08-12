package com.feijiu.framework.serializer.netty.kryo;


import com.feijiu.framework.serializer.MessageUtil;
import com.feijiu.framework.serializer.netty.MessageEncoder;

/**
 * Created by zhangtao on 2016/8/8.
 * Kryo编码器
 */
public class KryoEncoder extends MessageEncoder {

    public KryoEncoder(MessageUtil util) {
        super(util);
    }
}
