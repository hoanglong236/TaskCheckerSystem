package org.swing.app.view.home.comparetor;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.home.components.taskpanel.TaskPanel;

public class TaskPanelUpdateDateComparator extends TaskPanelComparator {

    @Override
    public int compare(TaskPanel o1, TaskPanel o2) {
        final TaskDto taskDto1 = o1.getTaskDto();
        final TaskDto taskDto2 = o2.getTaskDto();

        final int updateDateCompareResult = TaskDtoPropertyComparator.compareUpdateDate(taskDto1, taskDto2);
        if (updateDateCompareResult == 0) {
            return TaskDtoPropertyComparator.compareId(taskDto1, taskDto2);
        }
        return updateDateCompareResult;
    }
}
