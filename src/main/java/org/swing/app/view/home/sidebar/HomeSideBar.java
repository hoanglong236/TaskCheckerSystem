package org.swing.app.view.home.sidebar;

import org.swing.app.controller.ControllerBase;
import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.OptionPane;
import org.swing.app.view.components.ui.Button;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.home.HomeWrapperComponent;
import org.swing.app.view.components.request.InsertableTaskComponent;
import org.swing.app.view.home.components.taskbase.TaskPanel;
import org.swing.app.view.home.components.taskbase.TaskPanelContainer;
import org.swing.app.view.home.components.factory.TaskPanelContainerFactory;
import org.swing.app.view.home.components.factory.TaskPanelFactory;
import org.swing.app.view.home.components.roottask.factory.RootTaskPanelContainerFactory;
import org.swing.app.view.home.components.roottask.factory.RootTaskPanelFactory;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class HomeSideBar extends HomeWrapperComponent implements ActionListener, InsertableTaskComponent {

    private static final byte HORIZONTAL_GAP = ViewConstant.MEDIUM_H_GAP;
    private static final byte VERTICAL_GAP = ViewConstant.MEDIUM_V_GAP;
    private static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.CENTER, HORIZONTAL_GAP, VERTICAL_GAP);

    private TaskPanel dailyTaskPanel;
    private TaskPanelContainer taskPanelContainer;
    private Button addNewTaskBtn;

    private final TaskPanelFactory taskPanelFactory;
    private final TaskPanelContainerFactory taskPanelContainerFactory;

    public HomeSideBar(HomeFrameController homeFrameController,
            TaskPanelDto dailyTaskPanelDto, Set<TaskPanelDto> taskPanelDtos) {

        super(homeFrameController);
        this.taskPanelFactory = new RootTaskPanelFactory();
        this.taskPanelContainerFactory = new RootTaskPanelContainerFactory();
        setLayout(MAIN_LAYOUT);
        init(dailyTaskPanelDto, taskPanelDtos);
    }

    private void initDailyTaskPanel(TaskPanelDto dailyTaskPanelDto) {
        this.dailyTaskPanel = this.taskPanelFactory.createTaskPanel(this.homeFrameController, dailyTaskPanelDto);
    }

    private void initTaskPanelContainer(Set<TaskPanelDto> taskPanelDtos) {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        final String taskPanelContainerTitle =
                messageLoader.getMessage("task.panel.container.title");

        this.taskPanelContainer = this.taskPanelContainerFactory.createTaskPanelContainer(
                this.homeFrameController, taskPanelContainerTitle, taskPanelDtos);
    }

    private void initAddNewTaskBtn() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.addNewTaskBtn = UIComponentFactory.createButton(messageLoader.getMessage("add.task.component.text"));
        this.addNewTaskBtn.addActionListener(this);
    }

    private void init(TaskPanelDto dailyTaskPanelDto, Set<TaskPanelDto> taskPanelDtos) {
        initDailyTaskPanel(dailyTaskPanelDto);
        addChildComponent(this.dailyTaskPanel);

        initTaskPanelContainer(taskPanelDtos);
        addChildComponent(this.taskPanelContainer);

        initAddNewTaskBtn();
        addChildComponent(this.addNewTaskBtn);
    }

    @Override
    protected void loadChildComponentsSize() {
        final int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;

        final int maxChildComponentWidth = availableWidth - HORIZONTAL_GAP;

        final int dailyTaskPanelHeight = ViewConstant.ROOT_TASK_PANEL_HEIGHT;
        this.childComponentSizeMap.put(this.dailyTaskPanel,
                new Dimension(maxChildComponentWidth, dailyTaskPanelHeight));

        final int addNewTaskBtnHeight = 45;
        this.childComponentSizeMap.put(this.addNewTaskBtn, new Dimension(maxChildComponentWidth, addNewTaskBtnHeight));

        final int taskPanelContainerHeight = availableHeight - VERTICAL_GAP - dailyTaskPanelHeight
                - VERTICAL_GAP - addNewTaskBtnHeight - VERTICAL_GAP;
        this.childComponentSizeMap.put(this.taskPanelContainer,
                new Dimension(maxChildComponentWidth, taskPanelContainerHeight));
    }

    @Override
    protected void setNotResizableChildComponents() {
        this.dailyTaskPanel.setResizable(false);
        this.taskPanelContainer.setResizable(false);
        this.addNewTaskBtn.setResizable(false);
    }

    private void onActionPerformedForAddNewTaskBtn() {
        final boolean requestSuccess = this.homeFrameController.requestAddNewTaskPanel(
                ControllerBase.ROOT_TASK_TYPE, this);

        if (!requestSuccess) {
            requestFailureHandler();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final Object eventSource = e.getSource();

        if (eventSource == this.addNewTaskBtn) {
            onActionPerformedForAddNewTaskBtn();
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void handlerForResultOfInsertTaskAction(boolean isSuccess, TaskPanelDto taskPanelDto) {
        final MessageLoader messageLoader = MessageLoader.getInstance();

        if (isSuccess) {
            this.taskPanelContainer.addTaskPanelByDto(taskPanelDto);
            OptionPane.showMessageDialog(messageLoader.getMessage("insert.success.dialog"));
        } else {
            OptionPane.showMessageDialog(messageLoader.getMessage("insert.failure.dialog"));
        }
    }
}
