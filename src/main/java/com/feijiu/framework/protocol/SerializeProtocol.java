package com.feijiu.framework.protocol;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by zhangtao on 2016/8/8.
 * 消息序序列化协议类型
 */
public enum SerializeProtocol {
    JDKSERIALIZE("jdknative"), KRYOSERIALIZE("kryo");

    String serializeProtocol;

    SerializeProtocol(String serializeProtocol) {
        this.serializeProtocol = serializeProtocol;
    }

    public String toString() {
        ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
        return ReflectionToStringBuilder.toString(this);
    }

    public String getProtocol() {
        return serializeProtocol;
    }

}
