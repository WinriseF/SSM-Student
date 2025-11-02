package com.java.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * Spring在每次数据库操作时都会调用此方法，以决定使用哪个数据源
     * @return 返回的是数据源的查找键 (lookup key)，即我们在XML中配置的key
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSourceType();
    }
}