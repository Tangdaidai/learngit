package com.example.sqlSession;

import com.example.pojo.Configuration;

/**
 * @author oxygenxyl
 * @create 2021-07-02 0:51
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory{

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
