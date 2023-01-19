package org.swing.app.view.home.observer;

import org.swing.app.dto.TaskDto;
import org.swing.app.dto.TaskPanelDto;

public interface TaskPanelUpdateEventSubject extends TaskPanelModificationEventSubject {

    void notifyObserversToUpdate(TaskPanelDto updatedTaskPanelDto);

    void notifyObserversToUpdateTask(TaskDto updatedTaskDto);

    void notifyObserversToUpdateTaskCompletionRate(int completedChildTaskCount, int childTaskCount);
}
