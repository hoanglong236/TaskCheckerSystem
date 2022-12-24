package org.swing.app.view.home.components.taskbase;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.ui.Button;
import org.swing.app.view.components.ui.Label;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.home.HomeWrapperComponent;
import org.swing.app.view.home.components.factory.TaskPanelManagerComponentFactory;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public abstract class TaskContentPanel extends HomeWrapperComponent implements ActionListener {

    private static final byte HORIZONTAL_GAP = ViewConstant.SMALL_H_GAP;
    private static final byte VERTICAL_GAP = ViewConstant.SMALL_V_GAP;
    private static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT, HORIZONTAL_GAP, VERTICAL_GAP);

    private Label titleLabel;
    private TaskPanelManagerComponent taskPanelManagerComponent;
    private Button addNewTaskButton;

    private final TaskPanelManagerComponentFactory taskPanelManagerComponentFactory;

    public TaskContentPanel(HomeFrameController homeFrameController,
            TaskPanelManagerComponentFactory taskPanelManagerComponentFactory,
            String title, Set<TaskPanelDto> taskPanelDtos) {

        super(homeFrameController);
        this.taskPanelManagerComponentFactory = taskPanelManagerComponentFactory;
        setLayout(MAIN_LAYOUT);
        init(title, taskPanelDtos);
    }

    protected abstract byte getTaskTypeToRequest();

    private void initTitleLabel(String title) {
        this.titleLabel = UIComponentFactory.createLabel(title);
    }

    private void initTaskPanelManagerComponent(Set<TaskPanelDto> taskPanelDtos) {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        final String taskPanelManagerComponentTitle = messageLoader.getMessage("task.panel.manager.title");

        this.taskPanelManagerComponent = this.taskPanelManagerComponentFactory.createTaskPanelManagerComponent(
                this.homeFrameController, taskPanelManagerComponentTitle, taskPanelDtos);
    }

    private void initAddNewTaskButton() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.addNewTaskButton = UIComponentFactory.createButton(
                messageLoader.getMessage("add.task.component.text"));
        this.addNewTaskButton.addActionListener(this);
    }

    private void init(String title, Set<TaskPanelDto> taskPanelDtos) {
        initTitleLabel(title);
        addChildComponent(this.titleLabel);

        initTaskPanelManagerComponent(taskPanelDtos);
        addChildComponent(this.taskPanelManagerComponent);

        initAddNewTaskButton();
        addChildComponent(this.addNewTaskButton);
    }

    @Override
    protected void loadChildComponentsSize() {
        final int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;

        final int maxChildComponentWidth = availableWidth - HORIZONTAL_GAP;

        final byte titleLabelHeight = 100;
        this.childComponentSizeMap.put(this.titleLabel, new Dimension(maxChildComponentWidth, titleLabelHeight));

        final int taskPanelManagerComponentHeight = (int) (((float) 0.8 * availableHeight) - VERTICAL_GAP);
        this.childComponentSizeMap.put(this.taskPanelManagerComponent,
                new Dimension(maxChildComponentWidth, taskPanelManagerComponentHeight));

        final int addNewTaskButtonWidth = 100;
        final int addNewTaskButtonHeight = 50;
        this.childComponentSizeMap.put(this.addNewTaskButton,
                new Dimension(addNewTaskButtonWidth, addNewTaskButtonHeight));
    }

    @Override
    protected void setNotResizableChildComponents() {
        this.titleLabel.setResizable(true);
        this.taskPanelManagerComponent.setResizable(true);
        this.addNewTaskButton.setResizable(false);
    }

    private void onActionPerformedForAddNewTaskComponent() {
        this.taskPanelManagerComponent.insertTaskPanelHandler(getTaskTypeToRequest());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final Object eventSource = e.getSource();

        if (eventSource == this.addNewTaskButton) {
            onActionPerformedForAddNewTaskComponent();
            return;
        }
        throw new IllegalArgumentException();
    }
}
