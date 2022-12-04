package org.swing.app.view.home.components;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.PanelWrapperComponent;
import org.swing.app.view.components.ui.Button;
import org.swing.app.view.components.ui.Label;
import org.swing.app.view.components.ui.UIComponentFactory;
import org.swing.app.view.home.components.factory.TaskComponentFactory;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Set;

public class TaskContentPanel extends PanelWrapperComponent {

    private static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT,
            ViewConstant.SMALL_H_GAP, ViewConstant.SMALL_V_GAP);

    private Label titleLabel = null;
    private TaskPanelContainer taskPanelContainer = null;
    private Button addTaskBtn = null;

    private final TaskComponentFactory taskComponentFactory;

    public TaskContentPanel(TaskComponentFactory taskComponentFactory,
            String title, Set<TaskPanelDto> taskPanelDtos) {

        super();
        this.taskComponentFactory = taskComponentFactory;
        setLayout(MAIN_LAYOUT);
        init(title, taskPanelDtos);
    }

    private void initTitleLabel(String title) {
        this.titleLabel = UIComponentFactory.createLabel(title);
    }

    private void initTaskPanelContainer(Set<TaskPanelDto> taskPanelDtos) {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        final String taskPanelContainerTitle = messageLoader.getMessage("...");

        this.taskPanelContainer = this.taskComponentFactory.createTaskPanelContainer(
                taskPanelContainerTitle, taskPanelDtos);
    }

    private void initAddTaskBtn() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.addTaskBtn = UIComponentFactory.createButton((messageLoader.getMessage("button.add.task")));
    }

    private void init(String title, Set<TaskPanelDto> taskPanelDtos) {
        initTitleLabel(title);
        addChildComponent(this.titleLabel);

        initTaskPanelContainer(taskPanelDtos);
        addChildComponent(this.taskPanelContainer);

        initAddTaskBtn();
        addChildComponent(this.addTaskBtn);
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

    @Override
    protected void setNotResizableChildComponents() {
        this.titleLabel.setResizable(true);
        this.taskPanelContainer.setResizable(true);
        this.addTaskBtn.setResizable(false);
    }
}
