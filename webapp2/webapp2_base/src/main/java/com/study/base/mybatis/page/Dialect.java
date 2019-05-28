package com.study.base.mybatis.page;

public interface Dialect {

    /**
     * 数据库类型
     */
    enum Type {
        ORACLE,
        MYSQL
    }

    /**
     * page sql create
     *
     * @param sql sql
     * @param skipResults 偏移量
     * @param maxResults 最大值
     * @return sql
     */
     String getLimitString(String sql, int skipResults, int maxResults);
}
