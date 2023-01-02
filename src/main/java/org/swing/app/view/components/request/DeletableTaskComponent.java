package org.swing.app.view.components.request;

public interface DeletableTaskComponent {

    void handleForSuccessDeleteTaskAction();
    void handleForFailureDeleteTaskAction();
    void handleForCancelDeleteTaskAction();
}
