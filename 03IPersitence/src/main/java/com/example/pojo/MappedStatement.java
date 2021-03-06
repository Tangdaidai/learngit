package com.example.pojo;

/**
 * @author oxygenxyl
 * @create 2021-07-01 23:35
 */
public class MappedStatement {
    //id
    private String id;
    //sql语句
    private String sql;
    //输入参数
    private String paramterType;
    //输出参数
    private String resultType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getParamterType() {
        return paramterType;
    }

    public void setParamterType(String paramterType) {
        this.paramterType = paramterType;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
