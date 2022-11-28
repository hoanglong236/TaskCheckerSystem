package org.swing.app.dao;

import org.swing.app.dto.TaskPanelDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

public class HomeFrameDao {

    public HomeFrameDao() {
    }

    private String getRootTaskPanelDtosQuery() {
        final StringBuilder selectClause =
                new StringBuilder("SELECT id, title, start_date_time, finish_date_time\n");
        final StringBuilder fromClause =
                new StringBuilder("FROM task\n");
        final StringBuilder whereClause =
                new StringBuilder("WHERE parent_id IS NULL AND is_completed = FALSE\n");

        final StringBuilder query = new StringBuilder();
        query.append(selectClause);
        query.append(fromClause);
        query.append(whereClause);

        return query.toString();
    }

    private TaskPanelDto getRootTaskPanelDtoFromResultSet(ResultSet resultSet) throws SQLException {
        final TaskPanelDto taskPanelDto = new TaskPanelDto();

        taskPanelDto.setId(resultSet.getInt("id"));
        taskPanelDto.setTitle(resultSet.getString("title"));
        taskPanelDto.setStartDateTime(resultSet.getTimestamp("start_date_time").toLocalDateTime());
        taskPanelDto.setFinishDateTime(resultSet.getTimestamp("finish_date_time").toLocalDateTime());

        return taskPanelDto;
    }

    public Set<TaskPanelDto> getAllRootTaskPanelDtos() {
        final Set<TaskPanelDto> rootTaskPanelDtos = new LinkedHashSet<>();
        final String query = getRootTaskPanelDtosQuery();
        final Connection connection = MySQLConnection.getConnection();

        try {
            final PreparedStatement preStmt = connection.prepareStatement(query);
            final ResultSet resultSet = preStmt.executeQuery();

            while(resultSet.next()){
                rootTaskPanelDtos.add(getRootTaskPanelDtoFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rootTaskPanelDtos;
    }
}
