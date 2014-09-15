package com.litao.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Tao Li on 9/14/14.
 */
public class ConnectionWatcher implements Watcher {
    private static final int SESSION_TIMEOUT = 5000;

    protected ZooKeeper zk;
    private CountDownLatch connectedSignal = new CountDownLatch(1);

    public void connect(String hosts) throws IOException, InterruptedException {
        // 新建ZooKeeper对象后，会创建一个与zk连接的线程，然后立即返回
        // 参数this，便于回调，接收各种事件的通知
        zk = new ZooKeeper(hosts, SESSION_TIMEOUT, this);
        // ZooKeeper对象创建后，与zk的连接可能还并没有创建
        // 使用CountDownLatch，会将main线程阻塞住
        // 直到与zk建立连接，回调process()方法，将CountDownLatch减小至0为止，await()返回
        connectedSignal.await();
    }

    @Override
    public void process(WatchedEvent event) {
        if(event.getState() == Event.KeeperState.SyncConnected) {
            connectedSignal.countDown();
        }
    }

    public void close() throws InterruptedException {
        zk.close();
    }
}
