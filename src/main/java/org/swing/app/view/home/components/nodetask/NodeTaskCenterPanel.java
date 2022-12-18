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

    protected void handleNoteNotifyLabelByActionChildComponent(byte actionChildComponent) {
        if (actionChildComponent == INIT_ACTION_CHILD_COMPONENT) {
            initNoteNotifyLabel();
            addChildComponent(this.noteNotifyLabel);
            return;
        }

        if (actionChildComponent == REMOVE_ACTION_CHILD_COMPONENT) {
            removeChildComponent(this.noteNotifyLabel);
            this.noteNotifyLabel.dispose();
            this.noteNotifyLabel = null;
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

        final boolean hasDataForNoteNotifyLabel =
                (taskPanelDto.getNote() != null) && !(taskPanelDto.getNote().isEmpty());
        handleNoteNotifyLabelByActionChildComponent(
                getActionChildComponentWhenTryToUpdate(this.noteNotifyLabel, hasDataForNoteNotifyLabel));
    }

    @Override
    protected void setNotResizableChildComponents() {
        if (this.deadlineLabel != null) {
            this.deadlineLabel.setResizable(false);
        }
        if (this.completionRateLabel != null) {
            this.completionRateLabel.setResizable(false);
        }
        if (this.noteNotifyLabel != null) {
            this.noteNotifyLabel.setResizable(false);
        }
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
