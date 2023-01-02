package org.swing.app.view.taskform.roottask;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.taskform.TaskFormModal;
import org.swing.app.view.taskform.roottask.factory.RootTaskFormFactory;

public class RootTaskFormModal extends TaskFormModal {

    public RootTaskFormModal(FrameWrapperComponent parentFrame) {
        super(parentFrame, new RootTaskFormFactory());
    }

    public RootTaskFormModal(FrameWrapperComponent parentFrame, TaskDto taskDto) {
        super(parentFrame, new RootTaskFormFactory(), taskDto);
    }
}
