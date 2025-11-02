package com.java.datasource;

public class DataSourceContextHolder {

    // 使用ThreadLocal来保证线程安全，存储数据源的key
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    // 设置数据源key
    public static void setDataSourceType(String dataSourceType) {
        CONTEXT_HOLDER.set(dataSourceType);
    }

    // 获取数据源key
    public static String getDataSourceType() {
        return CONTEXT_HOLDER.get();
    }

    // 清除数据源key，在操作结束后调用，防止内存泄漏
    public static void clearDataSourceType() {
        CONTEXT_HOLDER.remove();
    }
}