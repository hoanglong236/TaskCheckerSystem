package org.swing.app.view.home.components;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.PanelWrapperComponent;
import org.swing.app.view.components.ui.Button;
import org.swing.app.view.components.ui.Label;
import org.swing.app.view.components.ui.UIComponentFactory;
import org.swing.app.view.components.ui.VerticalScrollPane;
import org.swing.app.view.home.components.factory.TaskComponentFactory;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Set;

public class TaskPanelContainer extends PanelWrapperComponent {

    private static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT,
            ViewConstant.SMALL_H_GAP, ViewConstant.SMALL_V_GAP);

    private Label titleLabel;
    private Button filterButton;
    private VerticalScrollPane verticalScrollPane;
    private TaskComponentFactory taskComponentFactory;

    public TaskPanelContainer(TaskComponentFactory taskComponentFactory,
            String title, Set<TaskPanelDto> taskPanelDtos) {
        super();
        this.taskComponentFactory = taskComponentFactory;
        init(title, taskPanelDtos);
    }

    private void initTitleLabel(String title) {
        this.titleLabel = UIComponentFactory.createLabel(title);
    }

    private void initFilterButton() {
        this.filterButton = UIComponentFactory.createButton(ViewConstant.ICON_LOCATION_FILTER);
    }

    private void initVerticalScrollPane(Set<TaskPanelDto> taskPanelDtos) {
        this.verticalScrollPane = this.taskComponentFactory.createScrollPaneToContainTaskPanels();

        for (final TaskPanelDto taskPanelDto : taskPanelDtos) {
            addTaskPanel(this.taskComponentFactory.createTaskPanel(taskPanelDto));
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
        final TaskPanel taskPanel = this.taskComponentFactory.createTaskPanel(taskPanelDto);
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

        final int filterBtnWidth = 40;
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
        this.verticalScrollPane.setResizable(true);
        this.filterButton.setResizable(false);
        this.titleLabel.setResizable(true);
    }
}
