package shenk.zookeeperapi.apache;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * @author: shenk
 * @date: 下午3:30
 * @description:
 */
public class ZookeeperUtil {

    /**
     * 同步创建节点
     * @param zooKeeper
     * @param path
     * @param data
     * @param acls
     * @param mode
     * @throws
     */
    public static String createNodeSync(final ZooKeeper zooKeeper, final String path, byte[] data, List<ACL> acls,
                                      CreateMode mode) throws KeeperException, InterruptedException {
        return zooKeeper.create(path, data,acls, mode);
    }

    /**
     * 异步创建节点,不会跑出异常，因为异常信息在回调函数中
     * @param zooKeeper
     * @param path
     * @param data
     * @param acls
     * @param mode
     * @param callback
     * @param ctx
     */
    public static void createNodeAsync(final ZooKeeper zooKeeper, final String path, byte[] data, List<ACL> acls,
                                       CreateMode mode, AsyncCallback.StringCallback callback, Object ctx) {
        /**
         * callback的结果result coded(状态码)
         * 0 成功
         * -4 ConnectionLoss
         * -110 NodeExist
         * -114 SessionExpired
         */
        zooKeeper.create(path, data, acls, mode, callback, ctx);
    }

    /**
     *
     * @param zooKeeper
     * @param path
     * @param version
     * @throws KeeperException
     * @throws InterruptedException
     */
    public static void deleteNodeSync(final ZooKeeper zooKeeper, String path, int version) throws KeeperException, InterruptedException {
        zooKeeper.delete(path, version);
    }

    /**
     *
     * @param zooKeeper
     * @param path
     * @param version
     * @param callback
     * @param ctx
     */
    public static void deleteNodeAsync(final ZooKeeper zooKeeper, String path, int version, AsyncCallback.VoidCallback callback, Object ctx) {
        zooKeeper.delete(path, version, callback, ctx);
    }

    public static List<String> getChildrenSync(final ZooKeeper zookeeper, String path, boolean watch) throws KeeperException, InterruptedException {
        //watcher监听NodeChildrenChange
        return zookeeper.getChildren(path, watch);
    }

    public static List<String> getChildrenSync(final ZooKeeper zookeeper, String path, Watcher watcher) throws KeeperException, InterruptedException {
        return zookeeper.getChildren(path, watcher);
    }

    public static List<String> getChildrenSync(final ZooKeeper zooKeeper, String path, Watcher watcher, Stat stat) throws KeeperException, InterruptedException {
        return zooKeeper.getChildren(path, watcher, stat);
    }

    public static List<String> getChildrenSync(final ZooKeeper zooKeeper, String path, boolean watch, Stat stat) throws KeeperException, InterruptedException {
        return zooKeeper.getChildren(path, watch, stat);
    }

    /**
     * 异步调用的结果在callback中，Children2Callback相对于ChildrenCallback多了Stat
     * @param zooKeeper
     * @param path
     * @param watcher
     * @param callback
     * @param ctx
     */
    public static void getChildrenAsync(final ZooKeeper zooKeeper, String path, Watcher watcher, AsyncCallback.Children2Callback callback, Object ctx) {
        zooKeeper.getChildren(path, watcher, callback, ctx);
    }

    public static void getChildrenAsync(final ZooKeeper zooKeeper, String path, boolean watch, AsyncCallback.Children2Callback callback, Object ctx) {
        zooKeeper.getChildren(path, watch, callback, ctx);
    }

    /**
     * 读取数据内容
     */
    public static byte[] getDataSync(final ZooKeeper zooKeeper, String path, Watcher watcher, Stat stat) throws KeeperException, InterruptedException {
        return zooKeeper.getData(path, watcher, stat);
    }

    public static byte[] getDataSync(final ZooKeeper zooKeeper, String path, boolean watch, Stat stat) throws KeeperException, InterruptedException {
        return zooKeeper.getData(path, watch, stat);
    }

    public void getDataAsync(final ZooKeeper zooKeeper, String path, Watcher watcher, AsyncCallback.DataCallback callback, Object ctx) {
        zooKeeper.getData(path, watcher, callback, ctx);
    }

    public void getDataAsync(final ZooKeeper zooKeeper, String path, boolean watch, AsyncCallback.DataCallback callback, Object ctx) {
        zooKeeper.getData(path, watch, callback, ctx);
    }

    /**
     * 更新数据
     * @param zooKeeper
     * @param path
     * @param data
     * @param version
     * @return
     */
    public static Stat setDataSync(final ZooKeeper zooKeeper, String path, byte[] data, int version) throws KeeperException, InterruptedException {
        return zooKeeper.setData(path, data, version);
    }

    public static void setDataAsync(final ZooKeeper zooKeeper, String path, byte[] data, int version, AsyncCallback.StatCallback callback, Object ctx) {
        zooKeeper.setData(path, data, version, callback, ctx);
    }

    /**
     * 检测节点存在
     */
    public static Stat existsSync(final ZooKeeper zooKeeper, String path, Watcher watcher) throws KeeperException, InterruptedException {
        return zooKeeper.exists(path, watcher);
    }

    public static Stat existsSync(final ZooKeeper zooKeeper, String path, boolean watch) throws KeeperException, InterruptedException {
        return zooKeeper.exists(path, watch);
    }

    public static void existsAsync(final ZooKeeper zooKeeper, String path, Watcher watcher, AsyncCallback.StatCallback callback, Object ctx) {
        zooKeeper.exists(path, watcher, callback, ctx);
    }

    public static void existsAsync(final ZooKeeper zooKeeper, String path, boolean watch, AsyncCallback.StatCallback callback, Object ctx) {
        zooKeeper.exists(path, watch, callback, ctx);
    }

    /**
     * Access Control(world, auth, digest, ip. super)
     * 删除节点的权限比较特殊，其作用范围是其子节点，删除本节点无需认证，可自由删除
     */

    public static void addAuthInfo(final ZooKeeper zookeeper, String scheme, byte[] data) {
        zookeeper.addAuthInfo(scheme, data);
    }
}
