package org.swing.app.view.home.comparetor;

import org.swing.app.dto.TaskDto;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.taskpanel.TaskPanel;

public class TaskPanelCreateDateComparator extends TaskPanelComparator {

    @Override
    public int compare(TaskPanel o1, TaskPanel o2) {
        final TaskPanelDto taskPanelDto1 = o1.getTaskPanelDto();
        final TaskPanelDto taskPanelDto2 = o2.getTaskPanelDto();

        final TaskDto taskDto1 = taskPanelDto1.getTaskDto();
        final TaskDto taskDto2 = taskPanelDto2.getTaskDto();

        final int createDateCompareResult = TaskDtoPropertyComparator.compareCreateDate(taskDto1, taskDto2);
        if (createDateCompareResult == 0) {
            return TaskDtoPropertyComparator.compareId(taskDto1, taskDto2);
        }
        return createDateCompareResult;
    }
}
