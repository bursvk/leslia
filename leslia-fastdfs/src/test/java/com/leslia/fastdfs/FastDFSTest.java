package com.leslia.fastdfs;

import org.apache.commons.io.IOUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class FastDFSTest {

    private String local_filename = "/Volumes/haslove/images/s_bibe1.jpg";

    @Test
    public void testUpload() {
        try {
            StorageClient storageClient = FastDFS.storageClient();
            NameValuePair nvp [] = new NameValuePair[]{
                    new NameValuePair("age", "18"),
                    new NameValuePair("sex", "male")
            };
            for(int i=0;i<3;i++){
                String fileIds[] = storageClient.upload_file(local_filename, "png", nvp);
                System.out.println("组名：" + fileIds[0]);
                System.out.println("路径: " + fileIds[1]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDownload() {
        try {
            StorageClient storageClient = FastDFS.storageClient();
            byte[] b = storageClient.download_file("group2", "M00/00/00/wKiZjlxFmk-AJWmgAAKPpiPsna4473.png");
            String filename=UUID.randomUUID().toString();
            System.out.println("文件名："+filename);
            IOUtils.write(b, new FileOutputStream("/Volumes/haslove/images/"+filename+".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetFileInfo(){
        try {
            StorageClient storageClient = FastDFS.storageClient();
            FileInfo fi = storageClient.get_file_info("group3", "M00/00/00/wKiZj1xFp3OAXqacAAAJo7BuCew575.png");
            System.out.println(fi.getSourceIpAddr());
            System.out.println(fi.getFileSize());
            System.out.println(fi.getCreateTimestamp());
            System.out.println(fi.getCrc32());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetFileMate(){
        try {
            StorageClient storageClient = FastDFS.storageClient();
            NameValuePair nvs [] = storageClient.get_metadata("group2", "M00/00/00/wKiZjlxFmk-AJWmgAAKPpiPsna4473.png");
            for(NameValuePair nvp : nvs){
                System.out.println(nvp.getName() + ":" + nvp.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete(){
        try {
            StorageClient storageClient = FastDFS.storageClient();
            int i = storageClient.delete_file("group2", "M00/00/00/wKiZjlxFeoqAVHxoAAKPpiPsna4348.png");
            System.out.println( i==0 ? "删除成功" : "删除失败:"+i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}