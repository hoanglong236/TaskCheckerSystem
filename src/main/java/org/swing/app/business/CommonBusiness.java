package org.swing.app.business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.swing.app.business.exception.BusinessException;
import org.swing.app.common.Constant;
import org.swing.app.dao.CommonDao;
import org.swing.app.dao.exception.DaoException;
import org.swing.app.dao.impl.CommonDaoImpl;
import org.swing.app.util.RandomString;

import java.util.Optional;

public class CommonBusiness {

    private static final Logger LOGGER = LogManager.getLogger(CommonBusiness.class);

    private static final CommonDao COMMON_DAO = new CommonDaoImpl();

    private final int taskIdMaxLength;

    public CommonBusiness() {
        this.taskIdMaxLength = getTaskIdMaxLength();
    }

    private int getTaskIdMaxLength() {
        try {
            final Optional<Integer> taskIdMaxLengthOptional = COMMON_DAO.getTaskIdMaxLength();

            if (taskIdMaxLengthOptional.isPresent()) {
                return taskIdMaxLengthOptional.get();
            }

            return Constant.DEFAULT_TASK_ID_MAX_LENGTH;
        } catch (DaoException e) {
            LOGGER.error("Method: initTaskIdMaxLength", e);
            return Constant.DEFAULT_TASK_ID_MAX_LENGTH;
        }
    }

    public String generateTaskId() throws BusinessException {
        final RandomString randomString = new RandomString();

        while (true) {
            final String taskId = randomString.generateString(this.taskIdMaxLength);

            try {
                if (!COMMON_DAO.isTaskIdExist(taskId)) {
                    return taskId;
                }
            } catch (DaoException e) {
                LOGGER.error("Method: generateTaskId", e);
                throw new BusinessException(e);
            }
        }
    }
}
