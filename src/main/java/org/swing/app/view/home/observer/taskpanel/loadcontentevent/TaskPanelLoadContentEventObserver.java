package org.swing.app.view.home.observer.taskpanel.loadcontentevent;

import org.swing.app.dto.TaskDto;

public interface TaskPanelLoadContentEventObserver {

    void handleUpdateMasterTask(TaskDto masterTaskDto);
}
