package org.swing.app.dao.sql;

public class HomeFrameDaoSql {

    public static final String createSqlToGetTaskPanelDto() {
        final StringBuilder sql = new StringBuilder();

        sql.append("SELECT id, parent_id, title, start_datetime, finish_datetime\n");
        sql.append("    , submit_datetime, is_completed, note\n");
        sql.append("    , (\n");
        sql.append("        SELECT count(*)\n");
        sql.append("        FROM task t1\n");
        sql.append("        WHERE t1.parent_id = task.id AND t1.is_completed = TRUE\n");
        sql.append("    ) AS child_task_completed_count\n");
        sql.append("    , (\n");
        sql.append("        SELECT count(*)\n");
        sql.append("        FROM task t2\n");
        sql.append("        WHERE t2.parent_id = task.id\n");
        sql.append("    ) AS child_task_count\n");
        sql.append("FROM task\n");

        return sql.toString();
    }

    public static final String createSqlToGetIncompleteRootTaskPanelDto() {
        final StringBuilder sql = new StringBuilder();

        sql.append(createSqlToGetTaskPanelDto());
        sql.append("WHERE task.parent_id IS NULL AND task.is_completed = FALSE\n");

        return sql.toString();
    }

    public static final String createSqlToGetTaskPanelDtoByParentId() {
        final StringBuilder sql = new StringBuilder();

        sql.append(createSqlToGetTaskPanelDto());
        sql.append("WHERE parent_id = ?\n");
        sql.append("ORDER BY is_completed\n");

        return sql.toString();
    }

    public static final String createSqlToGetTaskPanelDtoById() {
        final StringBuilder sql = new StringBuilder();

        sql.append(createSqlToGetTaskPanelDto());
        sql.append("WHERE task.id = ?");

        return sql.toString();
    }

    public static final String createSqlToGetTaskDtoById() {
        final StringBuilder sql = new StringBuilder();

        sql.append("SELECT id, title, important, start_datetime, finish_datetime, note\n");
        sql.append("FROM task\n");
        sql.append("WHERE id = ?");

        return sql.toString();
    }

    public static final String createSqlToInsertTask() {
        final StringBuilder sql = new StringBuilder();

        sql.append("INSERT INTO task(id, parent_id, title, important, start_datetime, finish_datetime, note)\n");
        sql.append("VALUES(?, ?, ?, ?, ?, ?, ?)\n");

        return sql.toString();
    }

    public static final String createSqlToUpdateTaskById() {
        final StringBuilder query = new StringBuilder();

        query.append("UPDATE task\n");
        query.append("SET title = ?\n");
        query.append("    AND important = ?\n");
        query.append("    AND start_datetime = ?\n");
        query.append("    AND finish_datetime = ?\n");
        query.append("    AND note = ?\n");
        query.append("WHERE id = ?");

        return query.toString();
    }

    public static final String createSqlToDeleteTaskById() {
        final StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM task WHERE id = ?\n");
        return sql.toString();
    }
}
