package org.swing.app.dao;

import org.swing.app.dao.connection.MySQLConnection;
import org.swing.app.dto.TaskDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CommonDaoImpl implements CommonDao {

    private static final Connection CONNECTION = MySQLConnection.getConnection();

    private String getInsertTaskByDtoQuery() {
        final StringBuilder query = new StringBuilder();

        query.append("INSERT INTO task(id, parent_id, title, important, start_datetime, finish_datetime, note)\n");
        query.append("VALUES(?, ?, ?, ?, ?, ?, ?)\n");

        return query.toString();
    }

    @Override
    public boolean insertTaskByDto(TaskDto taskDto) {
        final String query = getInsertTaskByDtoQuery();

        try {
            final PreparedStatement preStmt = CONNECTION.prepareStatement(query);

            preStmt.setString(1, taskDto.getId());
            preStmt.setString(2, taskDto.getParentId());
            preStmt.setString(3, taskDto.getTitle());
            preStmt.setBoolean(4, taskDto.isImportant());
            preStmt.setTimestamp(5, Timestamp.valueOf(taskDto.getStartDatetime()));
            preStmt.setTimestamp(6, Timestamp.valueOf(taskDto.getFinishDatetime()));
            preStmt.setString(7, taskDto.getNote());

            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
