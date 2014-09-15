package com.litao.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;

import java.io.IOException;

/**
 * Created by Tao Li on 9/14/14.
 */
public class CreateGroup extends ConnectionWatcher {

    public void create(String groupName) throws KeeperException, InterruptedException {
        String path = "/" + groupName;
        // 调用ZooKeeper对象的create()方法，创建一个新的znode
        // 路径、znode内容、访问控制列表、znode的类型
        // ZooDefs.Ids.OPEN_ACL_UNSAFE允许任何客户端对znode进行读写
        String createdPath = zk.create(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
    }

    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        CreateGroup createGroup = new CreateGroup();
        createGroup.connect(args[0]);
        createGroup.create(args[1]);
        createGroup.close();
    }
}
