package org.swing.app.view.components.request;

public interface DeletableTaskComponent extends AbleToRequestComponent {

    void handlerForResultOfDeleteTaskAction(boolean isSuccess);
}
