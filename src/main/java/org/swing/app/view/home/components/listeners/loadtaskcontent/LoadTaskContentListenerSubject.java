package org.swing.app.view.home.components.listeners.loadtaskcontent;

import org.swing.app.dto.TaskPanelDto;

import java.util.EventObject;
import java.util.Optional;
import java.util.Set;

public interface LoadTaskContentListenerSubject {

    Optional<String> getTaskIdToLoadContent(EventObject eventObject);

    void onLoadTaskContentSuccess(EventObject eventObject,
            TaskPanelDto masterTaskPanelDto, Set<TaskPanelDto> childTaskPanelDtos);

    void onLoadTaskContentFailure(EventObject eventObject);
}
