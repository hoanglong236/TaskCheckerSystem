package org.swing.app.view.home;

import org.swing.app.dto.TaskPanelDto;

public interface InsertableTaskComponent extends AbleToRequestComponent {

    void handlerForResultOfInsertTaskAction(boolean isSuccess, TaskPanelDto taskPanelDto);
}
