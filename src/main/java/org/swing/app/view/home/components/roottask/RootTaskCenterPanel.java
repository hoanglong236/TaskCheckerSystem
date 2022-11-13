package org.swing.app.view.home.components.roottask;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.ui.CompletionRateLabel;
import org.swing.app.view.components.ui.DeadlineLabel;
import org.swing.app.view.home.components.TaskCenterPanel;

import java.awt.Dimension;
import java.time.LocalDateTime;

public class RootTaskCenterPanel extends TaskCenterPanel {

    private DeadlineLabel deadlineLabel;
    private CompletionRateLabel completionRateLabel;

    public RootTaskCenterPanel(TaskPanelDto taskPanelDto) {
        super(taskPanelDto);
    }

    private void initDeadlineLabel(LocalDateTime startDateTime, LocalDateTime finishDateTime) {
        this.deadlineLabel = new DeadlineLabel(startDateTime, finishDateTime);
        this.deadlineLabel.setResizable(false);
    }

    private void initCompletionRateLabel(int completedCount, int totalCount) {
        this.completionRateLabel = new CompletionRateLabel(completedCount, totalCount);
        this.completionRateLabel.setResizable(false);
    }

    @Override
    protected void init(TaskPanelDto taskPanelDto) {
        super.init(taskPanelDto);

        if (taskPanelDto.getFinishDateTime() != null) {
            initDeadlineLabel(taskPanelDto.getStartDateTime(), taskPanelDto.getFinishDateTime());
            addChildComponent(this.deadlineLabel);
        }

        if (taskPanelDto.getChildTaskCount() == 0) {
            initCompletionRateLabel(taskPanelDto.getChildTaskCompletedCount(), taskPanelDto.getChildTaskCount());
            addChildComponent(this.completionRateLabel);
        }
    }

    @Override
    protected void loadOtherChildComponentsSize() {
        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;

        if (this.deadlineLabel != null) {
            final int deadlineLabelWidth = 120;
            final int deadlineLabelHeight = availableHeight / 2 - MAIN_LAYOUT.getVgap();
            this.childComponentSizeMap.put(this.deadlineLabel, new Dimension(deadlineLabelWidth, deadlineLabelHeight));
        }

        if (this.completionRateLabel != null) {
            final int completionRateLabelWidth = 120;
            final int completionRateLabelHeight = availableHeight / 2 - MAIN_LAYOUT.getVgap();
            this.childComponentSizeMap.put(this.completionRateLabel,
                    new Dimension(completionRateLabelWidth, completionRateLabelHeight));
        }
    }

    // TODO: handle dispose for deadline
}
