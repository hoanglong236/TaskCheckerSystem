package org.swing.app.view.components.request;

import org.swing.app.dto.TaskPanelDto;

public interface UpdatableTaskComponent {

    void handleForSuccessUpdateTaskAction(TaskPanelDto taskPanelDto);

    void handleForFailureUpdateTaskAction();

    void handleForCancelUpdateTaskAction();

    void handleForDeniedUpdateTaskAction();

    void handleForNothingToUpdateTaskAction();
}
