package org.swing.app.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.swing.app.dao.CommonDao;
import org.swing.app.dao.connection.MySQLConnection;
import org.swing.app.dao.exception.DaoException;
import org.swing.app.dao.sql.CommonDaoSql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CommonDaoImpl implements CommonDao {

    private static final Logger LOGGER = LogManager.getLogger(CommonDaoImpl.class);

    private static final Connection CONNECTION = MySQLConnection.getConnection();

    private Optional<String> getDataValueFromGenMaster(String dataId, String dataCd) throws SQLException {
        final String sql = CommonDaoSql.createSqlToGetDataValueFromGenMaster();
        String dataValue = null;

        final PreparedStatement preStmt = CONNECTION.prepareStatement(sql);
        preStmt.setString(1, dataId);
        preStmt.setString(2, dataCd);

        final ResultSet resultSet = preStmt.executeQuery();
        if (resultSet.next()) {
            dataValue = resultSet.getString("data_value");
        }

        return Optional.ofNullable(dataValue);
    }

    private Optional<String> getDataNameFromGenMaster(String dataId, String dataCd) throws SQLException {
        final String sql = CommonDaoSql.createSqlToGetDataNameFromGenMaster();
        String dataName = null;

        final PreparedStatement preStmt = CONNECTION.prepareStatement(sql);
        preStmt.setString(1, dataId);
        preStmt.setString(2, dataCd);

        final ResultSet resultSet = preStmt.executeQuery();
        if (resultSet.next()) {
            dataName = resultSet.getString("data_name");
        }

        return Optional.ofNullable(dataName);
    }

    @Override
    public Optional<Integer> getTaskIdMaxLength() throws DaoException {
        final String dataId = "01";
        final String dataCd = "02";

        try {
            final Optional<String> dataValue = getDataValueFromGenMaster(dataId, dataCd);

            if (dataValue.isPresent()) {
                final Integer taskIdMaxLength = Integer.parseInt(dataValue.get());
                return Optional.of(taskIdMaxLength);
            }

            return Optional.empty();
        } catch (SQLException | NumberFormatException e) {
            LOGGER.error("Method: getTaskIdMaxLength", e);
            throw new DaoException(e);
        }
    }

    @Override
    public boolean isTaskIdExist(String taskId) throws DaoException {
        final String sql = CommonDaoSql.createSqlToCheckTaskIdExist();

        try {
            final PreparedStatement preStmt = CONNECTION.prepareStatement(sql);
            preStmt.setString(1, taskId);

            final ResultSet resultSet = preStmt.executeQuery();
            if (resultSet.next()) {
                final int taskCount = resultSet.getInt("task_count");
                return taskCount > 0;
            }

            return true;
        } catch (SQLException e) {
            LOGGER.error("Method: isTaskIdExist", e);
            throw new DaoException(e);
        }
    }
}
