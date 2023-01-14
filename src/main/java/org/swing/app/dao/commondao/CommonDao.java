package org.swing.app.dao.commondao;

import org.swing.app.dao.exception.DaoException;

import java.util.Optional;

public interface CommonDao {

    /**
     * Task id max length store in gen_master table with data_id = "01", data_cd = "01"
     * @return int task id max length
     */
    Optional<Integer> getTaskIdMaxLength() throws DaoException;

    boolean isTaskIdExist(String taskId) throws DaoException;
}
