package com.example.test;

import com.example.mapper.IOrderMapper;
import com.example.mapper.IUserMapper;
import com.example.pojo.Order;
import com.example.pojo.Role;
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
 * @create 2021-07-04 1:07
 */
public class MybatisTest {

    //订单确定用户，一对一查询
    @Test
    public void multiTable() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IOrderMapper mapper = sqlSession.getMapper(IOrderMapper.class);
        List<Order> orderAndUser = mapper.findOrderAndUser();
        for (Order order : orderAndUser) {
            System.out.println(order);
        }
        sqlSession.close();
    }


    //查询用户与其所有的订单，一对多查询
    @Test
    public void multiTableOne() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        List<User> all = mapper.findAll();
        for (User user : all) {
            System.out.println(user.getUsername());
            System.out.println(user.getOrderList());
            System.out.println("===================================================================");
        }
        sqlSession.close();
    }

    @Test
    //查询所有用户，同时查询出用户所拥有的角色信息，多对多查询
    public void multiTableMuch() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        List<User> userAndRole = mapper.findUserAndRole();
        for (User user : userAndRole) {
            System.out.println(user);
        }
    }
}
