package org.swing.app.business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.swing.app.business.exception.BusinessException;
import org.swing.app.common.Constants;
import org.swing.app.dao.commondao.CommonDao;
import org.swing.app.dao.commondao.CommonDaoImpl;
import org.swing.app.dao.exception.DaoException;
import org.swing.app.util.RandomString;

import java.util.Optional;

public class CommonBusiness {

    private static final Logger LOGGER = LogManager.getLogger(CommonBusiness.class);

    private final CommonDao commonDao;

    private final int taskIdMaxLength;

    public CommonBusiness() throws BusinessException {
        try {
            this.commonDao = new CommonDaoImpl();
        } catch (DaoException e) {
            LOGGER.error("Constructor: CommonBusiness", e);
            throw new BusinessException(e);
        }
        this.taskIdMaxLength = getTaskIdMaxLength();
    }

    private int getTaskIdMaxLength() {
        try {
            final Optional<Integer> taskIdMaxLengthOptional = this.commonDao.getTaskIdMaxLength();

            if (taskIdMaxLengthOptional.isPresent()) {
                return taskIdMaxLengthOptional.get();
            }
        } catch (DaoException e) {
            LOGGER.error("Method: initTaskIdMaxLength", e);
        }

        return Constants.DEFAULT_TASK_ID_MAX_LENGTH;
    }

    public String generateTaskId() throws BusinessException {
        final RandomString randomString = new RandomString();

        while (true) {
            final String taskId = randomString.generateString(this.taskIdMaxLength);

            try {
                if (!this.commonDao.isTaskIdExist(taskId)) {
                    return taskId;
                }
            } catch (DaoException e) {
                LOGGER.error("Method: generateTaskId", e);
                throw new BusinessException(e);
            }
        }
    }
}
