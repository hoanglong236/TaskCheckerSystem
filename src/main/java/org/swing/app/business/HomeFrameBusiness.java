package org.swing.app.business;

import org.swing.app.dao.HomeFrameDao;
import org.swing.app.dao.impl.HomeFrameDaoImpl;
import org.swing.app.dto.TaskPanelDto;

import java.util.Set;

public class HomeFrameBusiness {

    private static final HomeFrameDao HOME_FRAME_DAO = new HomeFrameDaoImpl();

    public Set<TaskPanelDto> getIncompleteRootTaskPanelDtos() {
        return HOME_FRAME_DAO.getIncompleteRootTaskPanelDtos();
    }

    public Set<TaskPanelDto> getTaskPanelDtosByParentId(String parentId) {
        return HOME_FRAME_DAO.getTaskPanelDtosByParentId(parentId);
    }

    public boolean deleteTaskById(String taskId) {
        return HOME_FRAME_DAO.deleteTaskById(taskId);
    }

    public TaskPanelDto getTaskPanelDtoById(String taskId) {
        return HOME_FRAME_DAO.getTaskPanelDtoById(taskId);
    }
}
