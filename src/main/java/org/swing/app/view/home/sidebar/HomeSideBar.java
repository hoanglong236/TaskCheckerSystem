package org.swing.app.view.home.sidebar;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.WrapperComponent;
import org.swing.app.view.components.ui.TextButton;
import org.swing.app.view.home.components.roottask.RootTaskPanelContainer;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Set;

public class HomeSideBar extends WrapperComponent {

    private static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.CENTER,
            ViewConstant.MEDIUM_H_GAP, ViewConstant.MEDIUM_V_GAP);

    private RootTaskPanelContainer repeatTaskPanelContainer;
    private RootTaskPanelContainer nonRepeatTaskPanelContainer;
    private TextButton addTaskBtn;

    public HomeSideBar(Set<TaskPanelDto> repeatTaskPanelDtos, Set<TaskPanelDto> nonRepeatTaskPanelDtos) {
        super();
        this.component.setLayout(MAIN_LAYOUT);
        init(repeatTaskPanelDtos, nonRepeatTaskPanelDtos);
    }

    private void initRepeatTaskPanelContainer(Set<TaskPanelDto> taskPanelDtos) {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        final String repeatTaskPanelContainerTitle = messageLoader.getMessage("repeat.task.panel.container.title");
        this.repeatTaskPanelContainer = new RootTaskPanelContainer(repeatTaskPanelContainerTitle, taskPanelDtos);
    }

    private void initNonRepeatTaskPanelContainer(Set<TaskPanelDto> taskPanelDtos) {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        final String nonRepeatTaskPanelContainerTitle =
                messageLoader.getMessage("non.repeat.task.panel.container.title");
        this.nonRepeatTaskPanelContainer = new RootTaskPanelContainer(nonRepeatTaskPanelContainerTitle, taskPanelDtos);
    }

    private void initAddTaskBtn() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.addTaskBtn = new TextButton(messageLoader.getMessage("button.add.task"));
        this.addTaskBtn.setResizable(false);
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
}
