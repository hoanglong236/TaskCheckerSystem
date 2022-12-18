package org.swing.app.view.home.components.leaftask;

import org.swing.app.controller.ControllerBase;
import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.home.components.taskbase.TaskPanel;

import java.awt.Dimension;

public class LeafTaskPanel extends TaskPanel {

    public LeafTaskPanel(HomeFrameController homeFrameController, int preferHeight, TaskPanelDto taskPanelDto) {
        super(homeFrameController, preferHeight, taskPanelDto);
    }

    @Override
    protected void initTaskCenterPanel(TaskPanelDto taskPanelDto) {
        this.taskCenterPanel = new LeafTaskCenterPanel(this.homeFrameController, taskPanelDto);
    }

    @Override
    protected void init(TaskPanelDto taskPanelDto) {
        initActivationLabel();
        addChildComponent(this.activationLabel);

        initStatusChecker(taskPanelDto.isCompleted());
        addChildComponent(this.statusChecker);

        initTaskCenterPanel(taskPanelDto);
        addChildComponent(this.taskCenterPanel);

        initPopup();
        addChildComponent(this.popup);
    }

    @Override
    public void update(TaskPanelDto taskPanelDto) {
        updateStatusChecker(taskPanelDto.isCompleted());
        updateTaskCenterPanel(taskPanelDto);
    }

    @Override
    public boolean requestLoadContent() {
        return this.homeFrameController.requestLoadTaskContent(ControllerBase.LEAF_TASK_TYPE, getTaskId());
    }

    @Override
    protected boolean requestUpdate() {
        return this.homeFrameController.requestUpdateTaskPanel(
                ControllerBase.LEAF_TASK_TYPE, this);
    }

    @Override
    protected void setNotResizableChildComponents() {
        this.statusChecker.setResizable(false);
        this.taskCenterPanel.setResizable(true);
    }

    @Override
    protected void loadChildComponentsSize() {
        this.childComponentSizeMap.clear();

        final int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;

        final int hGap = MAIN_LAYOUT.getHgap();
        final int maxChildComponentHeight = availableHeight - MAIN_LAYOUT.getVgap();

        final int statusCheckerWidth = 30;
        this.childComponentSizeMap.put(this.statusChecker, new Dimension(statusCheckerWidth, maxChildComponentHeight));

        final int taskCenterPanelWidth = availableWidth - hGap - statusCheckerWidth - hGap;
        this.childComponentSizeMap.put(this.taskCenterPanel,
                new Dimension(taskCenterPanelWidth, maxChildComponentHeight));
    }
}
