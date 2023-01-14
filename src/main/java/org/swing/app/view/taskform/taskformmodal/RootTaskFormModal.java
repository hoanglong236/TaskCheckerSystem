package org.swing.app.view.taskform.taskformmodal;

import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.taskform.taskformpanel.factory.impl.RootTaskFormPanelFactory;

public class RootTaskFormModal extends TaskFormModal {

    public RootTaskFormModal(FrameWrapperComponent parentFrame) {
        super(parentFrame, new RootTaskFormPanelFactory());
    }
}
