package com.example.test;

import com.example.dao.IUserDao;
import com.example.io.Resources;
import com.example.pojo.User;
import com.example.sqlSession.SqlSession;
import com.example.sqlSession.SqlSessionFactory;
import com.example.sqlSession.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;

/**
 * @author oxygenxyl
 * @create 2021-07-01 0:22
 */
public class IPersistenceTest {

    @Test
    public void test() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //调用
        User user = new User();
        user.setId(1);
        user.setUsername("lucy");
        /*User selectone = sqlSession.selectone("user.selectOne", user);
        System.out.println(selectone);*/
       /* List<User> objects = sqlSession.selectList("user.selectList");
        for (User object : objects) {
            System.out.println(object);
        }*/


        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        //代理对象调用接口中任意方法，都会执行invoke方法
        /*User byCondition = userDao.findByCondition(user);
        System.out.println(byCondition);*/
        List<User> all = userDao.findAll();
        for (User user1 : all) {
            System.out.println(user1);
        }
    }
}
