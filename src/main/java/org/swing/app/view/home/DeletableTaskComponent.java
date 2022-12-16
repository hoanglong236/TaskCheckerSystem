package org.swing.app.view.home;

public interface DeletableTaskComponent extends AbleToRequestComponent {

    String getTaskId();
    void handlerForResultOfDeleteTaskAction(boolean isSuccess);
}
