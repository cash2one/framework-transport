package com.feijiu.framework;

import com.feijiu.framework.protocol.SerializeProtocol;
import com.feijiu.framework.transport.TransportConfig;
import com.feijiu.framework.transport.netty.ServerBoot;

/**
 * Created by zhangtao on 2016/8/10.
 */
public class TestMain {

    public static void main(String[] args) {
        TransportConfig config = new TransportConfig();
        config.setHost("127.0.0.1");
        config.setPort(58888);

        ServerBoot boot = new ServerBoot(config);
        try {
            boot.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
