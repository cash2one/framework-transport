package com.feijiu.framework.serializer.netty.kryo;


import com.feijiu.framework.serializer.MessageUtil;
import com.feijiu.framework.serializer.netty.MessageDecoder;

/**
 * Created by zhangtao on 2016/8/8.
 * Kryo解码器
 */
public class KryoDecoder extends MessageDecoder {


    public KryoDecoder(MessageUtil util) {
        super(util);
    }
}
