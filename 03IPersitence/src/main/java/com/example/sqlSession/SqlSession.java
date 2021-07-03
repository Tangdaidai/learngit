package com.example.sqlSession;

import java.util.List;

/**
 * @author oxygenxyl
 * @create 2021-07-02 0:57
 */
public interface SqlSession {

    //查询所有
    public <E> List<E> selectList(String statementid, Object... params) throws Exception;


    //根据条件拆线呢单个
    public <T> T selectone(String statementid, Object... params) throws Exception;

    //为Dao生成代理实现类
    public <T> T getMapper(Class<?> mapperClass);
}
