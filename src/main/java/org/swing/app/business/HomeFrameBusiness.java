package org.swing.app.business;

import org.swing.app.dao.HomeFrameDao;
import org.swing.app.dao.HomeFrameDaoImpl;
import org.swing.app.dto.TaskPanelDto;

import java.util.Set;

public class HomeFrameBusiness {

    private static final HomeFrameDao HOME_FRAME_DAO = new HomeFrameDaoImpl();

    public Set<TaskPanelDto> getIncompleteRootTaskPanelDtos() {
        return HOME_FRAME_DAO.getIncompleteRootTaskPanelDtos();
    }

    public Set<TaskPanelDto> getNodeTaskPanelDtosByParentId(String parentId) {
        return HOME_FRAME_DAO.getNodeTaskPanelDtosByParentId(parentId);
    }

    public Set<TaskPanelDto> getLeafTaskPanelDtosByParentId(String parentId) {
        return HOME_FRAME_DAO.getLeafTaskPanelDtosByParentId(parentId);
    }

    public boolean deleteTaskById(String taskId) {
        return HOME_FRAME_DAO.deleteTaskById(taskId);
    }
}
