package com.example.test;

import com.example.dao.IUserDao;
import com.example.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author oxygenxyl
 * @create 2021-07-03 19:57
 */
public class MybatisTest {

    @Test
    public void queryAll() throws IOException {
        //1.Resources工具类，进行了配置文件的加载，把配置文件加载成字节输入流
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //创建者模式
        //2.解析了配置文件，并创建了sqlSessionFactory工厂
        SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(resourceAsStream);
        //工厂模式
        //3.生成sqlSession回话对象
        //openSession：默认开启事务，但是该事务不会自动提交，所以在进行增删改操作时要手动提交事务
        //可以开启自动提交事务，SqlSession sqlSession = sqlSessionFactory.openSession(true);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4.sqlSession调用方法：查询所有selectList 查询单个selectOne 添加insert 修改update 删除delete
        List<User> users = sqlSession.selectList("com.example.dao.IUserDao.findAll");
        for (User user : users) {
            System.out.println(user);
        }
        //释放资源
        sqlSession.close();
    }

    @Test
    public void save() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //创建者模式
        SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(resourceAsStream);
        //工厂模式
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        User user = new User();
        user.setId(3);
        user.setUsername("jack");
        user.setPassword("456");
        user.setBirthday("2021-7-3");
        sqlSession.insert("com.example.dao.IUserDao.saveUser", user);
        //增删改需要提交事务,开启自动提交事务就不需要手动提交
        //sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    @Test
    public void update() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //创建者模式
        SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(resourceAsStream);
        //工厂模式
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(3);
        user.setPassword("123");
        sqlSession.update("com.example.dao.IUserDao.updateUser", user);
        //增删改需要提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    @Test
    public void delete() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //创建者模式
        SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(resourceAsStream);
        //工厂模式
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.delete("com.example.dao.IUserDao.deleteUser", 3);
        //增删改需要提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    @Test
    public void originTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获得MyBatis框架生成的UserMapper接口的实现类
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        List<User> all = mapper.findAll();
        for (User user : all) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    @Test
    public void ifTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        User user = new User();
        user.setUsername("tom");
        //user.setId(2);
        List<User> byCondition = mapper.findByCondition(user);
        for (User user1 : byCondition) {
            System.out.println(user1);
        }
    }

    @Test
    public void foreachTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        int[] arr = {1,3};
        List<User> all = mapper.findByIds(arr);
        for (User user : all) {
            System.out.println(user);
        }
    }
}
