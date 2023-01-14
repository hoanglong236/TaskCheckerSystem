package org.swing.app.view.taskform.taskformpanel.factory.impl;

import org.swing.app.view.taskform.taskformpanel.TaskFormPanel;
import org.swing.app.view.taskform.taskformpanel.NodeTaskFormPanel;
import org.swing.app.view.taskform.taskformpanel.factory.TaskFormPanelFactory;

public class NodeTaskFormPanelFactory implements TaskFormPanelFactory {

    @Override
    public TaskFormPanel createTaskFormPanel() {
        return new NodeTaskFormPanel();
    }
}
