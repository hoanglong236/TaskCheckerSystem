package org.swing.app.view.home.components.roottask;

import org.swing.app.controller.ControllerBase;
import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.components.ui.Button;
import org.swing.app.view.home.components.taskbase.TaskContentPanel;
import org.swing.app.view.home.components.nodetask.factory.NodeTaskPanelManagerComponentFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class RootTaskContentPanel extends TaskContentPanel implements ActionListener {

    public RootTaskContentPanel(HomeFrameController homeFrameController,
            String title, Set<TaskPanelDto> taskPanelDtos) {

        super(homeFrameController, new NodeTaskPanelManagerComponentFactory(), title, taskPanelDtos);
    }

    @Override
    protected void initAddNewTaskComponent() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.addNewTaskComponent = UIComponentFactory.createButton(
                messageLoader.getMessage("add.task.component.text"));
        ((Button) this.addNewTaskComponent).addActionListener(this);
    }

    private void onActionPerformedForAddNewTaskComponent() {
        final boolean requestSuccess = this.homeFrameController.requestAddNewTaskPanel(
                ControllerBase.ROOT_TASK_TYPE, this);

        if (!requestSuccess) {
            requestFailureHandler();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final Object eventSource = e.getSource();

        if (eventSource == this.addNewTaskComponent) {
            onActionPerformedForAddNewTaskComponent();
            return;
        }
        throw new IllegalArgumentException();
    }
}
