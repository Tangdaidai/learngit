package com.example.dao;

import com.example.pojo.User;

import java.io.IOException;
import java.util.List;

/**
 * @author oxygenxyl
 * @create 2021-07-03 21:30
 */
public interface IUserDao {
    //查询所有用户
    public List<User> findAll() throws IOException;

    //多条件组合查询：演示if
    public List<User> findByCondition(User user);

    //多值查询：演示foreach
    public List<User> findByIds(int[] ids);
}
