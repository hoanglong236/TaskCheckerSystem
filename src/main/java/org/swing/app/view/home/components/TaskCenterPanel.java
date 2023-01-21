package org.swing.app.view.home.components;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskDto;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.IconUrlConstants;
import org.swing.app.view.common.LayoutGapConstants;
import org.swing.app.view.common.ReserveSizeConstants;
import org.swing.app.view.components.ui.label.CompletionRateLabel;
import org.swing.app.view.components.ui.label.CountDownLabel;
import org.swing.app.view.components.ui.label.Label;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.home.HomeWrapperComponent;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.time.LocalDateTime;

public class TaskCenterPanel extends HomeWrapperComponent {

    private static final byte HORIZONTAL_GAP = LayoutGapConstants.SMALL_H_GAP;
    private static final byte VERTICAL_GAP = LayoutGapConstants.SMALL_V_GAP;
    private static final LayoutManager MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT, HORIZONTAL_GAP, VERTICAL_GAP);

    private Label titleLabel;
    private CountDownLabel deadlineLabel;
    private CompletionRateLabel completionRateLabel;
    private Label noteNotifyLabel;

    public TaskCenterPanel(HomeFrameController homeFrameController, TaskPanelDto taskPanelDto) {
        super(homeFrameController);
        setLayout(MAIN_LAYOUT);
        init(taskPanelDto);
    }

    private void initTitleLabel(String title) {
        this.titleLabel = UIComponentFactory.createLabel(title);
    }

    private void initDeadlineLabel(LocalDateTime deadline) {
        this.deadlineLabel = UIComponentFactory.createCountDownLabel(deadline);
    }

    private void initCompletionRateLabel(int completedCount, int totalCount) {
        this.completionRateLabel = UIComponentFactory.createCompletionRateLabel(completedCount, totalCount);
    }

    private void initNoteNotifyLabel() {
        this.noteNotifyLabel = UIComponentFactory.createLabel("");
        this.noteNotifyLabel.setIcon(IconUrlConstants.ICON_NOTE);
    }

    private void init(TaskPanelDto taskPanelDto) {
        final TaskDto taskDto = taskPanelDto.getTaskDto();

        initTitleLabel(taskDto.getTitle());
        addChildComponent(this.titleLabel);

        if (taskDto.getDeadline() != null) {
            initDeadlineLabel(taskDto.getDeadline());
            addChildComponent(this.deadlineLabel);
        }
        if (taskPanelDto.getChildTaskCount() > 0) {
            initCompletionRateLabel(taskPanelDto.getCompletedChildTaskCount(), taskPanelDto.getChildTaskCount());
            addChildComponent(this.completionRateLabel);
        }
        if (taskDto.getNote() != null && !taskDto.getNote().isEmpty()) {
            initNoteNotifyLabel();
            addChildComponent(this.noteNotifyLabel);
        }
    }

    private boolean isNeedUpdateChildComponentFowNewData(boolean isChildComponentExist, boolean isNewDataValid) {
        return isChildComponentExist && isNewDataValid;
    }

    private boolean isNeedRemoveChildComponentFowNewData(boolean isChildComponentExist, boolean isNewDataValid) {
        return isChildComponentExist && !isNewDataValid;
    }

    private boolean isNeedInitChildComponentFowNewData(boolean isChildComponentExist, boolean isNewDataValid) {
        return !isChildComponentExist && isNewDataValid;
    }

    private void handleDeadlineLabelForNewData(LocalDateTime newDeadline) {
        final boolean isChildComponentExist = this.deadlineLabel != null;
        final boolean isNewDataValid = newDeadline != null;

        if (isNeedUpdateChildComponentFowNewData(isChildComponentExist, isNewDataValid)) {
            this.deadlineLabel.update(newDeadline);
            return;
        }
        if (isNeedRemoveChildComponentFowNewData(isChildComponentExist, isNewDataValid)) {
            removeChildComponent(this.deadlineLabel);
            this.deadlineLabel.cancelAllEventListeners();
            this.deadlineLabel = null;
            return;
        }
        if (isNeedInitChildComponentFowNewData(isChildComponentExist, isNewDataValid)) {
            initDeadlineLabel(newDeadline);
            addChildComponent(this.deadlineLabel);
        }
    }

    private void handleCompletionRateLabelForNewData(int completedChildTaskCount, int childTaskCount) {
        final boolean isChildComponentExist = this.completionRateLabel != null;
        final boolean isNewDataValid = childTaskCount > 0;

        if (isNeedUpdateChildComponentFowNewData(isChildComponentExist, isNewDataValid)) {
            this.completionRateLabel.update(completedChildTaskCount, childTaskCount);
            return;
        }
        if (isNeedRemoveChildComponentFowNewData(isChildComponentExist, isNewDataValid)) {
            removeChildComponent(this.completionRateLabel);
            this.completionRateLabel.cancelAllEventListeners();
            this.completionRateLabel = null;
            return;
        }
        if (isNeedInitChildComponentFowNewData(isChildComponentExist, isNewDataValid)) {
            initCompletionRateLabel(completedChildTaskCount, childTaskCount);
            addChildComponent(this.completionRateLabel);
        }
    }

    private void handleNoteNotifyLabelForNewData(boolean hasNote) {
        final boolean isChildComponentExist = this.noteNotifyLabel != null;
        final boolean isNewDataValid = hasNote;

        if (isNeedRemoveChildComponentFowNewData(isChildComponentExist, isNewDataValid)) {
            removeChildComponent(this.noteNotifyLabel);
            this.noteNotifyLabel.cancelAllEventListeners();
            this.noteNotifyLabel = null;
            return;
        }
        if (isNeedInitChildComponentFowNewData(isChildComponentExist, isNewDataValid)) {
            initNoteNotifyLabel();
            addChildComponent(this.noteNotifyLabel);
        }
    }

    public void update(TaskPanelDto taskPanelDto) {
        final TaskDto taskDto = taskPanelDto.getTaskDto();
        this.titleLabel.setText(taskDto.getTitle());

        handleDeadlineLabelForNewData(taskDto.getDeadline());
        handleCompletionRateLabelForNewData(taskPanelDto.getCompletedChildTaskCount(),
                taskPanelDto.getChildTaskCount());

        final boolean hasNote = (taskDto.getNote() != null) && !(taskDto.getNote().isEmpty());
        handleNoteNotifyLabelForNewData(hasNote);

        resize(getSize());
    }

    public void updateCompletionRate(int completedChildTaskCount, int childTaskCount) {
        handleCompletionRateLabelForNewData(completedChildTaskCount, childTaskCount);
        resize(getSize());
    }

    @Override
    protected void loadChildComponentsSize() {
        final int availableWidth = getSize().width - ReserveSizeConstants.SMALL_RESERVE_WIDTH;
        int availableHeight = getSize().height - ReserveSizeConstants.SMALL_RESERVE_HEIGHT;

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