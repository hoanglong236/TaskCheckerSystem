package org.swing.app.view.home.comparetor;

import org.swing.app.dto.TaskDto;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.taskpanel.TaskPanel;

import java.time.LocalDateTime;

// TODO: handle this
public class TaskPanelUpdateDateComparator extends TaskPanelComparator {

    @Override
    public int compare(TaskPanel o1, TaskPanel o2) {
        final TaskPanelDto taskPanelDto1 = o1.getTaskPanelDto();
        final TaskPanelDto taskPanelDto2 = o2.getTaskPanelDto();

        final TaskDto taskDto1 = taskPanelDto1.getTaskDto();
        final TaskDto taskDto2 = taskPanelDto2.getTaskDto();

        final LocalDateTime dateTime1 = taskDto1.getUpdatedAt();
        final LocalDateTime dateTime2 = taskDto2.getUpdatedAt();

        if (dateTime1 == null && dateTime2 == null) {
            return compareTaskId(o1, o2);
        }

        final byte lessThan = -1;
        final byte moreThan = 1;

        if (dateTime1 == null) {
            return moreThan;
        }
        if (dateTime2 == null) {
            return lessThan;
        }

        if (dateTime1.isBefore(dateTime2)) {
            return lessThan;
        }
        if (dateTime1.isAfter(dateTime2)) {
            return moreThan;
        }

        return compareTaskId(o1, o2);
    }
}
