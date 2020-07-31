package shenk.watcher;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

/**
 * @author: shenk
 * @date: 下午3:10
 * @description:
 */
public class ConnectWatcher implements Watcher {

    /**
     * 用于检测病阻塞用户线程直至连接初始化完成，semaphore的大小为1
e     */
    private CountDownLatch semaphore;

    public ConnectWatcher(CountDownLatch semaphore) {
        this.semaphore = semaphore;
    }

    public void process(WatchedEvent watchedEvent) {
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            System.out.println("zookeeper session established!");
            semaphore.countDown();
        }
    }
}
