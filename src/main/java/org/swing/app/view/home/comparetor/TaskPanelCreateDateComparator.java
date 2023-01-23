package org.swing.app.view.home.comparetor;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.home.components.taskpanel.TaskPanel;

/**
 * Sort TaskPanel by createdAt property (this property is in TaskDto).
 * All TaskPanels with a null value of createdAt property will be at the end of this collection
 */
public class TaskPanelCreateDateComparator extends TaskPanelComparator {

    @Override
    public int compare(TaskPanel o1, TaskPanel o2) {
        final TaskDto taskDto1 = o1.getTaskDto();
        final TaskDto taskDto2 = o2.getTaskDto();

        final int createDateCompareResult = TaskDtoPropertyComparator.compareCreateDate(taskDto1, taskDto2);
        if (createDateCompareResult == 0) {
            return TaskDtoPropertyComparator.compareId(taskDto1, taskDto2);
        }
        return createDateCompareResult;
    }
}
