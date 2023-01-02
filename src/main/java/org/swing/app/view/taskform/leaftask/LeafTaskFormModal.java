package org.swing.app.view.taskform.leaftask;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.taskform.TaskFormModal;
import org.swing.app.view.taskform.leaftask.factory.LeafTaskFormFactory;

public class LeafTaskFormModal extends TaskFormModal {

    public LeafTaskFormModal(FrameWrapperComponent parentFrame) {
        super(parentFrame, new LeafTaskFormFactory());
    }

    public LeafTaskFormModal(FrameWrapperComponent parentFrame, TaskDto taskDto) {
        super(parentFrame, new LeafTaskFormFactory(), taskDto);
    }
}
