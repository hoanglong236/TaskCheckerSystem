package org.swing.app.view.taskform.roottask.factory;

import org.swing.app.controller.TaskFormModalController;
import org.swing.app.dto.TaskDto;
import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.taskform.TaskFormModal;
import org.swing.app.view.taskform.factory.TaskFormModalFactory;
import org.swing.app.view.taskform.roottask.RootTaskFormModal;

public class RootTaskFormModalFactory implements TaskFormModalFactory {

    @Override
    public TaskFormModal createAddingTaskFormModal(FrameWrapperComponent parentFrame,
            TaskFormModalController taskFormModalController) {

        return new RootTaskFormModal(parentFrame, taskFormModalController);
    }

    @Override
    public TaskFormModal createUpdatingTaskFormModal(FrameWrapperComponent parentFrame,
            TaskFormModalController taskFormModalController, TaskDto taskDto) {

        return new RootTaskFormModal(parentFrame, taskFormModalController, taskDto);
    }
}
