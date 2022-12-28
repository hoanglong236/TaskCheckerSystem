package org.swing.app.view.components.request;

import org.swing.app.dto.TaskPanelDto;

public interface InsertableTaskComponent extends RequestableComponent {

    void handlerForResultOfInsertTaskAction(boolean isSuccess, TaskPanelDto taskPanelDto);
}
