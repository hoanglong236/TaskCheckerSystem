package org.swing.app.view.home.components.nodetask.factory;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.ui.CompletionRateLabel;
import org.swing.app.view.components.ui.DeadlineLabel;
import org.swing.app.view.components.ui.IconLabel;
import org.swing.app.view.home.components.TaskCenterPanel;
import org.swing.app.view.home.components.factory.TaskCenterPanelFactory;

import java.awt.Dimension;
import java.time.LocalDateTime;

public class NoteTaskCenterPanelFactory implements TaskCenterPanelFactory {

    @Override
    public TaskCenterPanel createTaskCenterPanel(TaskPanelDto taskPanelDto) {
        return new NodeTaskCenterPanel(taskPanelDto);
    }
}

class NodeTaskCenterPanel extends TaskCenterPanel {

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
    public void update(TaskPanelDto taskPanelDto) {
        super.update(taskPanelDto);
        if (taskPanelDto.getFinishDateTime() == null) {
            if (this.deadlineLabel != null) {
                this.childComponents.remove(this.deadlineLabel);
                this.deadlineLabel.dispose();
                this.deadlineLabel = null;
            }
        } else {
            if (this.deadlineLabel != null) {
                this.deadlineLabel.update(taskPanelDto.getStartDateTime(), taskPanelDto.getFinishDateTime());
            } else {
                initDeadlineLabel(taskPanelDto.getStartDateTime(), taskPanelDto.getFinishDateTime());
                addChildComponent(this.deadlineLabel);
            }
        }
        if (taskPanelDto.getChildTaskCount() == 0) {
            if (this.completionRateLabel != null) {
                this.childComponents.remove(this.completionRateLabel);
                this.completionRateLabel.dispose();
                this.completionRateLabel = null;
            }
        } else {
            if (this.completionRateLabel != null) {
                this.completionRateLabel.update(taskPanelDto.getChildTaskCompletedCount(),
                        taskPanelDto.getChildTaskCount());
            } else {
                initCompletionRateLabel(taskPanelDto.getChildTaskCompletedCount(), taskPanelDto.getChildTaskCount());
                addChildComponent(this.completionRateLabel);
            }
        }
        if (taskPanelDto.getNote() == null) {
            if (this.noteNotifyLabel != null) {
                this.childComponents.remove(this.noteNotifyLabel);
                this.noteNotifyLabel.dispose();
                this.noteNotifyLabel = null;
            }
        } else {
            if (this.noteNotifyLabel == null) {
                initNoteNotifyLabel();
                addChildComponent(this.completionRateLabel);
            }
        }
    }

    @Override
    protected void setNotResizableChildComponents() {
        this.deadlineLabel.setResizable(false);
        this.completionRateLabel.setResizable(false);
        this.noteNotifyLabel.setResizable(false);
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
