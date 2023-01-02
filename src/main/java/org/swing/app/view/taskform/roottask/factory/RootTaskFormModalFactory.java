package org.swing.app.view.taskform.roottask.factory;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.taskform.TaskFormModal;
import org.swing.app.view.taskform.factory.TaskFormModalFactory;
import org.swing.app.view.taskform.roottask.RootTaskFormModal;

public class RootTaskFormModalFactory extends TaskFormModalFactory {

    @Override
    protected TaskFormModal createAddingTaskFormModal(FrameWrapperComponent parentFrame) {
        return new RootTaskFormModal(parentFrame);
    }

    @Override
    public TaskFormModal createUpdatingTaskFormModal(FrameWrapperComponent parentFrame, TaskDto taskDto) {
        return new RootTaskFormModal(parentFrame, taskDto);
    }
}
