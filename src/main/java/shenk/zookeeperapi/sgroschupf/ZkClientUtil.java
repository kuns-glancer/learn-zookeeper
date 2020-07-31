package shenk.zookeeperapi.sgroschupf;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * @author: shenk
 * @date: 下午2:11
 * @description:
 */
public class ZkClientUtil {

    /**
     * create node
     */
    public static String create(final ZkClient zkClient, String path, Object data, CreateMode mode) {
        return zkClient.create(path, data, mode);
    }


    public static void createEphemeral(final ZkClient zkClient, String path, Object object) {
        zkClient.createEphemeral(path, object);
    }

    public static void createEphemeralSequential(final ZkClient zkClient, String path, Object object) {
        zkClient.createEphemeralSequential(path, object);
    }

    public static void createPersistent(final ZkClient zkClient, String path, Object object) {
        zkClient.createPersistent(path, object);
    }

    public static void createPersistent(final ZkClient zkClient, String path, boolean createParent) {
        zkClient.createPersistent(path, createParent);
    }

    /**
     * delete node
     */
    public static boolean delete(final ZkClient zkClient, String path) {
        return zkClient.delete(path);
    }

    public static boolean deleteRecursive(final ZkClient zkClient, String path) {
        return zkClient.deleteRecursive(path);
    }

    /**
     * getChildren
     */
    public static List<String> getChildren(final ZkClient zkClient, final String path) {
        /**
         * 使用该方式监听子节点列表变化
         *kClient.subscribeChildChanges(path, IZkChildListener listener)
         */
        return zkClient.getChildren(path);
    }

    /**
     * getData
     */
    public static <T extends Object> T readData(final ZkClient zkClient, String path) {
        return zkClient.readData(path);
    }

    public static <T extends Object> T readData(final ZkClient zkClient, String path, boolean returnNullIfPathNotExists) {
        return zkClient.readData(path, returnNullIfPathNotExists);
    }

    public static <T extends Object> T readData(final ZkClient zkClient, String path, Stat stat) {
        return zkClient.readData(path, stat);
    }

    /**
     * setData
     */
    public static void writeData(final ZkClient zkClient, String path, Object data) {
        zkClient.writeData(path, data);
    }

    public static void writeData(final ZkClient zkClient, String path, Object data, int expectedVersion) {
            zkClient.writeData(path, data, expectedVersion);
    }

    /**
     * node exists
     */
    public static boolean exists(final ZkClient zkClient, String path) {
        return zkClient.exists(path);
    }

}
