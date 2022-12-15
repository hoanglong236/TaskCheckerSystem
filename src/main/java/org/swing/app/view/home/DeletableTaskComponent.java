package org.swing.app.view.home;

public interface DeletableTaskComponent {

    String getTaskId();
    void handlerForResultOfDeleteTaskAction(boolean isSuccess);
}
