package com.feijiu.framework.transport.netty.client;

import com.feijiu.framework.protocol.SerializeProtocol;
import com.google.common.util.concurrent.*;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zhangtao on 2016/8/11.
 * 服务器配置加载
 */
public class ServerLoader {

    private volatile static ServerLoader serverLoader;
    private final static String DELIMITER = ":";
    private SerializeProtocol serializeProtocol = SerializeProtocol.JDKSERIALIZE;

    private final static int parallel = Runtime.getRuntime().availableProcessors() * 2;
    private EventLoopGroup eventLoopGroup = new NioEventLoopGroup(parallel);
    private static ListeningExecutorService threadPoolExecutor = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(100));
    private ClientHandler clientHandler = null;

    private Lock lock = new ReentrantLock();
    private Condition connectStatus = lock.newCondition();
    private Condition handlerStatus = lock.newCondition();

    private ServerLoader() {
    }

    public static ServerLoader getInstance() {
        if (serverLoader == null) {
            synchronized (ServerLoader.class) {
                if (serverLoader == null) {
                    serverLoader = new ServerLoader();
                }
            }
        }
        return serverLoader;
    }

    public void load(String serverAddress, SerializeProtocol serializeProtocol) {
        String[] ipAddr = serverAddress.split(ServerLoader.DELIMITER);
        if (ipAddr.length == 2) {
            String host = ipAddr[0];
            int port = Integer.parseInt(ipAddr[1]);
            final InetSocketAddress remoteAddr = new InetSocketAddress(host, port);

            ListenableFuture<Boolean> listenableFuture = threadPoolExecutor.submit(new ClientInitializeTask(eventLoopGroup, remoteAddr, serializeProtocol));

            Futures.addCallback(listenableFuture, new FutureCallback<Boolean>() {
                public void onSuccess(Boolean result) {
                    try {
                        lock.lock();

                        if (clientHandler == null) {
                            handlerStatus.await();
                        }

                        if (result == Boolean.TRUE && clientHandler != null) {
                            connectStatus.signalAll();
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ServerLoader.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        lock.unlock();
                    }
                }

                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
            }, threadPoolExecutor);
        }
    }

    public void setMessageSendHandler(ClientHandler clientHandler) {
        try {
            lock.lock();
            this.clientHandler = clientHandler;
            handlerStatus.signal();
        } finally {
            lock.unlock();
        }
    }

    public ClientHandler getClientHandler() throws InterruptedException {
        try {
            lock.lock();
            if (clientHandler == null) {
                connectStatus.await();
            }
            return clientHandler;
        } finally {
            lock.unlock();
        }
    }

    public void unLoad() {
        clientHandler.close();
        threadPoolExecutor.shutdown();
        eventLoopGroup.shutdownGracefully();
    }

    public void setSerializeProtocol(SerializeProtocol serializeProtocol) {
        this.serializeProtocol = serializeProtocol;
    }
}
