package org.swing.app.dao;

import org.swing.app.dao.connection.MySQLConnection;
import org.swing.app.dto.TaskPanelDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

public class HomeFrameDaoImpl implements HomeFrameDao {

    private static final Connection CONNECTION = MySQLConnection.getConnection();

    private String getIncompleteRootTaskPanelDtosQuery() {
        final StringBuilder query = new StringBuilder();

        query.append("SELECT id, title, start_datetime, finish_datetime\n");
        query.append("    , (\n");
        query.append("        SELECT count(*)\n");
        query.append("        FROM task t1\n");
        query.append("        WHERE t1.parent_id = task.id AND t1.is_completed = TRUE\n");
        query.append("    ) AS child_task_completed_count\n");
        query.append("    , (\n");
        query.append("        SELECT count(*)\n");
        query.append("        FROM task t2\n");
        query.append("        WHERE t2.parent_id = task.id\n");
        query.append("    ) AS child_task_count\n");
        query.append("FROM task\n");
        query.append("WHERE parent_id IS NULL AND is_completed = FALSE\n");

        return query.toString();
    }

    private Set<TaskPanelDto> getIncompleteRootTaskPanelDtosFromResultSet(ResultSet resultSet) throws SQLException {
        final Set<TaskPanelDto> taskPanelDtos = new LinkedHashSet<>();

        while(resultSet.next()){
            final TaskPanelDto taskPanelDto = new TaskPanelDto();

            taskPanelDto.setId(resultSet.getString("id"));
            taskPanelDto.setTitle(resultSet.getString("title"));

            final Timestamp startTimestamp = resultSet.getTimestamp("start_datetime");
            taskPanelDto.setStartDatetime(startTimestamp == null ? null : startTimestamp.toLocalDateTime());
            final Timestamp finishTimestamp = resultSet.getTimestamp("finish_datetime");
            taskPanelDto.setFinishDatetime(finishTimestamp == null ? null : finishTimestamp.toLocalDateTime());

            taskPanelDto.setChildTaskCompletedCount(resultSet.getInt("child_task_completed_count"));
            taskPanelDto.setChildTaskCount(resultSet.getInt("child_task_count"));

            taskPanelDtos.add(taskPanelDto);
        }

        return taskPanelDtos;
    }

    @Override
    public Set<TaskPanelDto> getIncompleteRootTaskPanelDtos() {
        final String query = getIncompleteRootTaskPanelDtosQuery();

        try {
            final PreparedStatement preStmt = CONNECTION.prepareStatement(query);
            final ResultSet resultSet = preStmt.executeQuery();

            return getIncompleteRootTaskPanelDtosFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new LinkedHashSet<>();
    }

    private String getNodeTaskPanelDtosByParentIdQuery() {
        final StringBuilder query = new StringBuilder();

        query.append("SELECT id, parent_id, title, start_datetime, finish_datetime\n");
        query.append("    , submit_datetime, is_completed, note\n");
        query.append("    , (\n");
        query.append("        SELECT count(*)\n");
        query.append("        FROM task t1\n");
        query.append("        WHERE t1.parent_id = task.id AND t1.is_completed = TRUE\n");
        query.append("    ) AS child_task_completed_count\n");
        query.append("    , (\n");
        query.append("        SELECT count(*)\n");
        query.append("        FROM task t2\n");
        query.append("        WHERE t2.parent_id = task.id\n");
        query.append("    ) AS child_task_count\n");
        query.append("FROM task\n");
        query.append("WHERE parent_id = ?\n");
        query.append("ORDER BY is_completed\n");

        return query.toString();
    }

    private Set<TaskPanelDto> getNodeTaskPanelDtosFromResultSet(ResultSet resultSet) throws SQLException {
        final Set<TaskPanelDto> taskPanelDtos = new LinkedHashSet<>();

        while(resultSet.next()){
            final TaskPanelDto taskPanelDto = new TaskPanelDto();
            final Timestamp startTimestamp = resultSet.getTimestamp("start_datetime");
            final Timestamp finishTimestamp = resultSet.getTimestamp("finish_datetime");
            final Timestamp submitTimestamp = resultSet.getTimestamp("submit_datetime");

            taskPanelDto.setId(resultSet.getString("id"));
            taskPanelDto.setParentId(resultSet.getString("parent_id"));
            taskPanelDto.setTitle(resultSet.getString("title"));

            taskPanelDto.setStartDatetime(startTimestamp == null ? null : startTimestamp.toLocalDateTime());
            taskPanelDto.setFinishDatetime(finishTimestamp == null ? null : finishTimestamp.toLocalDateTime());
            taskPanelDto.setSubmitDatetime(submitTimestamp == null ? null : submitTimestamp.toLocalDateTime());

            taskPanelDto.setCompleted(resultSet.getBoolean("is_completed"));
            taskPanelDto.setNote(resultSet.getString("note"));
            taskPanelDto.setChildTaskCompletedCount(resultSet.getInt("child_task_completed_count"));
            taskPanelDto.setChildTaskCount(resultSet.getInt("child_task_count"));

            taskPanelDtos.add(taskPanelDto);
        }

        return taskPanelDtos;
    }

    @Override
    public Set<TaskPanelDto> getNodeTaskPanelDtosByParentId(String parentId) {
        final String query = getNodeTaskPanelDtosByParentIdQuery();

        try {
            final PreparedStatement preStmt = CONNECTION.prepareStatement(query);
            preStmt.setString(1, parentId);
            final ResultSet resultSet = preStmt.executeQuery();

            return getNodeTaskPanelDtosFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new LinkedHashSet<>();
    }

    private String getLeafTaskPanelDtosByParentIdQuery() {
        final StringBuilder query = new StringBuilder();

        query.append("SELECT id, parent_id, title, is_completed\n");
        query.append("FROM task\n");
        query.append("WHERE parent_id = ?\n");
        query.append("ORDER BY is_completed\n");

        return query.toString();
    }

    private Set<TaskPanelDto> getLeafTaskPanelDtosFromResultSet(ResultSet resultSet) throws SQLException {
        final Set<TaskPanelDto> taskPanelDtos = new LinkedHashSet<>();

        while(resultSet.next()){
            final TaskPanelDto taskPanelDto = new TaskPanelDto();

            taskPanelDto.setId(resultSet.getString("id"));
            taskPanelDto.setParentId(resultSet.getString("parent_id"));
            taskPanelDto.setTitle(resultSet.getString("title"));
            taskPanelDto.setCompleted(resultSet.getBoolean("is_completed"));

            taskPanelDtos.add(taskPanelDto);
        }

        return taskPanelDtos;
    }

    @Override
    public Set<TaskPanelDto> getLeafTaskPanelDtosByParentId(String parentId) {
        final String query = getLeafTaskPanelDtosByParentIdQuery();

        try {
            final PreparedStatement preStmt = CONNECTION.prepareStatement(query);
            preStmt.setString(1, parentId);
            final ResultSet resultSet = preStmt.executeQuery();

            return getLeafTaskPanelDtosFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new LinkedHashSet<>();
    }

    private String getDeleteTaskByIdQuery() {
        final StringBuilder query = new StringBuilder();
        query.append("DELETE FROM task WHERE id = ?\n");
        return query.toString();
    }

    @Override
    public boolean deleteTaskById(String taskId) {
        return false;
    }
}
