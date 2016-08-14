package com.feijiu.framework.client;

import com.feijiu.framework.proxy.InterfaceProxy;


/**
 * Created by zhangtao on 2016/8/11.
 */
public interface ClientProxyTest extends InterfaceProxy<ClientProxyTest> {

    String say(String str);
}
