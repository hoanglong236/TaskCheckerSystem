package org.swing.app.view.components.request;

import org.swing.app.dto.TaskPanelDto;

public interface UpdatableTaskComponent extends AbleToRequestComponent {

    String getTaskId();
    void handlerForResultOfUpdateTaskAction(boolean isSuccess, TaskPanelDto taskPanelDto);
}
