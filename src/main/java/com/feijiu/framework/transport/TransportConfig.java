package com.feijiu.framework.transport;


/**
 * Created by zhangtao on 2016/8/10.
 */
public class TransportConfig {

    private String host ;
    private Integer port ;
    private String protocol = "KRYOSERIALIZE";


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
