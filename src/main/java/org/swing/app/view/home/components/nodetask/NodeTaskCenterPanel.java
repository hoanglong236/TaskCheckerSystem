package org.swing.app.view.home.components.nodetask;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.ui.CompletionRateLabel;
import org.swing.app.view.components.ui.DeadlineLabel;
import org.swing.app.view.components.ui.IconLabel;
import org.swing.app.view.home.components.TaskCenterPanel;

import java.awt.Dimension;
import java.time.LocalDateTime;

public class NodeTaskCenterPanel extends TaskCenterPanel {

    private DeadlineLabel deadlineLabel;
    private CompletionRateLabel completionRateLabel;
    private IconLabel noteNotifyLabel;

    public NodeTaskCenterPanel(TaskPanelDto taskPanelDto) {
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

    private void initNoteNotifyLabel() {
        this.noteNotifyLabel = new IconLabel(ViewConstant.ICON_LOCATION_NOTE);
        this.noteNotifyLabel.setResizable(false);
    }

    @Override
    protected void init(TaskPanelDto taskPanelDto) {
        super.init(taskPanelDto);

        initDeadlineLabel(taskPanelDto.getStartDateTime(), taskPanelDto.getFinishDateTime());
        addChildComponent(this.deadlineLabel);

        initCompletionRateLabel(taskPanelDto.getChildTaskCompletedCount(), taskPanelDto.getChildTaskCount());
        addChildComponent(this.completionRateLabel);

        if (taskPanelDto.getNote() != null) {
            initNoteNotifyLabel();
            addChildComponent(this.noteNotifyLabel);
        }
    }

    @Override
    protected void loadOtherChildComponentsSize() {
        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;

        final int deadlineLabelWidth = 100;
        final int deadlineLabelHeight = availableHeight / 2 - MAIN_LAYOUT.getVgap();
        this.childComponentSizeMap.put(this.deadlineLabel, new Dimension(deadlineLabelWidth, deadlineLabelHeight));

        final int completionRateLabelWidth = 80;
        final int completionRateLabelHeight = deadlineLabelHeight;
        this.childComponentSizeMap.put(this.completionRateLabel,
                new Dimension(completionRateLabelWidth, completionRateLabelHeight));

        final int noteNotifyLabelWidth = 30;
        final int noteNotifyLabelHeight = deadlineLabelHeight;
        this.childComponentSizeMap.put(this.noteNotifyLabel,
                new Dimension(noteNotifyLabelWidth, noteNotifyLabelHeight));
    }
}
