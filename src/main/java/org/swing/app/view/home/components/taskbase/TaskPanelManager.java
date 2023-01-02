package org.swing.app.view.home.components.taskbase;

public interface TaskPanelManager {

    void insertTaskPanelHandler(byte taskType);

    void deleteTaskPanelHandler(TaskPanel taskPanel);

    void updateTaskPanelHandler(TaskPanel taskPanel);

    void loadTaskPanelContentHandler(TaskPanel taskPanel);
}
