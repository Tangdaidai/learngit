package com.example.mapper;

import com.example.pojo.Order;

import java.util.List;

/**
 * @author oxygenxyl
 * @create 2021-07-04 1:42
 */
public interface IOrderMapper {

    //查询订单的同时，还需查询该订单所属的用户,一对一
    public List<Order> findOrderAndUser();
}
