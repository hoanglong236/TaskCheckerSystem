package org.swing.app.dao.sql;

public class CommonDaoSql {

    public static final String createSqlToGetDataValueFromGenMaster() {
        final StringBuilder sql = new StringBuilder();

        sql.append("SELECT data_value\n");
        sql.append("FROM gen_master\n");
        sql.append("WHERE data_id = ? AND data_cd = ?\n");

        return sql.toString();
    }

    public static final String createSqlToGetDataNameFromGenMaster() {
        final StringBuilder sql = new StringBuilder();

        sql.append("SELECT data_name\n");
        sql.append("FROM gen_master\n");
        sql.append("WHERE data_id = ? AND data_cd = ?\n");

        return sql.toString();
    }

    public static final String createSqlToCheckTaskIdExist() {
        final StringBuilder sql = new StringBuilder();

        sql.append("SELECT COUNT(*) AS task_count FROM task\n");
        sql.append("WHERE id = ?\n");

        return sql.toString();
    }
}
