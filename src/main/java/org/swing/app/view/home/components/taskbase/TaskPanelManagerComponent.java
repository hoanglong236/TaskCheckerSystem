package org.swing.app.view.home.components.taskbase;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.OptionPane;
import org.swing.app.view.components.request.DeletableTaskComponent;
import org.swing.app.view.components.request.InsertableTaskComponent;
import org.swing.app.view.components.ui.Button;
import org.swing.app.view.components.ui.Label;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.components.ui.Popup;
import org.swing.app.view.components.ui.PopupItem;
import org.swing.app.view.components.ui.VerticalScrollPane;
import org.swing.app.view.home.HomeWrapperComponent;
import org.swing.app.view.home.components.factory.TaskPanelContainerFactory;
import org.swing.app.view.home.components.factory.TaskPanelFactory;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public abstract class TaskPanelManagerComponent extends HomeWrapperComponent
        implements TaskPanelManager, InsertableTaskComponent, DeletableTaskComponent, ActionListener {

    private static final byte HORIZONTAL_GAP = ViewConstant.SMALL_H_GAP;
    private static final byte VERTICAL_GAP = ViewConstant.SMALL_V_GAP;
    private static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT, HORIZONTAL_GAP, VERTICAL_GAP);

    private Label titleLabel;
    private Button filterButton;
    private VerticalScrollPane verticalScrollPane;
    protected TaskPanelContainer taskPanelContainer;

    private Popup filterPopup;
    private PopupItem sortByCreateDatePopupItem;
    private PopupItem sortByModifyDatePopupItem;

    private final TaskPanelFactory taskPanelFactory;
    private final TaskPanelContainerFactory taskPanelContainerFactory;

    private TaskPanel taskPanelRequesting;

    public TaskPanelManagerComponent(HomeFrameController homeFrameController,
            TaskPanelFactory taskPanelFactory, TaskPanelContainerFactory taskPanelContainerFactory,
            String title, Set<TaskPanelDto> taskPanelDtos) {

        super(homeFrameController);
        this.taskPanelFactory = taskPanelFactory;
        this.taskPanelContainerFactory = taskPanelContainerFactory;
        setLayout(MAIN_LAYOUT);
        init(title, taskPanelDtos);
    }

    private void initTitleLabel(String title) {
        this.titleLabel = UIComponentFactory.createLabel(title);
    }

    private void initFilterButton() {
        this.filterButton = UIComponentFactory.createButton(ViewConstant.ICON_LOCATION_FILTER);
        this.filterButton.addActionListener(this);
    }

    private void initVerticalScrollPane() {
        this.verticalScrollPane = UIComponentFactory.createVerticalScrollPane();
    }

    private void initTaskPanelContainer(Set<TaskPanelDto> taskPanelDtos) {
        this.taskPanelContainer = this.taskPanelContainerFactory.createTaskPanelContainer(this.homeFrameController);

        for (final TaskPanelDto taskPanelDto : taskPanelDtos) {
            addTaskPanelByDto(taskPanelDto);
        }
    }

    private void initSortByCreateDatePopupItem() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.sortByCreateDatePopupItem = UIComponentFactory.createRadioButtonPopupItem(
                messageLoader.getMessage("sort.create.date.popup.item.title"));
        this.sortByCreateDatePopupItem.addActionListener(this);
    }

    private void initSortByModifyDatePopupItem() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.sortByModifyDatePopupItem = UIComponentFactory.createRadioButtonPopupItem(
                messageLoader.getMessage("sort.modify.date.popup.item.title"));
        this.sortByModifyDatePopupItem.addActionListener(this);
    }

    private void initFilterPopupToPrepareForFilterButtonEvent() {
        this.filterPopup = UIComponentFactory.createPopup();

        initSortByCreateDatePopupItem();
        this.filterPopup.addPopupItem(this.sortByCreateDatePopupItem);

        initSortByModifyDatePopupItem();
        this.filterPopup.addPopupItem(this.sortByModifyDatePopupItem);
    }

    private void init(String title, Set<TaskPanelDto> taskPanelDtos) {
        initTitleLabel(title);
        addChildComponent(this.titleLabel);

        initFilterButton();
        addChildComponent(this.filterButton);

        initVerticalScrollPane();
        addChildComponent(this.verticalScrollPane);

        initTaskPanelContainer(taskPanelDtos);
        this.verticalScrollPane.setViewportViewPanel(this.taskPanelContainer);

        initFilterPopupToPrepareForFilterButtonEvent();
    }

    public void addTaskPanelByDto(TaskPanelDto taskPanelDto) {
        final TaskPanel taskPanel = this.taskPanelFactory.createTaskPanel(
                this.homeFrameController, this, taskPanelDto);
        addTaskPanel(taskPanel);
    }

    public void addTaskPanel(TaskPanel taskPanel) {
        this.taskPanelContainer.addTaskPanel(taskPanel);
    }

    private void deleteTaskPanel(TaskPanel taskPanel) {
        this.taskPanelContainer.removeChildComponent(taskPanel);
    }

    @Override
    protected void loadChildComponentsSize() {
        final int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;

        final int commonComponentHeight = 40;

        final int filterBtnWidth = 40;
        this.childComponentSizeMap.put(this.filterButton, new Dimension(filterBtnWidth, commonComponentHeight));

        final int titleLabelWidth = availableWidth - HORIZONTAL_GAP - filterBtnWidth - HORIZONTAL_GAP;
        this.childComponentSizeMap.put(this.titleLabel, new Dimension(titleLabelWidth, commonComponentHeight));

        final int verticalScrollPaneWidth = availableWidth - HORIZONTAL_GAP;
        final int verticalScrollPaneHeight = availableHeight - VERTICAL_GAP - commonComponentHeight - VERTICAL_GAP;
        this.childComponentSizeMap.put(this.verticalScrollPane,
                new Dimension(verticalScrollPaneWidth, verticalScrollPaneHeight));
    }

    @Override
    protected void setNotResizableChildComponents() {
        this.verticalScrollPane.setResizable(true);
        this.filterButton.setResizable(false);
        this.titleLabel.setResizable(true);
    }

    private void onActionPerformedForFilterButton() {
        this.filterPopup.show(this.filterButton, 0, this.filterButton.getSize().height);
    }

    private void onActionPerformedForSortByCreateDatePopupItem() {
        this.taskPanelContainer.sortTaskPanel(TaskPanelContainer.SORT_BY_CREATE_DATE_ASC);
    }

    private void onActionPerformedForSortByModifyDatePopupItem() {
        this.taskPanelContainer.sortTaskPanel(TaskPanelContainer.SORT_BY_MODIFY_DATE_ASC);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final Object eventSource = e.getSource();

        if (eventSource == this.filterButton) {
            onActionPerformedForFilterButton();
            return;
        }
        if (eventSource == this.sortByCreateDatePopupItem) {
            onActionPerformedForSortByCreateDatePopupItem();
            return;
        }
        if (eventSource == this.sortByModifyDatePopupItem) {
            onActionPerformedForSortByModifyDatePopupItem();
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void insertTaskPanelHandler(byte taskType) {
        final boolean requestSuccess = this.homeFrameController
                .requestAddNewTaskPanel(taskType, this);

        if (!requestSuccess) {
            requestFailureHandler();
        }
    }

    @Override
    public void handlerForResultOfInsertTaskAction(boolean isSuccess, TaskPanelDto taskPanelDto) {
        final MessageLoader messageLoader = MessageLoader.getInstance();

        if (isSuccess) {
            addTaskPanelByDto(taskPanelDto);
            OptionPane.showMessageDialog(messageLoader.getMessage("insert.success.dialog"));
        } else {
            OptionPane.showMessageDialog(messageLoader.getMessage("insert.failure.dialog"));
        }
    }

    @Override
    public void deleteTaskPanelHandler(TaskPanel taskPanel) {
        if (this.homeFrameController.hasRequestingComponent()) {
            requestFailureHandler();
            return;
        }

        final MessageLoader messageLoader = MessageLoader.getInstance();
        final byte result = OptionPane.showConfirmDialog(messageLoader.getMessage("confirm.dialog.question"),
                messageLoader.getMessage("confirm.dialog.delete.task.title"));

        if (result == OptionPane.YES_DIALOG_OPTION) {
            final boolean requestSuccess = this.homeFrameController
                    .requestDeleteTaskPanel(this, taskPanel.getTaskId());

            if (!requestSuccess) {
                requestFailureHandler();
            }

            this.taskPanelRequesting = taskPanel;
        }
    }

    @Override
    public void handlerForResultOfDeleteTaskAction(boolean isSuccess) {
        final MessageLoader messageLoader = MessageLoader.getInstance();

        if (isSuccess) {
            dispose();
            deleteTaskPanel(this.taskPanelRequesting);
            refreshUI();
            OptionPane.showMessageDialog(messageLoader.getMessage("delete.success.dialog"));
        } else {
            OptionPane.showMessageDialog(messageLoader.getMessage("delete.failure.dialog"));
        }

        this.taskPanelRequesting = null;
    }
}
