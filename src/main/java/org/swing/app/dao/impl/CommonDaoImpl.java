package org.swing.app.dao.impl;

import org.swing.app.common.Constant;
import org.swing.app.dao.CommonDao;
import org.swing.app.dao.connection.MySQLConnection;
import org.swing.app.dao.sql.CommonDaoSql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CommonDaoImpl implements CommonDao {

    private static final Connection CONNECTION = MySQLConnection.getConnection();

    public Optional<String> getDataValueFromGenMaster(String dataId, String dataCd) {
        final String sql = CommonDaoSql.createSqlToGetDataValueFromGenMaster();
        String dataValue = null;

        try {
            final PreparedStatement preStmt = CONNECTION.prepareStatement(sql);
            preStmt.setString(1, dataId);
            preStmt.setString(2, dataCd);

            final ResultSet resultSet = preStmt.executeQuery();
            if (resultSet.next()) {
                dataValue = resultSet.getString("data_value");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(dataValue);
    }

    public Optional<String> getDataNameFromGenMaster(String dataId, String dataCd) {
        final String sql = CommonDaoSql.createSqlToGetDataNameFromGenMaster();
        String dataName = null;

        try {
            final PreparedStatement preStmt = CONNECTION.prepareStatement(sql);
            preStmt.setString(1, dataId);
            preStmt.setString(2, dataCd);

            final ResultSet resultSet = preStmt.executeQuery();
            if (resultSet.next()) {
                dataName = resultSet.getString("data_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(dataName);
    }

    @Override
    public int getTaskIdMaxLength() {
        final byte defaultTaskIdMaxLength = Constant.DEFAULT_TASK_ID_MAX_LENGTH;

        final String dataId = "01";
        final String dataCd = "02";
        final Optional<String> dataValue = getDataValueFromGenMaster(dataId, dataCd);

        if (!dataValue.isPresent()) {
            return defaultTaskIdMaxLength;
        }

        try {
            final int taskIdMaxLength = Integer.parseInt(dataValue.get());
            return taskIdMaxLength;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaultTaskIdMaxLength;
        }
    }

    @Override
    public boolean isTaskIdExist(String taskId) {
        final String sql = CommonDaoSql.createSqlToCheckTaskIdExist();

        try {
            final PreparedStatement preStmt = CONNECTION.prepareStatement(sql);
            preStmt.setString(1, taskId);

            final ResultSet resultSet = preStmt.executeQuery();
            if (resultSet.next()) {
                final int taskCount = resultSet.getInt("task_count");
                return taskCount > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
}
