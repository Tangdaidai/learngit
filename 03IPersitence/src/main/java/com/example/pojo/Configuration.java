package com.example.pojo;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author oxygenxyl
 * @create 2021-07-01 23:37
 */
public class Configuration {
    private DataSource dataSource;

    Map<String, MappedStatement> mappedStatementMap = new HashMap<>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getMappedStatementMap() {
        return mappedStatementMap;
    }

    public void setMappedStatementMap(Map<String, MappedStatement> mappedStatementMap) {
        this.mappedStatementMap = mappedStatementMap;
    }
}
