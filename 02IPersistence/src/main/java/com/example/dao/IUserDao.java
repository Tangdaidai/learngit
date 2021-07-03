package com.example.dao;

import com.example.pojo.User;

import java.util.List;

/**
 * @author oxygenxyl
 * @create 2021-07-03 16:43
 */
public interface IUserDao {

    //查询所有用户
    public List<User> findAll() throws Exception;

    //根据条件查询
    public User findByCondition(User user) throws Exception;
}
