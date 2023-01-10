package org.swing.app.business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.swing.app.business.exception.BusinessException;
import org.swing.app.dao.HomeFrameDao;
import org.swing.app.dao.exception.DaoException;
import org.swing.app.dao.impl.HomeFrameDaoImpl;
import org.swing.app.dto.TaskDto;
import org.swing.app.dto.TaskPanelDto;

import java.util.Set;

public class HomeFrameBusiness {

    private static final Logger LOGGER = LogManager.getLogger(HomeFrameBusiness.class);

    private final HomeFrameDao homeFrameDao;

    public HomeFrameBusiness() throws BusinessException {
        try {
            this.homeFrameDao = new HomeFrameDaoImpl();
        } catch (DaoException e) {
            LOGGER.error("Constructor: HomeFrameBusiness", e);
            throw new BusinessException(e);
        }
    }

    public void insertTaskByDto(TaskDto taskDto) throws BusinessException {
        try {
            this.homeFrameDao.insertTaskByDto(taskDto);
        } catch (DaoException e) {
            LOGGER.error("Method: insertTaskByDto");
            throw new BusinessException(e);
        }
    }

    public void updateTaskByDto(TaskDto taskDto) throws BusinessException {
        try {
            this.homeFrameDao.updateTaskByDto(taskDto);
        } catch (DaoException e) {
            LOGGER.error("Method: updateTaskByDto", e);
            throw new BusinessException(e);
        }
    }

    public void deleteTaskById(String taskId) throws BusinessException {
        try {
            this.homeFrameDao.deleteTaskById(taskId);
        } catch (DaoException e) {
            LOGGER.error("Method: deleteTaskById", e);
            throw new BusinessException(e);
        }
    }

    public TaskPanelDto getTaskPanelDtoById(String taskId) throws BusinessException {
        try {
            return this.homeFrameDao.getTaskPanelDtoById(taskId);
        } catch (DaoException e) {
            LOGGER.error("Method: getTaskPanelDtoById", e);
            throw new BusinessException(e);
        }
    }

    public TaskDto getTaskDtoById(String taskId) throws BusinessException {
        try {
            return this.homeFrameDao.getTaskDtoById(taskId);
        } catch (DaoException e) {
            LOGGER.error("Method: getTaskDtoById", e);
            throw new BusinessException(e);
        }
    }

    public Set<TaskPanelDto> getIncompleteRootTaskPanelDtos() throws BusinessException {
        try {
            return this.homeFrameDao.getIncompleteRootTaskPanelDtos();
        } catch (DaoException e) {
            LOGGER.error("Method: getIncompleteRootTaskPanelDtos", e);
            throw new BusinessException(e);
        }
    }

    public Set<TaskPanelDto> getTaskPanelDtosByParentId(String parentId) throws BusinessException {
        try {
            return this.homeFrameDao.getTaskPanelDtosByParentId(parentId);
        } catch (DaoException e) {
            LOGGER.error("Method: getTaskPanelDtosByParentId", e);
            throw new BusinessException(e);
        }
    }
}
