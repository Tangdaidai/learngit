package com.example.test;

import com.example.io.Resources;
import com.example.sqlSession.SqlSession;
import com.example.sqlSession.SqlSessionFactory;
import com.example.sqlSession.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * @author oxygenxyl
 * @create 2021-07-01 0:22
 */
public class IPersistenceTest {

    public void test() throws DocumentException, PropertyVetoException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
    }
}
