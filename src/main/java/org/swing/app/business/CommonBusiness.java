package org.swing.app.business;

import org.swing.app.dao.CommonDao;
import org.swing.app.dao.impl.CommonDaoImpl;
import org.swing.app.util.RandomString;

public class CommonBusiness {

    private static final CommonDao COMMON_DAO = new CommonDaoImpl();
    private static final int TASK_ID_MAX_LENGTH = COMMON_DAO.getTaskIdMaxLength();

    public String generateTaskId() {
        final RandomString randomString = new RandomString();

        while (true) {
            final String taskId = randomString.generateString(TASK_ID_MAX_LENGTH);
            if (!COMMON_DAO.isTaskIdExist(taskId)) {
                return taskId;
            }
        }
    }
}
