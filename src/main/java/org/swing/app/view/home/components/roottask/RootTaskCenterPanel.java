package org.swing.app.view.home.components.roottask;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.home.components.taskbase.TaskCenterPanel;

import java.awt.Dimension;

class RootTaskCenterPanel extends TaskCenterPanel {

    public RootTaskCenterPanel(HomeFrameController homeFrameController, TaskPanelDto taskPanelDto) {
        super(homeFrameController, taskPanelDto);
    }

    @Override
    protected void init(TaskPanelDto taskPanelDto) {
        super.init(taskPanelDto);

        if (taskPanelDto.getFinishDatetime() != null) {
            initDeadlineLabel(taskPanelDto.getStartDatetime(), taskPanelDto.getFinishDatetime());
            addChildComponent(this.deadlineLabel);
        }

        if (taskPanelDto.getChildTaskCount() == 0) {
            initCompletionRateLabel(taskPanelDto.getChildTaskCompletedCount(), taskPanelDto.getChildTaskCount());
            addChildComponent(this.completionRateLabel);
        }
    }

    @Override
    protected void setNotResizableChildComponents() {
        if (this.deadlineLabel != null) {
            this.deadlineLabel.setResizable(false);
        }
        if (this.completionRateLabel != null) {
            this.completionRateLabel.setResizable(false);
        }
    }

    @Override
    public void update(TaskPanelDto taskPanelDto) {
        super.update(taskPanelDto);

        final boolean hasDataForDeadlineLabel = taskPanelDto.getFinishDatetime() == null;
        handleDeadlineLabelByActionChildComponent(
                getActionChildComponentWhenTryToUpdate(this.deadlineLabel, hasDataForDeadlineLabel),
                taskPanelDto.getStartDatetime(), taskPanelDto.getFinishDatetime());

        final boolean hasDataForCompletionRateLabel = taskPanelDto.getChildTaskCount() > 0;
        handleCompletionRateLabelByActionChildComponent(
                getActionChildComponentWhenTryToUpdate(this.completionRateLabel, hasDataForCompletionRateLabel),
                taskPanelDto.getChildTaskCompletedCount(), taskPanelDto.getChildTaskCount());
    }

    @Override
    protected void loadOtherChildComponentsSize() {
        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;
        final int commonChildComponentHeight = availableHeight / 2 - MAIN_LAYOUT.getVgap();

        if (this.deadlineLabel != null) {
            final int deadlineLabelWidth = 100;
            this.childComponentSizeMap.put(this.deadlineLabel,
                    new Dimension(deadlineLabelWidth, commonChildComponentHeight));
        }

        if (this.completionRateLabel != null) {
            final int completionRateLabelWidth = 80;
            this.childComponentSizeMap.put(this.completionRateLabel,
                    new Dimension(completionRateLabelWidth, commonChildComponentHeight));
        }
    }
}
