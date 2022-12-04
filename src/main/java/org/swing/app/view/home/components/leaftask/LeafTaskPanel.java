package org.swing.app.view.home.components.leaftask;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.home.components.TaskPanel;

import java.awt.Dimension;

class LeafTaskPanel extends TaskPanel {

    public LeafTaskPanel(TaskPanelDto taskPanelDto) {
        super(taskPanelDto);
    }

    @Override
    protected void initTaskCenterPanel(TaskPanelDto taskPanelDto) {
        this.taskCenterPanel = new LeafTaskCenterPanel(taskPanelDto);
    }

    @Override
    protected void init(TaskPanelDto taskPanelDto) {
        initStatusChecker(taskPanelDto.isCompleted());
        addChildComponent(this.statusChecker);

        initTaskCenterPanel(taskPanelDto);
        addChildComponent(this.taskCenterPanel);

        initRemoveLabel();
        addChildComponent(this.removeLabel);
    }

    @Override
    public void update(TaskPanelDto taskPanelDto) {
        updateStatusChecker(taskPanelDto.isCompleted());
        updateTaskCenterPanel(taskPanelDto);
    }

    @Override
    protected void setNotResizableChildComponents() {
        this.statusChecker.setResizable(false);
        this.taskCenterPanel.setResizable(true);
        this.removeLabel.setResizable(false);
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

        final int removeLabelWidth = 30;
        this.childComponentSizeMap.put(this.removeLabel, new Dimension(removeLabelWidth, maxChildComponentHeight));

        final int taskCenterPanelWidth = availableWidth - hGap - statusCheckerWidth
                - hGap - removeLabelWidth - hGap;
        this.childComponentSizeMap.put(this.taskCenterPanel,
                new Dimension(taskCenterPanelWidth, maxChildComponentHeight));
    }
}
