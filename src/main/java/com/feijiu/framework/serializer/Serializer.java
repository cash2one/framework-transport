package com.feijiu.framework.serializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by zhangtao on 2016/8/10.
 * 消息序列化/反序列化接口定义
 */
public interface Serializer {

    /**
     * 序列化
     * */
    void serialize(OutputStream output, Object object) throws IOException;

    /**
     * 反序列化
     * */
    Object deserialize(InputStream input) throws IOException;
}
