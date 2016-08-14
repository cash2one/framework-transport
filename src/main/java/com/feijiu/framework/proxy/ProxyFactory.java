package com.feijiu.framework.proxy;


import com.feijiu.framework.proxy.interceptor.Interceptor;
import com.feijiu.framework.proxy.interceptor.InterceptorFactory;
import net.sf.cglib.proxy.Enhancer;

/**
 * Created by zhangtao on 2016/8/12.
 */
public class ProxyFactory {

    private Class<? extends Proxy> proxy ;
    private Class<? extends Interceptor> interceptor ;

    public ProxyFactory(Class<? extends Proxy> proxy, Class<? extends Interceptor> interceptor){
        this.interceptor = interceptor ;
        this.proxy = proxy ;
    }


    public <T> T instance() throws Exception {
        if(proxy.isInterface()){
            ByteCodeUtil byteCode = new ByteCodeUtil();
            byteCode.setSourceInterFace(proxy);
            proxy  = byteCode.createClass();
        }
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(proxy);
        enhancer.setCallback(InterceptorFactory.instance(interceptor));
        return (T) enhancer.create();

    }
}
