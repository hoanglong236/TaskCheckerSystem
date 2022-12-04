package org.swing.app.view.home.components;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.PanelWrapperComponent;
import org.swing.app.view.components.ui.CompletionRateLabel;
import org.swing.app.view.components.ui.DeadlineLabel;
import org.swing.app.view.components.ui.Label;
import org.swing.app.view.components.ui.UIComponentFactory;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.time.LocalDateTime;

public abstract class TaskCenterPanel extends PanelWrapperComponent {

    protected static FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT,
            ViewConstant.SMALL_H_GAP, ViewConstant.SMALL_V_GAP);

    protected Label titleLabel;
    protected DeadlineLabel deadlineLabel;
    protected CompletionRateLabel completionRateLabel;
    protected Label noteNotifyLabel;

    public TaskCenterPanel(TaskPanelDto taskPanelDto) {
        super();
        setLayout(MAIN_LAYOUT);
        init(taskPanelDto);
    }

    protected void initTitleLabel(String title) {
        this.titleLabel = UIComponentFactory.createLabel(title);
    }

    protected void initDeadlineLabel(LocalDateTime startDatetime, LocalDateTime finishDatetime) {
        this.deadlineLabel = UIComponentFactory.createDeadlineLabel(startDatetime, finishDatetime);
    }

    protected void initCompletionRateLabel(int completedCount, int totalCount) {
        this.completionRateLabel = UIComponentFactory.createCompletionRateLabel(completedCount, totalCount);
    }

    protected void initNoteNotifyLabel() {
        this.noteNotifyLabel = UIComponentFactory.createLabel(ViewConstant.ICON_LOCATION_NOTE);
    }

    protected void init(TaskPanelDto taskPanelDto) {
        initTitleLabel(taskPanelDto.getTitle());
        addChildComponent(this.titleLabel);
    }

    public void update(TaskPanelDto taskPanelDto) {
        this.titleLabel.setText(taskPanelDto.getTitle());
    }

    private boolean hasOtherChildComponents() {
        return this.childComponents.size() > 1;
    }

    protected abstract void loadOtherChildComponentsSize();

    @Override
    protected void loadChildComponentsSize() {
        final int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;

        final int maxChildComponentWidth = availableWidth - MAIN_LAYOUT.getHgap();
        final int maxChildComponentHeight = availableHeight - MAIN_LAYOUT.getVgap();

        int titleLabelHeight = maxChildComponentHeight;
        if (hasOtherChildComponents()) {
            titleLabelHeight = availableHeight / 2 - MAIN_LAYOUT.getVgap();
            loadOtherChildComponentsSize();
        }
        this.childComponentSizeMap.put(this.titleLabel, new Dimension(maxChildComponentWidth, titleLabelHeight));
    }
}