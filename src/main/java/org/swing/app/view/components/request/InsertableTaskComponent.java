package org.swing.app.view.components.request;

import org.swing.app.dto.TaskPanelDto;

public interface InsertableTaskComponent {

    void handleForSuccessInsertTaskAction(TaskPanelDto taskPanelDto);
    void handleForFailureInsertTaskAction();
    void handleForCancelInsertTaskAction();
}
