package org.swing.app.view.home.components.listeners.subjects;

import java.util.EventObject;
import java.util.Optional;

public interface DeleteTaskListenerSubject {

    Optional<String> getTaskIdToDelete(EventObject eventObject);

    void onDeleteTaskSuccess(EventObject eventObject);
    void onDeleteTaskFailure(EventObject eventObject);
}
