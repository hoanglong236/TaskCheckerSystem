package org.swing.app.view.taskform.nodetask;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.taskform.TaskFormModal;
import org.swing.app.view.taskform.nodetask.factory.NodeTaskFormFactory;

public class NodeTaskFormModal extends TaskFormModal {

    public NodeTaskFormModal(FrameWrapperComponent parentFrame) {
        super(parentFrame, new NodeTaskFormFactory());
    }

    public NodeTaskFormModal(FrameWrapperComponent parentFrame, TaskDto taskDto) {
        super(parentFrame, new NodeTaskFormFactory(), taskDto);
    }
}
