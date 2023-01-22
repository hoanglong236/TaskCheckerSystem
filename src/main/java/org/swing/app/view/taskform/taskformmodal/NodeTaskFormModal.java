package org.swing.app.view.taskform.taskformmodal;

import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.taskform.taskformpanel.factory.TaskFormPanelFactory;
import org.swing.app.view.taskform.taskformpanel.factory.impl.NodeTaskFormPanelFactory;

public class NodeTaskFormModal extends TaskFormModal {

    public NodeTaskFormModal(FrameWrapperComponent parentFrame) {
        super(parentFrame);
    }

    @Override
    protected TaskFormPanelFactory createTaskFormPanelFactory() {
        return new NodeTaskFormPanelFactory();
    }
}
