package com.example.test;

import com.example.io.Resources;

import java.io.InputStream;

/**
 * @author oxygenxyl
 * @create 2021-07-01 0:22
 */
public class IPersistenceTest {

    public void test(){
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

    }
}
