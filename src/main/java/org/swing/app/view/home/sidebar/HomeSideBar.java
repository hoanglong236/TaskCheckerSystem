package org.swing.app.view.home.sidebar;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.PanelWrapperComponent;
import org.swing.app.view.components.ui.Button;
import org.swing.app.view.components.ui.UIComponentFactory;
import org.swing.app.view.home.components.TaskPanelContainer;
import org.swing.app.view.home.components.factory.TaskPanelContainerFactory;
import org.swing.app.view.home.components.roottask.factory.RootTaskPanelContainerFactory;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Set;

public class HomeSideBar extends PanelWrapperComponent {

    private static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.CENTER,
            ViewConstant.MEDIUM_H_GAP, ViewConstant.MEDIUM_V_GAP);

    private TaskPanelContainer repeatTaskPanelContainer;
    private TaskPanelContainer nonRepeatTaskPanelContainer;

    private final TaskPanelContainerFactory taskPanelContainerFactory;
    private Button addTaskBtn;

    public HomeSideBar(Set<TaskPanelDto> repeatTaskPanelDtos, Set<TaskPanelDto> nonRepeatTaskPanelDtos) {
        super();
        this.component.setLayout(MAIN_LAYOUT);
        this.taskPanelContainerFactory = new RootTaskPanelContainerFactory();
        init(repeatTaskPanelDtos, nonRepeatTaskPanelDtos);
    }

    private void initRepeatTaskPanelContainer(Set<TaskPanelDto> taskPanelDtos) {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        final String repeatTaskPanelContainerTitle = messageLoader.getMessage("repeat.task.panel.container.title");
        this.repeatTaskPanelContainer = this.taskPanelContainerFactory.createTaskPanelContainer(
                repeatTaskPanelContainerTitle, taskPanelDtos);
    }

    private void initNonRepeatTaskPanelContainer(Set<TaskPanelDto> taskPanelDtos) {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        final String nonRepeatTaskPanelContainerTitle =
                messageLoader.getMessage("non.repeat.task.panel.container.title");
        this.nonRepeatTaskPanelContainer = this.taskPanelContainerFactory.createTaskPanelContainer(
                nonRepeatTaskPanelContainerTitle, taskPanelDtos);
    }

    private void initAddTaskBtn() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.addTaskBtn = UIComponentFactory.createButton(messageLoader.getMessage("button.add.task"));
    }

    private void init(Set<TaskPanelDto> repeatTaskPanelDtos, Set<TaskPanelDto> nonRepeatTaskPanelDtos) {
        initRepeatTaskPanelContainer(repeatTaskPanelDtos);
        addChildComponent(this.repeatTaskPanelContainer);

        initNonRepeatTaskPanelContainer(nonRepeatTaskPanelDtos);
        addChildComponent(this.nonRepeatTaskPanelContainer);

        initAddTaskBtn();
        addChildComponent(this.addTaskBtn);
    }

    @Override
    protected void loadChildComponentsSize() {
        final int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;

        final int maxChildComponentWidth = availableWidth - MAIN_LAYOUT.getHgap();

        final int repeatTaskPanelContainerHeight = (int) (((float) 0.35 * availableHeight) - MAIN_LAYOUT.getVgap());
        this.childComponentSizeMap.put(this.repeatTaskPanelContainer,
                new Dimension(maxChildComponentWidth, repeatTaskPanelContainerHeight));

        final int nonRepeatTaskPanelContainerHeight = (int) (((float) 0.55 * availableHeight) - MAIN_LAYOUT.getVgap());
        this.childComponentSizeMap.put(this.nonRepeatTaskPanelContainer,
                new Dimension(maxChildComponentWidth, nonRepeatTaskPanelContainerHeight));

        final int addTaskBtnHeight = 50;
        this.childComponentSizeMap.put(this.addTaskBtn, new Dimension(maxChildComponentWidth, addTaskBtnHeight));
    }

    @Override
    protected void setNotResizableChildComponents() {
        this.repeatTaskPanelContainer.setResizable(false);
        this.nonRepeatTaskPanelContainer.setResizable(false);
        this.addTaskBtn.setResizable(false);
    }
}
