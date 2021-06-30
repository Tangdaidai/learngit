package com.example.io;

import java.io.InputStream;

/**
 * @author oxygenxyl
 * @create 2021-07-01 0:13
 */
public class Resources {

    //根據配置文件的路徑，將配置文件加載成字節輸入流，存储在内存中
    public static InputStream getResourceAsStream(String path){
        InputStream resourceAsStream = Resources.class.getClassLoader().getResourceAsStream(path);
        return resourceAsStream;
    }
}
