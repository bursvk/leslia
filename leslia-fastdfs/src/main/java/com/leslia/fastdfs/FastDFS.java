package com.leslia.fastdfs;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

public class FastDFS {


    private static final String conf_filename = "conf/fdfs.properties";

    public static StorageClient storageClient(){
        try {
            ClientGlobal.init(conf_filename);

            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            return storageClient;
        }catch (IOException e){
            throw new RuntimeException(e);
        }catch (MyException e){
            throw new RuntimeException(e);
        }
    }



}
