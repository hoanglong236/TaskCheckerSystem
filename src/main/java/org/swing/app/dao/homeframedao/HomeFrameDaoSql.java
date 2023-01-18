package org.swing.app.dao.homeframedao;

class HomeFrameDaoSql {

    public static String createSqlToGetTaskPanelDto() {
        final StringBuilder sql = new StringBuilder();

        sql.append("SELECT task.*\n");
        sql.append("    , (\n");
        sql.append("        SELECT count(*)\n");
        sql.append("        FROM task t1\n");
        sql.append("        WHERE t1.parent_id = task.id AND t1.completed = TRUE\n");
        sql.append("    ) AS child_task_completed_count\n");
        sql.append("    , (\n");
        sql.append("        SELECT count(*)\n");
        sql.append("        FROM task t2\n");
        sql.append("        WHERE t2.parent_id = task.id\n");
        sql.append("    ) AS child_task_count\n");
        sql.append("FROM task\n");

        return sql.toString();
    }

    public static String createSqlToGetIncompleteRootTaskPanelDto() {
        final StringBuilder sql = new StringBuilder();

        sql.append(createSqlToGetTaskPanelDto());
        sql.append("WHERE task.parent_id IS NULL AND task.completed = FALSE\n");

        return sql.toString();
    }

    public static String createSqlToGetTaskPanelDtoByParentId() {
        final StringBuilder sql = new StringBuilder();

        sql.append(createSqlToGetTaskPanelDto());
        sql.append("WHERE parent_id = ?\n");
        sql.append("ORDER BY completed\n");

        return sql.toString();
    }

    public static String createSqlToGetTaskPanelDtoById() {
        final StringBuilder sql = new StringBuilder();

        sql.append(createSqlToGetTaskPanelDto());
        sql.append("WHERE task.id = ?");

        return sql.toString();
    }

    public static String createSqlToGetTaskDtoById() {
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM task WHERE id = ?");
        return sql.toString();
    }

    public static String createSqlToInsertTask() {
        final StringBuilder sql = new StringBuilder();

        sql.append("INSERT INTO task(id, parent_id, title, important, deadline, note)\n");
        sql.append("VALUES(?, ?, ?, ?, ?, ?)\n");

        return sql.toString();
    }

    public static String createSqlToUpdateTaskById() {
        final StringBuilder sql = new StringBuilder();

        sql.append("UPDATE task\n");
        sql.append("SET title = ?\n");
        sql.append("    AND important = ?\n");
        sql.append("    AND deadline = ?\n");
        sql.append("    AND submit_datetime = ?\n");
        sql.append("    AND note = ?\n");
        sql.append("WHERE id = ?");

        return sql.toString();
    }

    public static String createSqlToDeleteTaskById() {
        final StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM task WHERE id = ?\n");
        return sql.toString();
    }
}
