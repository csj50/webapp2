package com.study.base.mybatis.page;

public class OracleDialect implements Dialect {

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
        return "select * from ( select row_.*, rownum rownum_ from ( " +
                sql +
                " ) row_ ) where rownum_ > " + offset + " and rownum_ <= " + (offset + limit);
    }

}
