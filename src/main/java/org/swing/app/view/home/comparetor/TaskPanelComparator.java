package org.swing.app.view.home.comparetor;

import org.swing.app.view.home.components.taskbase.TaskPanel;

import java.util.Comparator;

public abstract class TaskPanelComparator implements Comparator<TaskPanel> {

    protected int compareTaskId(TaskPanel o1, TaskPanel o2) {
        final String taskId1 = o1.getTaskId();
        final String taskId2 = o2.getTaskId();

        return taskId1.compareTo(taskId2);
    }
}
