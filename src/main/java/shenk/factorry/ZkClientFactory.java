package shenk.factorry;

import org.I0Itec.zkclient.IZkConnection;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.ZkSerializer;

/**
 * @author: shenk
 * @date: 上午11:26
 * @description:
 */
public class ZkClientFactory {

    public static ZkClient getZkClient(String server) {
        return new ZkClient(server);
    }

    /**
     *
     * @param servers 服务列表。由英文逗号隔开
     * @param connectTimeout
     * @return
     */
    public static ZkClient getZkClient(String servers, int connectTimeout) {
        return new ZkClient(servers, connectTimeout);
    }

    public static ZkClient getZkClient(String servers, int sessionTImeOUt, int connectTimeout) {
        return new ZkClient(servers, sessionTImeOUt, connectTimeout);
    }

    public static ZkClient getZkClient(String servers, int sessionTImeOUt, int connectTimeout, ZkSerializer zkSerializer) {
        //zkSerializer序列化组件，不提供默认使用jdk序列化
        return new ZkClient(servers, sessionTImeOUt, connectTimeout, zkSerializer);
    }

    public static ZkClient getZkClient(IZkConnection iZkConnection) {
        return new ZkClient(iZkConnection);
    }

    public static ZkClient getZkClient(IZkConnection iZkConnection, int connectTimeout) {
        return new ZkClient(iZkConnection, connectTimeout);
    }

    public static ZkClient getZkClient(IZkConnection iZkConnection, int connectTimeout, ZkSerializer zkSerializer) {
        return new ZkClient(iZkConnection, connectTimeout, zkSerializer);
    }
}
