package org.swing.app.view.home.components;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ComponentSizeConstants;
import org.swing.app.view.common.IconUrlConstants;
import org.swing.app.view.common.LayoutGapConstants;
import org.swing.app.view.common.ReserveSizeConstants;
import org.swing.app.view.components.ui.button.BasicButton;
import org.swing.app.view.components.ui.label.Label;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.components.ui.Popup;
import org.swing.app.view.components.ui.button.PopupItem;
import org.swing.app.view.components.ui.VerticalScrollPane;
import org.swing.app.view.home.HomeWrapperComponent;
import org.swing.app.view.home.components.taskpanel.TaskPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskPanelContainerWrapper extends HomeWrapperComponent implements ActionListener {

    private static final byte HORIZONTAL_GAP = LayoutGapConstants.SMALL_H_GAP;
    private static final byte VERTICAL_GAP = LayoutGapConstants.SMALL_V_GAP;
    private static final LayoutManager MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT, HORIZONTAL_GAP, VERTICAL_GAP);

    private Label titleLabel;
    private BasicButton filterButton;
    private VerticalScrollPane verticalScrollPane;
    private TaskPanelContainer taskPanelContainer;

    private int taskPanelContainerWidth;

    private Popup filterPopup;
    private PopupItem sortByCreateDatePopupItem;
    private PopupItem sortByUpdateDatePopupItem;

    public TaskPanelContainerWrapper(HomeFrameController homeFrameController, String title) {
        super(homeFrameController);
        setLayout(MAIN_LAYOUT);
        init(title);
    }

    private void initTitleLabel(String title) {
        this.titleLabel = UIComponentFactory.createLabel(title);
    }

    private void initFilterButton() {
        this.filterButton = UIComponentFactory.createBasicButton(IconUrlConstants.FILTER_ICON);
        this.filterButton.addActionListener(this);
    }

    private void initVerticalScrollPane() {
        this.verticalScrollPane = UIComponentFactory.createVerticalScrollPane();
    }

    private void initTaskPanelContainer() {
        this.taskPanelContainer = new TaskPanelContainer(this.homeFrameController);
    }

    private void initSortByCreateDatePopupItem() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.sortByCreateDatePopupItem = UIComponentFactory.createRadioButtonPopupItem(
                messageLoader.getMessage("sort.create.date.popup.item.title"));
        this.sortByCreateDatePopupItem.addActionListener(this);
    }

    private void initSortByUpdateDatePopupItem() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.sortByUpdateDatePopupItem = UIComponentFactory.createRadioButtonPopupItem(
                messageLoader.getMessage("sort.modify.date.popup.item.title"));
        this.sortByUpdateDatePopupItem.addActionListener(this);
    }

    private void initFilterPopupToPrepareForFilterButtonEvent() {
        this.filterPopup = UIComponentFactory.createPopup();

        initSortByCreateDatePopupItem();
        this.filterPopup.addPopupItem(this.sortByCreateDatePopupItem);

        initSortByUpdateDatePopupItem();
        this.filterPopup.addPopupItem(this.sortByUpdateDatePopupItem);
    }

    private void init(String title) {
        initTitleLabel(title);
        addChildComponent(this.titleLabel);

        initFilterButton();
        addChildComponent(this.filterButton);

        initVerticalScrollPane();
        addChildComponent(this.verticalScrollPane);

        initTaskPanelContainer();
        this.verticalScrollPane.setViewportViewPanel(this.taskPanelContainer);

        initFilterPopupToPrepareForFilterButtonEvent();
    }

    public void addTaskPanel(TaskPanel taskPanel) {
        this.taskPanelContainer.addTaskPanel(taskPanel);
    }

    public void removeTaskPanel(TaskPanel taskPanel) {
        this.taskPanelContainer.removeTaskPanel(taskPanel);
    }

    public void resizeTaskPanelInContainer(TaskPanel taskPanel) {
        this.taskPanelContainer.resizeTaskPanel(taskPanel);
    }

    @Override
    public void resize(Dimension dimension) {
        super.resize(dimension);
        this.taskPanelContainer.resizeWidth(this.taskPanelContainerWidth);
    }

    @Override
    protected void loadChildComponentsSize() {
        final int availableWidth = getSize().width - ReserveSizeConstants.SMALL_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ReserveSizeConstants.SMALL_RESERVE_HEIGHT;

        final int commonChildComponentHeight = 40;

        final int filterBtnWidth = 40;
        this.childComponentSizeMap.put(this.filterButton, new Dimension(filterBtnWidth, commonChildComponentHeight));

        final int titleLabelWidth = availableWidth - HORIZONTAL_GAP - filterBtnWidth - HORIZONTAL_GAP;
        this.childComponentSizeMap.put(this.titleLabel, new Dimension(titleLabelWidth, commonChildComponentHeight));

        final int verticalScrollPaneWidth = availableWidth - HORIZONTAL_GAP;
        final int verticalScrollPaneHeight =
                availableHeight - VERTICAL_GAP - commonChildComponentHeight - VERTICAL_GAP;
        this.childComponentSizeMap.put(this.verticalScrollPane,
                new Dimension(verticalScrollPaneWidth, verticalScrollPaneHeight));

        this.taskPanelContainerWidth = verticalScrollPaneWidth - ComponentSizeConstants.SCROLLBAR_WIDTH
                - ReserveSizeConstants.SMALL_RESERVE_WIDTH;
    }

    private void onActionPerformedForFilterButton() {
        this.filterPopup.show(this.filterButton, 0, this.filterButton.getSize().height);
    }

    private void onActionPerformedForSortByCreateDatePopupItem() {
        this.taskPanelContainer.sortTaskPanel(TaskPanelContainer.SORT_BY_CREATE_DATE_ASC);
    }

    private void onActionPerformedForSortByUpdateDatePopupItem() {
        this.taskPanelContainer.sortTaskPanel(TaskPanelContainer.SORT_BY_UPDATE_DATE_ASC);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final Object eventSource = e.getSource();

        if (eventSource == this.filterButton.getSourceComponent()) {
            onActionPerformedForFilterButton();
            return;
        }
        if (eventSource == this.sortByCreateDatePopupItem.getSourceComponent()) {
            onActionPerformedForSortByCreateDatePopupItem();
            return;
        }
        if (eventSource == this.sortByUpdateDatePopupItem.getSourceComponent()) {
            onActionPerformedForSortByUpdateDatePopupItem();
            return;
        }
        throw new IllegalArgumentException();
    }
}
