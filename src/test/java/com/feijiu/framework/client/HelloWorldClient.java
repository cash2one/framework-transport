package com.feijiu.framework.client;

import com.feijiu.framework.RPCProxy;

/**
 * Created by zhangtao on 2016/8/11.
 */
public interface HelloWorldClient  {

    class HelloWorldClientImpl extends RPCProxy<HelloWorldClient>{

    }

    String say(String name) ;

}
