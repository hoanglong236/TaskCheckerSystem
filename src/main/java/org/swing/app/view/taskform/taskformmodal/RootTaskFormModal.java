package org.swing.app.view.taskform.taskformmodal;

import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.taskform.taskformpanel.factory.TaskFormPanelFactory;
import org.swing.app.view.taskform.taskformpanel.factory.impl.RootTaskFormPanelFactory;

public class RootTaskFormModal extends TaskFormModal {

    public RootTaskFormModal(FrameWrapperComponent parentFrame) {
        super(parentFrame);
    }

    @Override
    protected TaskFormPanelFactory createTaskFormPanelFactory() {
        return new RootTaskFormPanelFactory();
    }
}
