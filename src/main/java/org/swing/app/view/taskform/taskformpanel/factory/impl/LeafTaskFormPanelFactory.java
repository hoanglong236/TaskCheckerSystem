package org.swing.app.view.taskform.taskformpanel.factory.impl;

import org.swing.app.view.taskform.taskformpanel.TaskFormPanel;
import org.swing.app.view.taskform.taskformpanel.LeafTaskFormPanel;
import org.swing.app.view.taskform.taskformpanel.factory.TaskFormPanelFactory;

public class LeafTaskFormPanelFactory implements TaskFormPanelFactory {

    @Override
    public TaskFormPanel createTaskFormPanel() {
        return new LeafTaskFormPanel();
    }
}
