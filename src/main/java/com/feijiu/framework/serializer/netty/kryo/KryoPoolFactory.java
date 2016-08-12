package com.feijiu.framework.serializer.netty.kryo;


import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.pool.KryoFactory;
import com.esotericsoftware.kryo.pool.KryoPool;
import com.feijiu.framework.protocol.Request;
import com.feijiu.framework.protocol.Response;
import org.objenesis.strategy.StdInstantiatorStrategy;

/**
 * Created by zhangtao on 2016/8/8.
 * Kryo对象池工厂
 */
public class KryoPoolFactory {

    private static KryoPoolFactory poolFactory = null;

    KryoFactory factory = new KryoFactory() {
        public Kryo create() {
            Kryo kryo = new Kryo();
            kryo.setReferences(false);
            kryo.register(Request.class);
            kryo.register(Response.class);
            kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
            return kryo;
        }
    };

    KryoPool pool = new KryoPool.Builder(factory).build();

    private KryoPoolFactory() {}

    public static KryoPool getKryoPoolInstance() {
        if (poolFactory == null) {
            synchronized (KryoPoolFactory.class) {
                if (poolFactory == null) {
                    poolFactory = new KryoPoolFactory();
                }
            }
        }
        return poolFactory.getPool();
    }

    public KryoPool getPool() {
        return pool;
    }
}
