package org.swing.app.view.home.components;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.WrapperComponent;
import org.swing.app.view.components.ui.Button;
import org.swing.app.view.components.ui.TextButton;
import org.swing.app.view.components.ui.TextLabel;
import org.swing.app.view.home.components.factory.TaskPanelContainerFactory;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Set;

public class TaskContentPanel extends WrapperComponent {

    protected static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT,
            ViewConstant.SMALL_H_GAP, ViewConstant.SMALL_V_GAP);

    private TextLabel titleLabel = null;
    private TaskPanelContainer taskPanelContainer = null;
    private Button addTaskBtn = null;

    private TaskPanelContainerFactory taskPanelContainerFactory;

    public TaskContentPanel(TaskPanelContainerFactory taskPanelContainerFactory,
            String title, Set<TaskPanelDto> taskPanelDtos) {
        super();
        this.taskPanelContainerFactory = taskPanelContainerFactory;
    }

    private void initTitleLabel(String title) {
        this.titleLabel = new TextLabel(title);
        this.titleLabel.setResizable(true);
    }

    // TODO: name of task container panel title????
    private void initTaskPanelContainer(Set<TaskPanelDto> taskPanelDtos) {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        final String taskPanelContainerTitle = messageLoader.getMessage("...");
        this.taskPanelContainer = this.taskPanelContainerFactory.createTaskPanelContainer(taskPanelContainerTitle,
                taskPanelDtos);
        this.taskPanelContainer.setResizable(true);
    }

    private void initAddTaskBtn() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.addTaskBtn = new TextButton((messageLoader.getMessage("button.add.task")));
        this.addTaskBtn.setResizable(false);
    }

    @Override
    protected void loadChildComponentsSize() {
        final int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;

        final int maxChildComponentWidth = availableWidth - MAIN_LAYOUT.getHgap();

        final byte titleLabelHeight = 100;
        this.childComponentSizeMap.put(this.titleLabel, new Dimension(maxChildComponentWidth, titleLabelHeight));

        final int taskPanelContainerHeight = (int) (((float) 0.8 * availableHeight) - MAIN_LAYOUT.getVgap());
        this.childComponentSizeMap.put(this.taskPanelContainer,
                new Dimension(maxChildComponentWidth, taskPanelContainerHeight));

        final int addTaskBtnWidth = 100;
        final int addTaskBtnHeight = 50;
        this.childComponentSizeMap.put(this.addTaskBtn, new Dimension(addTaskBtnWidth, addTaskBtnHeight));
    }
}
