package org.swing.app.dao;

public interface CommonDao {

    /**
     * Task id max length store in gen_master table with data_id = "01", data_cd = "01"
     * @return int task id max length
     */
    int getTaskIdMaxLength();

    boolean isTaskIdExist(String taskId);
}
