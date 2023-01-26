package org.swing.app.view.taskform.taskformmodal;

import org.swing.app.view.components.ViewComponent;
import org.swing.app.view.taskform.taskformpanel.factory.TaskFormPanelFactory;
import org.swing.app.view.taskform.taskformpanel.factory.impl.RootTaskFormPanelFactory;

public class RootTaskFormModal extends TaskFormModal {

    public RootTaskFormModal(ViewComponent windowComponent) {
        super(windowComponent);
    }

    @Override
    protected TaskFormPanelFactory createTaskFormPanelFactory() {
        return new RootTaskFormPanelFactory();
    }
}
