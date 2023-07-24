package com.database.masterslavesetup.config;
/*
* query 타입(read or write)으로 master인지 slave db인지 지정하는 클래스
* */
public class ReplicationContextHolder {

    private static final ThreadLocal<DataSourceType> CONTEXT_HOLDER = new ThreadLocal<>();

    public static void setDataSourceType(DataSourceType dataSourceType) {
        CONTEXT_HOLDER.set(dataSourceType);
    }

    public static DataSourceType getDataSourceType() {
        return CONTEXT_HOLDER.get();
    }

    public static void clearDataSourceType() {
        CONTEXT_HOLDER.remove();
    }
}
