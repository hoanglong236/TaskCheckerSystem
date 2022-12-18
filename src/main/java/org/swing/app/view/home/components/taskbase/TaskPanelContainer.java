package org.swing.app.view.home.components.taskbase;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.ui.Button;
import org.swing.app.view.components.ui.Label;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.components.ui.VerticalScrollPane;
import org.swing.app.view.home.HomeWrapperComponent;
import org.swing.app.view.home.components.factory.TaskPanelFactory;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Set;

public abstract class TaskPanelContainer extends HomeWrapperComponent {

    private static final byte HORIZONTAL_GAP = ViewConstant.SMALL_H_GAP;
    private static final byte VERTICAL_GAP = ViewConstant.SMALL_V_GAP;
    private static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT, HORIZONTAL_GAP, VERTICAL_GAP);

    private Label titleLabel;
    private Button filterButton;
    protected VerticalScrollPane verticalScrollPane;

    private final TaskPanelFactory taskPanelFactory;

    public TaskPanelContainer(HomeFrameController homeFrameController, TaskPanelFactory taskPanelFactory,
            String title, Set<TaskPanelDto> taskPanelDtos) {

        super(homeFrameController);
        this.taskPanelFactory = taskPanelFactory;
        setLayout(MAIN_LAYOUT);
        init(title, taskPanelDtos);
    }

    private void initTitleLabel(String title) {
        this.titleLabel = UIComponentFactory.createLabel(title);
    }

    private void initFilterButton() {
        this.filterButton = UIComponentFactory.createButton(ViewConstant.ICON_LOCATION_FILTER);
    }

    protected abstract void initVerticalScrollPane(Set<TaskPanelDto> taskPanelDtos);

    private void init(String title, Set<TaskPanelDto> taskPanelDtos) {
        initTitleLabel(title);
        addChildComponent(this.titleLabel);

        initFilterButton();
        addChildComponent(this.filterButton);

        initVerticalScrollPane(taskPanelDtos);
        addChildComponent(this.verticalScrollPane);
    }

    public void addTaskPanelByDto(TaskPanelDto taskPanelDto) {
        final TaskPanel taskPanel = this.taskPanelFactory.createTaskPanel(this.homeFrameController, taskPanelDto);
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
        this.childComponentSizeMap.clear();

        final int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;

        final int commonComponentHeight = 40;

        final int filterBtnWidth = 40;
        this.childComponentSizeMap.put(this.filterButton, new Dimension(filterBtnWidth, commonComponentHeight));

        final int titleLabelWidth = availableWidth - HORIZONTAL_GAP - filterBtnWidth - HORIZONTAL_GAP;
        this.childComponentSizeMap.put(this.titleLabel, new Dimension(titleLabelWidth, commonComponentHeight));

        final int verticalScrollPaneWidth = availableWidth - HORIZONTAL_GAP;
        final int verticalScrollPaneHeight = availableHeight - VERTICAL_GAP - commonComponentHeight - VERTICAL_GAP;
        this.childComponentSizeMap.put(this.verticalScrollPane,
                new Dimension(verticalScrollPaneWidth, verticalScrollPaneHeight));
    }

    @Override
    protected void setNotResizableChildComponents() {
        this.verticalScrollPane.setResizable(true);
        this.filterButton.setResizable(false);
        this.titleLabel.setResizable(true);
    }
}
