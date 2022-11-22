package org.swing.app.view.home.components;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.WrapperComponent;
import org.swing.app.view.components.ui.IconButton;
import org.swing.app.view.components.ui.TextLabel;
import org.swing.app.view.components.ui.VerticalScrollPane;
import org.swing.app.view.home.components.factory.TaskPanelContainerChildComponentFactory;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Set;

public class TaskPanelContainer extends WrapperComponent {

    private static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT,
            ViewConstant.SMALL_H_GAP, ViewConstant.SMALL_V_GAP);

    private TextLabel titleLabel;
    private IconButton filterButton;
    private VerticalScrollPane verticalScrollPane;
    private TaskPanelContainerChildComponentFactory taskPanelFactory;

    public TaskPanelContainer(TaskPanelContainerChildComponentFactory taskPanelFactory,
            String title, Set<TaskPanelDto> taskPanelDtos) {
        super();
        this.taskPanelFactory = taskPanelFactory;
        init(title, taskPanelDtos);
    }

    private void initTitleLabel(String title) {
        this.titleLabel = new TextLabel(title);
        this.titleLabel.setResizable(true);
    }

    private void initFilterButton() {
        this.filterButton = new IconButton(ViewConstant.ICON_LOCATION_FILTER);
        this.filterButton.setResizable(false);
    }

    private void initVerticalScrollPane(Set<TaskPanelDto> taskPanelDtos) {
        this.verticalScrollPane = this.taskPanelFactory.createVerticalScrollPane();
        this.verticalScrollPane.setResizable(true);

        for (final TaskPanelDto taskPanelDto : taskPanelDtos) {
            addTaskPanel(this.taskPanelFactory.createTaskPanel(taskPanelDto));
        }
    }

    private void init(String title, Set<TaskPanelDto> taskPanelDtos) {
        initTitleLabel(title);
        addChildComponent(this.titleLabel);

        initFilterButton();
        addChildComponent(this.filterButton);

        initVerticalScrollPane(taskPanelDtos);
        addChildComponent(this.verticalScrollPane);
    }

    public void addTaskPanelByDto(TaskPanelDto taskPanelDto) {
        final TaskPanel taskPanel = this.taskPanelFactory.createTaskPanel(taskPanelDto);
        addTaskPanel(taskPanel);
    }

    public void addTaskPanel(TaskPanel taskPanel) {
        this.verticalScrollPane.addChildComponent(taskPanel);
    }

    public void removeTaskPanel(TaskPanel taskPanel) {
        this.verticalScrollPane.removeChildComponent(taskPanel);
    }

    @Override
    protected void loadChildComponentsSize() {
        final int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;

        final int hGap = MAIN_LAYOUT.getHgap();
        final int vGap = MAIN_LAYOUT.getVgap();

        final int filterBtnWidth = 50;
        final int filterBtnHeight = 40;
        this.childComponentSizeMap.put(this.filterButton, new Dimension(filterBtnWidth, filterBtnHeight));

        final int titleLabelWidth = availableWidth - hGap - filterBtnWidth - hGap;
        final int titleLabelHeight = filterBtnHeight;
        this.childComponentSizeMap.put(this.titleLabel, new Dimension(titleLabelWidth, titleLabelHeight));

        final int verticalScrollPaneWidth = availableWidth - hGap;
        final int verticalScrollPaneHeight = availableHeight - vGap - filterBtnHeight - vGap;
        this.childComponentSizeMap.put(this.verticalScrollPane,
                new Dimension(verticalScrollPaneWidth, verticalScrollPaneHeight));
    }

    @Override
    protected void setNotResizableChildComponents() {
    }
}
