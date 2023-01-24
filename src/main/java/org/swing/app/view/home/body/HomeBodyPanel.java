package org.swing.app.view.home.body;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskDto;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.LayoutGapConstants;
import org.swing.app.view.common.ReserveSizeConstants;
import org.swing.app.view.home.HomeWrapperComponent;
import org.swing.app.view.home.components.taskcontentpanel.NodeTaskContentPanel;
import org.swing.app.view.home.components.taskcontentpanel.RootTaskContentPanel;
import org.swing.app.view.home.components.taskcontentpanel.TaskContentPanel;
import org.swing.app.view.home.observer.taskcompletionrate.TaskCompletionRateEventSubject;
import org.swing.app.view.home.observer.taskcontent.SubTaskContentEventSubject;
import org.swing.app.view.home.observer.taskcontent.TaskContentEventObserver;
import org.swing.app.view.home.observer.taskcontent.TaskContentEventSubject;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.Set;

public class HomeBodyPanel extends HomeWrapperComponent implements TaskContentEventObserver {

    private static final byte HORIZONTAL_GAP = LayoutGapConstants.MEDIUM_H_GAP;
    private static final byte VERTICAL_GAP = LayoutGapConstants.MEDIUM_V_GAP;
    private static final LayoutManager MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT, HORIZONTAL_GAP, VERTICAL_GAP);

    private TaskContentPanel mainContentPanel;
    private TaskContentPanel subContentPanel;

    public HomeBodyPanel(HomeFrameController homeFrameController) {
        super(homeFrameController);
        setLayout(MAIN_LAYOUT);
    }

    @Override
    protected void loadChildComponentsSize() {
        int availableWidth = getSize().width - ReserveSizeConstants.MEDIUM_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ReserveSizeConstants.MEDIUM_RESERVE_HEIGHT;

        final int maxChildComponentHeight = availableHeight - VERTICAL_GAP;

        if (this.subContentPanel != null) {
            final int subContentPanelWidth = (int) (((float) 0.4) * availableWidth);
            this.childComponentSizeMap.put(this.subContentPanel,
                    new Dimension(subContentPanelWidth, maxChildComponentHeight));
            availableWidth -= HORIZONTAL_GAP + subContentPanelWidth;
        }
        if (this.mainContentPanel != null) {
            final int mainContentPanelWidth = availableWidth - HORIZONTAL_GAP;
            this.childComponentSizeMap.put(this.mainContentPanel,
                    new Dimension(mainContentPanelWidth, maxChildComponentHeight));
        }
    }

    private void initMainContentPanel(TaskPanelDto masterTaskPanelDto, Set<TaskPanelDto> taskPanelDtos,
            TaskCompletionRateEventSubject taskCompletionRateEventSubject) {

        this.mainContentPanel = new RootTaskContentPanel(this.homeFrameController, masterTaskPanelDto, taskPanelDtos,
                taskCompletionRateEventSubject);

        final TaskContentEventSubject taskContentEventSubject = new SubTaskContentEventSubject();
        taskContentEventSubject.registerObserver(this);

        this.mainContentPanel.setTaskContentEventSubject(taskContentEventSubject);
    }

    private void removeMainContentPanel() {
        this.mainContentPanel.cancelAllEventListeners();

        final TaskContentEventSubject taskContentEventSubject = this.mainContentPanel.getTaskContentEventSubject();
        taskContentEventSubject.removeObserver(this);

        this.mainContentPanel = null;
    }

    private void initSubContentPanel(TaskPanelDto masterTaskPanelDto, Set<TaskPanelDto> taskPanelDtos,
            TaskCompletionRateEventSubject taskCompletionRateEventSubject) {

        this.subContentPanel = new NodeTaskContentPanel(this.homeFrameController, masterTaskPanelDto, taskPanelDtos,
                taskCompletionRateEventSubject);
    }

    private void removeSubContentPanel() {
        this.subContentPanel.cancelAllEventListeners();
        this.subContentPanel = null;
    }

    @Override
    public void handleLoadMainContent(TaskPanelDto masterTaskPanelDto, Set<TaskPanelDto> taskPanelDtos,
            TaskCompletionRateEventSubject masterTaskCompletionRateEventSubject) {

        if (this.mainContentPanel != null) {
            handleClearMainContent();
        }

        initMainContentPanel(masterTaskPanelDto, taskPanelDtos, masterTaskCompletionRateEventSubject);
        addChildComponent(this.mainContentPanel);

        resize(getSize());
    }

    @Override
    public void handleUpdateMasterTaskInMainContent(TaskDto masterTaskDto) {
        this.mainContentPanel.updateMasterTask(masterTaskDto);
    }

    @Override
    public void handleClearMainContent() {
        if (this.subContentPanel != null) {
            removeSubContentPanel();
        }
        removeMainContentPanel();
    }

    @Override
    public void handleLoadSubContent(TaskPanelDto masterTaskPanelDto, Set<TaskPanelDto> taskPanelDtos,
            TaskCompletionRateEventSubject masterTaskCompletionRateEventSubject) {

        initSubContentPanel(masterTaskPanelDto, taskPanelDtos, masterTaskCompletionRateEventSubject);
        addChildComponent(this.subContentPanel);

        resize(getSize());
    }

    @Override
    public void handleUpdateMasterTaskInSubContent(TaskDto masterTaskDto) {
        this.subContentPanel.updateMasterTask(masterTaskDto);
    }

    @Override
    public void handleClearSubContent() {
        removeSubContentPanel();
        resize(getSize());
    }
}
