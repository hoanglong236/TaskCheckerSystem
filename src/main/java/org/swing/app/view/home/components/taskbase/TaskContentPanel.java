package org.swing.app.view.home.components.taskbase;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.OptionPane;
import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.ui.Label;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.home.HomeWrapperComponent;
import org.swing.app.view.components.request.InsertableTaskComponent;
import org.swing.app.view.home.components.factory.TaskPanelContainerFactory;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Set;

public abstract class TaskContentPanel extends HomeWrapperComponent implements InsertableTaskComponent {

    private static final byte HORIZONTAL_GAP = ViewConstant.SMALL_H_GAP;
    private static final byte VERTICAL_GAP = ViewConstant.SMALL_V_GAP;
    private static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT, HORIZONTAL_GAP, VERTICAL_GAP);

    private Label titleLabel;
    private TaskPanelContainer taskPanelContainer;
    protected SimpleComponent addNewTaskComponent;

    private final TaskPanelContainerFactory taskPanelContainerFactory;

    public TaskContentPanel(HomeFrameController homeFrameController,
            TaskPanelContainerFactory taskPanelContainerFactory, String title, Set<TaskPanelDto> taskPanelDtos) {

        super(homeFrameController);
        this.taskPanelContainerFactory = taskPanelContainerFactory;
        setLayout(MAIN_LAYOUT);
        init(title, taskPanelDtos);
    }

    private void initTitleLabel(String title) {
        this.titleLabel = UIComponentFactory.createLabel(title);
    }

    private void initTaskPanelContainer(Set<TaskPanelDto> taskPanelDtos) {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        final String taskPanelContainerTitle = messageLoader.getMessage("...");

        this.taskPanelContainer = this.taskPanelContainerFactory.createTaskPanelContainer(
                this.homeFrameController, taskPanelContainerTitle, taskPanelDtos);
    }

    protected abstract void initAddNewTaskComponent();

    private void init(String title, Set<TaskPanelDto> taskPanelDtos) {
        initTitleLabel(title);
        addChildComponent(this.titleLabel);

        initTaskPanelContainer(taskPanelDtos);
        addChildComponent(this.taskPanelContainer);

        initAddNewTaskComponent();
        addChildComponent(this.addNewTaskComponent);
    }

    @Override
    protected void loadChildComponentsSize() {
        this.childComponentSizeMap.clear();

        final int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;

        final int maxChildComponentWidth = availableWidth - HORIZONTAL_GAP;

        final byte titleLabelHeight = 100;
        this.childComponentSizeMap.put(this.titleLabel, new Dimension(maxChildComponentWidth, titleLabelHeight));

        final int taskPanelContainerHeight = (int) (((float) 0.8 * availableHeight) - VERTICAL_GAP);
        this.childComponentSizeMap.put(this.taskPanelContainer,
                new Dimension(maxChildComponentWidth, taskPanelContainerHeight));

        final int addTaskBtnWidth = 100;
        final int addTaskBtnHeight = 50;
        this.childComponentSizeMap.put(this.addNewTaskComponent, new Dimension(addTaskBtnWidth, addTaskBtnHeight));
    }

    @Override
    protected void setNotResizableChildComponents() {
        this.titleLabel.setResizable(true);
        this.taskPanelContainer.setResizable(true);
        this.addNewTaskComponent.setResizable(false);
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
