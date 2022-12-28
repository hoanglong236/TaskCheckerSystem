package org.swing.app.view.components.request;

import org.swing.app.dto.TaskPanelDto;

public interface UpdatableTaskComponent extends RequestableComponent {

    void handlerForResultOfUpdateTaskAction(boolean isSuccess, TaskPanelDto taskPanelDto);
}
