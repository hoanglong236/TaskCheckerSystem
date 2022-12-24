package org.swing.app.view.home.comparetor;

import org.swing.app.view.home.components.taskbase.TaskPanel;

import java.time.LocalDateTime;

public class TaskPanelCreateDateComparator extends TaskPanelComparator {

    @Override
    public int compare(TaskPanel o1, TaskPanel o2) {
        final LocalDateTime dateTime1 = o1.getCreateDateTime();
        final LocalDateTime dateTime2 = o2.getCreateDateTime();

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
