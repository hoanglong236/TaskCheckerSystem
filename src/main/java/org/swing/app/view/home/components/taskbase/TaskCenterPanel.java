package org.swing.app.view.home.components.taskbase;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.ViewComponent;
import org.swing.app.view.components.ui.CompletionRateLabel;
import org.swing.app.view.components.ui.DeadlineLabel;
import org.swing.app.view.components.ui.Label;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.home.HomeWrapperComponent;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.time.LocalDateTime;

public abstract class TaskCenterPanel extends HomeWrapperComponent {

    private static final byte DO_NOTHING_ACTION_CHILD_COMPONENT = 0;
    private static final byte INIT_ACTION_CHILD_COMPONENT = 1;
    private static final byte UPDATE_ACTION_CHILD_COMPONENT = 2;
    private static final byte REMOVE_ACTION_CHILD_COMPONENT = 3;

    private static final byte HORIZONTAL_GAP = ViewConstant.SMALL_H_GAP;
    private static final byte VERTICAL_GAP = ViewConstant.SMALL_V_GAP;
    private static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT, HORIZONTAL_GAP, VERTICAL_GAP);

    private Label titleLabel;
    private DeadlineLabel deadlineLabel;
    private CompletionRateLabel completionRateLabel;
    private Label noteNotifyLabel;

    public TaskCenterPanel(HomeFrameController homeFrameController, TaskPanelDto taskPanelDto) {
        super(homeFrameController);
        setLayout(MAIN_LAYOUT);
        init(taskPanelDto);
    }

    protected abstract boolean isNeedDeadlineLabel();
    protected abstract boolean isNeedCompletionRateLabel();
    protected abstract boolean isNeedNoteNotifyLabel();

    private void initTitleLabel(String title) {
        this.titleLabel = UIComponentFactory.createLabel(title);
    }

    private void initDeadlineLabel(LocalDateTime startDatetime, LocalDateTime finishDatetime) {
        this.deadlineLabel = UIComponentFactory.createDeadlineLabel(startDatetime, finishDatetime);
    }

    private void initCompletionRateLabel(int completedCount, int totalCount) {
        this.completionRateLabel = UIComponentFactory.createCompletionRateLabel(completedCount, totalCount);
    }

    private void initNoteNotifyLabel() {
        this.noteNotifyLabel = UIComponentFactory.createLabel(ViewConstant.ICON_LOCATION_NOTE);
    }

    private void init(TaskPanelDto taskPanelDto) {
        initTitleLabel(taskPanelDto.getTitle());
        addChildComponent(this.titleLabel);

        if (isNeedDeadlineLabel() && taskPanelDto.getFinishDatetime() != null) {
            initDeadlineLabel(taskPanelDto.getStartDatetime(), taskPanelDto.getFinishDatetime());
            addChildComponent(this.deadlineLabel);
        }

        if (isNeedCompletionRateLabel() && taskPanelDto.getChildTaskCount() > 0) {
            initCompletionRateLabel(taskPanelDto.getChildTaskCompletedCount(), taskPanelDto.getChildTaskCount());
            addChildComponent(this.completionRateLabel);
        }

        if (isNeedNoteNotifyLabel() && !(taskPanelDto.getNote().isEmpty())) {
            initNoteNotifyLabel();
            addChildComponent(this.noteNotifyLabel);
        }
    }

    private void updateTitleLabel(String title) {
        this.titleLabel.setText(title);
    }

    private void updateDeadlineLabel(LocalDateTime startDateTime, LocalDateTime finishDateTime) {
        this.deadlineLabel.update(startDateTime, finishDateTime);
    }

    private void updateCompletionRateLabel(int completedCount, int totalCount) {
        this.completionRateLabel.update(completedCount, totalCount);
    }

    private byte getActionChildComponentWhenTryToUpdate(ViewComponent componentTryToUpdate, boolean hasData) {
        if (hasData) {
            if (componentTryToUpdate != null) {
                return UPDATE_ACTION_CHILD_COMPONENT;
            }
            return INIT_ACTION_CHILD_COMPONENT;
        }

        if (componentTryToUpdate != null) {
            return REMOVE_ACTION_CHILD_COMPONENT;
        }

        return DO_NOTHING_ACTION_CHILD_COMPONENT;
    }

    private void handleDeadlineLabelByActionChildComponent(byte actionChildComponent,
            LocalDateTime startDateTime, LocalDateTime finishDateTime) {

        if (actionChildComponent == INIT_ACTION_CHILD_COMPONENT) {
            initDeadlineLabel(startDateTime, finishDateTime);
            addChildComponent(this.deadlineLabel);
        }
        else if (actionChildComponent == UPDATE_ACTION_CHILD_COMPONENT) {
            updateDeadlineLabel(startDateTime, finishDateTime);
        }
        else if (actionChildComponent == REMOVE_ACTION_CHILD_COMPONENT) {
            removeChildComponent(this.deadlineLabel);
            this.deadlineLabel.dispose();
            this.deadlineLabel = null;
        }
    }

    private void handleCompletionRateLabelByActionChildComponent(byte actionChildComponent,
            int completedCount, int totalCount) {

        if (actionChildComponent == INIT_ACTION_CHILD_COMPONENT) {
            initCompletionRateLabel(completedCount, totalCount);
            addChildComponent(this.completionRateLabel);
        }
        else if (actionChildComponent == UPDATE_ACTION_CHILD_COMPONENT) {
            updateCompletionRateLabel(completedCount, totalCount);
        }
        else if (actionChildComponent == REMOVE_ACTION_CHILD_COMPONENT) {
            removeChildComponent(this.completionRateLabel);
            this.completionRateLabel.dispose();
            this.completionRateLabel = null;
        }
    }

    private void handleNoteNotifyLabelByActionChildComponent(byte actionChildComponent) {
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

    public void update(TaskPanelDto taskPanelDto) {
        updateTitleLabel(taskPanelDto.getTitle());

        if (isNeedDeadlineLabel()) {
            final boolean hasDataForDeadlineLabel = taskPanelDto.getFinishDatetime() == null;
            handleDeadlineLabelByActionChildComponent(
                    getActionChildComponentWhenTryToUpdate(this.deadlineLabel, hasDataForDeadlineLabel),
                    taskPanelDto.getStartDatetime(), taskPanelDto.getFinishDatetime());
        }

        if (isNeedCompletionRateLabel()) {
            final boolean hasDataForCompletionRateLabel = taskPanelDto.getChildTaskCount() > 0;
            handleCompletionRateLabelByActionChildComponent(
                    getActionChildComponentWhenTryToUpdate(this.completionRateLabel, hasDataForCompletionRateLabel),
                    taskPanelDto.getChildTaskCompletedCount(), taskPanelDto.getChildTaskCount());
        }

        if (isNeedNoteNotifyLabel()) {
            final boolean hasDataForNoteNotifyLabel =
                    (taskPanelDto.getNote() != null) && !(taskPanelDto.getNote().isEmpty());
            handleNoteNotifyLabelByActionChildComponent(
                    getActionChildComponentWhenTryToUpdate(this.noteNotifyLabel, hasDataForNoteNotifyLabel));
        }
    }

    @Override
    protected void loadChildComponentsSize() {
        final int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;

        final int maxChildComponentWidth = availableWidth - HORIZONTAL_GAP;

        boolean hasOtherChildComponents = false;
        final int commonChildComponentHeight = availableHeight / 2 - VERTICAL_GAP;

        if (this.deadlineLabel != null) {
            final int deadlineLabelWidth = 100;
            this.childComponentSizeMap.put(this.deadlineLabel,
                    new Dimension(deadlineLabelWidth, commonChildComponentHeight));
            hasOtherChildComponents = true;
        }

        if (this.completionRateLabel != null) {
            final int completionRateLabelWidth = 80;
            this.childComponentSizeMap.put(this.completionRateLabel,
                    new Dimension(completionRateLabelWidth, commonChildComponentHeight));
            hasOtherChildComponents = true;
        }

        if (noteNotifyLabel != null) {
            final int noteNotifyLabelWidth = 30;
            this.childComponentSizeMap.put(this.noteNotifyLabel,
                    new Dimension(noteNotifyLabelWidth, commonChildComponentHeight));
            hasOtherChildComponents = true;
        }

        if (hasOtherChildComponents) {
            availableHeight -= VERTICAL_GAP + commonChildComponentHeight;
        }

        final int titleLabelHeight = availableHeight - VERTICAL_GAP;
        this.childComponentSizeMap.put(this.titleLabel, new Dimension(maxChildComponentWidth, titleLabelHeight));
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
}