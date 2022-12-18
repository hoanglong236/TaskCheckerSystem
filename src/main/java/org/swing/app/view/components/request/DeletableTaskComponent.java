package org.swing.app.view.components.request;

public interface DeletableTaskComponent extends AbleToRequestComponent {

    String getTaskId();
    void handlerForResultOfDeleteTaskAction(boolean isSuccess);
}
