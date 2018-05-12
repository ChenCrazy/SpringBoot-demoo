package com.example.demo.Controller;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileOutputStream;

public class FileUtil {

    public static void uploadFile(byte[] file, String fileName) throws Exception {
        File path = new File(ResourceUtils.getURL("src/main/resources/static/").getPath());
        //  打印上传地址
        //        System.out.println("path:"+path.getPath());
        FileOutputStream out = new FileOutputStream(path+"/"+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
