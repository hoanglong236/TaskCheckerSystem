package org.swing.app.view.taskform.leaftask.factory;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.taskform.TaskFormModal;
import org.swing.app.view.taskform.factory.TaskFormModalFactory;
import org.swing.app.view.taskform.leaftask.LeafTaskFormModal;

public class LeafTaskFormModalFactory extends TaskFormModalFactory {

    @Override
    protected TaskFormModal createAddingTaskFormModal(FrameWrapperComponent parentFrame) {
        return new LeafTaskFormModal(parentFrame);
    }

    @Override
    protected TaskFormModal createUpdatingTaskFormModal(FrameWrapperComponent parentFrame, TaskDto taskDto) {
        return new LeafTaskFormModal(parentFrame, taskDto);
    }
}
