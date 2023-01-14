package org.swing.app.view.taskform.taskformpanel.factory.impl;

import org.swing.app.view.taskform.taskformpanel.TaskFormPanel;
import org.swing.app.view.taskform.taskformpanel.RootTaskFormPanel;
import org.swing.app.view.taskform.taskformpanel.factory.TaskFormPanelFactory;

public class RootTaskFormPanelFactory implements TaskFormPanelFactory {

    @Override
    public TaskFormPanel createTaskFormPanel() {
        return new RootTaskFormPanel();
    }
}