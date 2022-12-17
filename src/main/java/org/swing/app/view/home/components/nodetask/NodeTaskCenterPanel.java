package org.swing.app.view.home.components.nodetask;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.components.ui.Label;
import org.swing.app.view.home.components.taskbase.TaskCenterPanel;

import java.awt.Dimension;

class NodeTaskCenterPanel extends TaskCenterPanel {

    private Label noteNotifyLabel;

    public NodeTaskCenterPanel(HomeFrameController homeFrameController, TaskPanelDto taskPanelDto) {
        super(homeFrameController, taskPanelDto);
    }

    private void initNoteNotifyLabel() {
        this.noteNotifyLabel = UIComponentFactory.createLabel(ViewConstant.ICON_LOCATION_NOTE);
    }

    @Override
    protected void init(TaskPanelDto taskPanelDto) {
        super.init(taskPanelDto);

        initDeadlineLabel(taskPanelDto.getStartDatetime(), taskPanelDto.getFinishDatetime());
        addChildComponent(this.deadlineLabel);

        initCompletionRateLabel(taskPanelDto.getChildTaskCompletedCount(), taskPanelDto.getChildTaskCount());
        addChildComponent(this.completionRateLabel);

        if (taskPanelDto.getNote() != null) {
            initNoteNotifyLabel();
            addChildComponent(this.noteNotifyLabel);
        }
    }

    // TODO: common this
    @Override
    public void update(TaskPanelDto taskPanelDto) {
        super.update(taskPanelDto);
        if (taskPanelDto.getFinishDatetime() == null) {
            if (this.deadlineLabel != null) {
                removeChildComponent(this.deadlineLabel);
                this.deadlineLabel.dispose();
                this.deadlineLabel = null;
            }
        } else {
            if (this.deadlineLabel != null) {
                this.deadlineLabel.update(taskPanelDto.getStartDatetime(), taskPanelDto.getFinishDatetime());
            } else {
                initDeadlineLabel(taskPanelDto.getStartDatetime(), taskPanelDto.getFinishDatetime());
                addChildComponent(this.deadlineLabel);
            }
        }
        if (taskPanelDto.getChildTaskCount() == 0) {
            if (this.completionRateLabel != null) {
                removeChildComponent(this.completionRateLabel);
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
                removeChildComponent(this.noteNotifyLabel);
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
        if (noteNotifyLabel != null) {
            final int noteNotifyLabelWidth = 30;
            this.childComponentSizeMap.put(this.noteNotifyLabel,
                    new Dimension(noteNotifyLabelWidth, commonChildComponentHeight));
        }
    }
}
