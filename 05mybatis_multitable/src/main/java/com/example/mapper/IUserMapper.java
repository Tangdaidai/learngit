package com.example.mapper;

import com.example.pojo.Role;
import com.example.pojo.User;

import java.util.List;

/**
 * @author oxygenxyl
 * @create 2021-07-04 0:42
 */
public interface IUserMapper {

    //查询所有用户信息，同时查询出每个用户关联的订单信息
    public List<User> findAll();

    //查询所有用户，同时查询每个用户关联的角色信息
    public List<User> findUserAndRole();
}
