package org.swing.app.view.components.request;

public interface DeletableTaskComponent extends RequestableComponent {

    boolean showDeleteTaskConfirmDialog();
    void handlerForResultOfDeleteTaskAction(boolean isSuccess);
    void handlerForCancelDeleteTaskAction();
}
