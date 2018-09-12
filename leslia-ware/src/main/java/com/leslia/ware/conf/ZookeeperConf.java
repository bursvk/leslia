package com.leslia.ware.conf;

import java.util.ResourceBundle;

public class ZookeeperConf{

    private static String zkAddress;

    static {
        ResourceBundle resourceBundle=ResourceBundle.getBundle("conf/lock-zookeeper");
        zkAddress=resourceBundle.getString("zk.address");
    }

    public static String getZkAddress() {
        return zkAddress;
    }

    public static void setZkAddress(String zkAddress) {
        ZookeeperConf.zkAddress = zkAddress;
    }

}
