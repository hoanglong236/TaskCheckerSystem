package org.swing.app.view.taskform.nodetask.factory;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.taskform.TaskFormModal;
import org.swing.app.view.taskform.factory.TaskFormModalFactory;
import org.swing.app.view.taskform.nodetask.NodeTaskFormModal;

public class NodeTaskFormModalFactory extends TaskFormModalFactory {

    @Override
    protected TaskFormModal createAddingTaskFormModal(FrameWrapperComponent parentFrame) {
        return new NodeTaskFormModal(parentFrame);
    }

    @Override
    protected TaskFormModal createUpdatingTaskFormModal(FrameWrapperComponent parentFrame, TaskDto taskDto) {
        return new NodeTaskFormModal(parentFrame, taskDto);
    }
}
