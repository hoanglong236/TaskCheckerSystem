package org.swing.app.view.home.components.roottask.factory;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.ui.CompletionRateLabel;
import org.swing.app.view.components.ui.DeadlineLabel;
import org.swing.app.view.home.components.TaskCenterPanel;
import org.swing.app.view.home.components.factory.TaskCenterPanelFactory;

import java.awt.Dimension;
import java.time.LocalDateTime;

public class RootTaskCenterPanelFactory implements TaskCenterPanelFactory {

    @Override
    public TaskCenterPanel createTaskCenterPanel(TaskPanelDto taskPanelDto) {
        return new RootTaskCenterPanel(taskPanelDto);
    }
}

class RootTaskCenterPanel extends TaskCenterPanel {

    private DeadlineLabel deadlineLabel;
    private CompletionRateLabel completionRateLabel;

    public RootTaskCenterPanel(TaskPanelDto taskPanelDto) {
        super(taskPanelDto);
    }

    @Override
    protected void init(TaskPanelDto taskPanelDto) {
        super.init(taskPanelDto);
        if (taskPanelDto.getFinishDateTime() != null) {
            this.deadlineLabel = new DeadlineLabel(taskPanelDto.getStartDateTime(), taskPanelDto.getFinishDateTime());
            addChildComponent(this.deadlineLabel);
        }
        if (taskPanelDto.getChildTaskCount() == 0) {
            this.completionRateLabel = new CompletionRateLabel(taskPanelDto.getChildTaskCompletedCount(),
                    taskPanelDto.getChildTaskCount());
            addChildComponent(this.completionRateLabel);
        }
    }

    @Override
    protected void setNotResizableChildComponents() {
        this.deadlineLabel.setResizable(false);
        this.completionRateLabel.setResizable(false);
    }

    @Override
    public void update(TaskPanelDto taskPanelDto) {
        super.update(taskPanelDto);
        if (taskPanelDto.getFinishDateTime() == null) {
            if (this.deadlineLabel != null) {
                removeChildComponent(this.deadlineLabel);
                this.deadlineLabel = null;
            }
        } else {
            if (this.deadlineLabel == null) {
                this.deadlineLabel.update(taskPanelDto.getStartDateTime(), taskPanelDto.getFinishDateTime());
            } else {
                this.deadlineLabel = new DeadlineLabel(taskPanelDto.getStartDateTime(),
                        taskPanelDto.getFinishDateTime());
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
                this.completionRateLabel = new CompletionRateLabel(taskPanelDto.getChildTaskCompletedCount(),
                        taskPanelDto.getChildTaskCount());
                addChildComponent(this.completionRateLabel);
            }
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