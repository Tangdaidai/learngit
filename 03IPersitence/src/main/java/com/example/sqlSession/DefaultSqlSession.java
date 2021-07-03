package com.example.sqlSession;

import com.example.pojo.Configuration;
import com.example.pojo.MappedStatement;

import java.lang.reflect.*;
import java.util.List;

/**
 * @author oxygenxyl
 * @create 2021-07-02 0:59
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementid, Object... params) throws Exception {
        //完成simpleExecutor里的query方法调用
        simpleExecutor simpleExecutor = new simpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementid);
        List<Object> list = simpleExecutor.query(configuration, mappedStatement, params);

        return (List<E>) list;
    }

    @Override
    public <T> T selectone(String statementid, Object... params) throws Exception {
        List<Object> objects = selectList(statementid, params);
        if(objects.size() == 1){
            return (T) objects.get(0);
        } else {
            throw new RuntimeException("查询结果为空或者查询结果太多！");
        }
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        //使用JDK动态代理来为Dao接口生成代理对象，并返回
        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            //proxy:当前代理对象的引用
            //method:当前被调用方法的引用
            //args:传递的参数
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //底层还是去执行JDBC代码
                //根据不同情况，来调用selectList或者selectOne

                //准备参数 1.statementid:sql语句的唯一表示：namespace.id=接口全限定名.方法名
                //方法名
                String methodName = method.getName();
                //getDeclaringClass该方法所在的字节码对象。getName拿到该类的全限定名
                String className = method.getDeclaringClass().getName();
                String statementid = className + "." + methodName;

                //准备参数 2: params实际传递的参数：args
                //获取被调用方法的返回值类型
                Type genericReturnType = method.getGenericReturnType();
                //判断是否进行了 泛型类型参数化
                if (genericReturnType instanceof ParameterizedType){
                    List<Object> objects = selectList(statementid, args);
                    return objects;
                }

                return selectone(statementid, args);
            }
        });

        return (T) proxyInstance;
    }
}
