package shenk.factorry;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import shenk.watcher.ConnectWatcher;
import shenk.zookeeperapi.apache.ZookeeperUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author: shenk
 * @date: 下午3:06
 * @description:
 */
public class ZookeeperSessionFactory {

    public static ZooKeeper getZookeeper(String url, int timeOut, Watcher watcher) throws IOException {
        ZooKeeper zooKeeper = new ZooKeeper(url, timeOut, watcher);
        return zooKeeper;
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        CountDownLatch semaphore = new CountDownLatch(1);
        ZooKeeper zooKeeper = getZookeeper("127.0.0.1:2181", 3000, new ConnectWatcher(semaphore));

        semaphore.await();

        ZookeeperUtil.createNodeSync(zooKeeper, "/ipwd", "Hello World".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        Stat stat = ZookeeperUtil.existsSync(zooKeeper, "/ipwd", null);

        System.out.println(stat);

        ZookeeperUtil.createNodeSync(zooKeeper, "/ipwd", "Hello World".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        Map<String, Object> ctx = new HashMap<>();
        CountDownLatch ctxSem = new CountDownLatch(1);
        ZookeeperUtil.getChildrenAsync(zooKeeper, "/", null, new AsyncCallback.Children2Callback() {
            @Override
            public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
                System.out.println(Thread.currentThread());
                System.out.println(ctx.getClass());
                System.out.println(stat);
                ((Map)ctx).put("children", children);
                ctxSem.countDown();
            }
        }, ctx);

        ctxSem.await();
        System.out.println(ctx.size());

        zooKeeper.close();
    }
}
