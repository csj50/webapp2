package com.study.base.mybatis.page;

public class MysqlDialect implements Dialect {

    /**
     * page sql create
     *
     * @param sql sql
     * @param offset 偏移量
     * @param limit 最大值
     * @return sql
     */
    @Override
    public String getLimitString(String sql, int offset, int limit) {
        sql = sql.trim();

        //offset表示开始位置, limit表示返回的条数
        return sql + " limit " + offset + " , " + limit;
    }

}
