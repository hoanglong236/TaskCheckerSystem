package org.swing.app.view.taskform.taskformmodal;

import org.swing.app.view.components.ViewComponent;
import org.swing.app.view.taskform.taskformpanel.factory.TaskFormPanelFactory;
import org.swing.app.view.taskform.taskformpanel.factory.impl.NodeTaskFormPanelFactory;

public class NodeTaskFormModal extends TaskFormModal {

    public NodeTaskFormModal(ViewComponent windowComponent) {
        super(windowComponent);
    }

    @Override
    protected TaskFormPanelFactory createTaskFormPanelFactory() {
        return new NodeTaskFormPanelFactory();
    }
}
