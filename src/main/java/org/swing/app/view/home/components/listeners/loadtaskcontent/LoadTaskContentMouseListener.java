package org.swing.app.view.home.components.listeners.loadtaskcontent;

import org.swing.app.controller.ControllerResponse;
import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventObject;
import java.util.Optional;
import java.util.Set;

public class LoadTaskContentMouseListener implements MouseListener {

    private final HomeFrameController homeFrameController;
    private final LoadTaskContentListenerSubject loadTaskContentListenerSubject;

    public LoadTaskContentMouseListener(HomeFrameController homeFrameController,
            LoadTaskContentListenerSubject loadTaskContentListenerSubject) {

        this.homeFrameController = homeFrameController;
        this.loadTaskContentListenerSubject = loadTaskContentListenerSubject;
    }

    private void handleLoadTaskContentResponse(EventObject eventObject, ControllerResponse response) {
        final MessageLoader messageLoader = MessageLoader.getInstance();

        if (response.getResponseType() == ControllerResponse.RESPONSE_TYPE_SUCCESS) {
            final Optional<Object> masterTaskPanelDtoOptional = response.getData(
                    messageLoader.getMessage("master.task.panel.dto"));

            if (!masterTaskPanelDtoOptional.isPresent()) {
                this.loadTaskContentListenerSubject.onLoadTaskContentFailure(eventObject);
                return;
            }
            final TaskPanelDto masterTaskPanelDto = (TaskPanelDto) masterTaskPanelDtoOptional.get();

            final Optional<Object> childTaskPanelDtosOptional = response.getData(
                    messageLoader.getMessage("child.task.panel.dtos"));
            final Set<TaskPanelDto> childTaskPanelDtos = (Set<TaskPanelDto>) childTaskPanelDtosOptional.get();

            this.loadTaskContentListenerSubject.onLoadTaskContentSuccess(eventObject,
                    masterTaskPanelDto, childTaskPanelDtos);
            return;
        }
        if (response.getResponseType() == ControllerResponse.RESPONSE_TYPE_ERROR) {
            this.loadTaskContentListenerSubject.onLoadTaskContentFailure(eventObject);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        final Optional<String> taskIdToLoadContentOptional = this.loadTaskContentListenerSubject
                .getTaskIdToLoadContent(e);

        if (!taskIdToLoadContentOptional.isPresent()) {
            return;
        }

        final String taskIdToLoadContent = taskIdToLoadContentOptional.get();
        final ControllerResponse response = this.homeFrameController.requestLoadTaskContent(taskIdToLoadContent);
        handleLoadTaskContentResponse(e, response);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
