package org.swing.app.business;

import org.swing.app.dao.CommonDao;
import org.swing.app.dao.CommonDaoImpl;
import org.swing.app.dto.TaskDto;

public class CommonBusiness {

    private static final CommonDao COMMON_DAO = new CommonDaoImpl();

    public boolean insertTaskByDto(TaskDto taskDto) {
        return COMMON_DAO.insertTaskByDto(taskDto);
    }
}
