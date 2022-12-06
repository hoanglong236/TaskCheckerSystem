package org.swing.app.view.home.sidebar;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.PanelWrapperComponent;
import org.swing.app.view.components.ui.Button;
import org.swing.app.view.components.ui.UIComponentFactory;
import org.swing.app.view.home.components.TaskPanel;
import org.swing.app.view.home.components.TaskPanelContainer;
import org.swing.app.view.home.components.factory.TaskPanelContainerFactory;
import org.swing.app.view.home.components.factory.TaskPanelFactory;
import org.swing.app.view.home.components.roottask.RootTaskPanelContainerFactory;
import org.swing.app.view.home.components.roottask.RootTaskPanelFactory;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Set;

public class HomeSideBar extends PanelWrapperComponent {

    private static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.CENTER,
            ViewConstant.MEDIUM_H_GAP, ViewConstant.MEDIUM_V_GAP);

    private TaskPanel dailyTaskPanel;
    private TaskPanelContainer taskPanelContainer;
    private Button addTaskBtn;

    private final TaskPanelFactory taskPanelFactory;
    private final TaskPanelContainerFactory taskPanelContainerFactory;

    public HomeSideBar(TaskPanelDto dailyTaskPanelDto, Set<TaskPanelDto> taskPanelDtos) {
        super();
        setLayout(MAIN_LAYOUT);
        this.taskPanelFactory = new RootTaskPanelFactory();
        this.taskPanelContainerFactory = new RootTaskPanelContainerFactory();
        init(dailyTaskPanelDto, taskPanelDtos);
    }

    private void initDailyTaskPanel(TaskPanelDto dailyTaskPanelDto) {
        this.dailyTaskPanel = this.taskPanelFactory.createTaskPanel(dailyTaskPanelDto);
    }

    private void initTaskPanelContainer(Set<TaskPanelDto> taskPanelDtos) {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        final String nonRepeatTaskPanelContainerTitle =
                messageLoader.getMessage("task.panel.container.title");

        this.taskPanelContainer = this.taskPanelContainerFactory.createTaskPanelContainer(
                nonRepeatTaskPanelContainerTitle, taskPanelDtos);
    }

    private void initAddTaskBtn() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.addTaskBtn = UIComponentFactory.createButton(messageLoader.getMessage("add.task.component.text"));
    }

    private void init(TaskPanelDto dailyTaskPanelDto, Set<TaskPanelDto> taskPanelDtos) {
        initDailyTaskPanel(dailyTaskPanelDto);
        addChildComponent(this.dailyTaskPanel);

        initTaskPanelContainer(taskPanelDtos);
        addChildComponent(this.taskPanelContainer);

        initAddTaskBtn();
        addChildComponent(this.addTaskBtn);
    }

    @Override
    protected void loadChildComponentsSize() {
        final int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;

        final int vGap = MAIN_LAYOUT.getVgap();
        final int maxChildComponentWidth = availableWidth - MAIN_LAYOUT.getHgap();

        final int dailyTaskPanelHeight = ViewConstant.ROOT_TASK_PANEL_HEIGHT;
        this.childComponentSizeMap.put(this.dailyTaskPanel,
                new Dimension(maxChildComponentWidth, dailyTaskPanelHeight));

        final int addTaskBtnHeight = 45;
        this.childComponentSizeMap.put(this.addTaskBtn, new Dimension(maxChildComponentWidth, addTaskBtnHeight));

        final int taskPanelContainerHeight = availableHeight - vGap
                - dailyTaskPanelHeight - vGap - addTaskBtnHeight - vGap;
        this.childComponentSizeMap.put(this.taskPanelContainer,
                new Dimension(maxChildComponentWidth, taskPanelContainerHeight));
    }

    @Override
    protected void setNotResizableChildComponents() {
        this.dailyTaskPanel.setResizable(false);
        this.taskPanelContainer.setResizable(false);
        this.addTaskBtn.setResizable(false);
    }
}
