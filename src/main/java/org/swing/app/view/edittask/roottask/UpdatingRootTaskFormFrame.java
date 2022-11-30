package org.swing.app.view.edittask.roottask;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.edittask.TaskFormFrame;

public class UpdatingRootTaskFormFrame extends TaskFormFrame {

    public UpdatingRootTaskFormFrame(TaskDto taskDto) {
        super(new RootTaskFormFactory(), taskDto);
    }
}