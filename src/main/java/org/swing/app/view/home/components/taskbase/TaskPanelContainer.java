package org.swing.app.view.home.components.taskbase;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.components.ui.Label;
import org.swing.app.view.home.HomeWrapperComponent;
import org.swing.app.view.home.comparetor.TaskPanelComparator;
import org.swing.app.view.home.comparetor.TaskPanelModifyDateComparator;
import org.swing.app.view.home.comparetor.TaskPanelCreateDateComparator;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public abstract class TaskPanelContainer extends HomeWrapperComponent
        implements MouseListener {

    public static final byte SORT_BY_CREATE_DATE_ASC = 0;
//    public static final byte SORT_BY_CREATE_DATE_DESC = 1;
    public static final byte SORT_BY_MODIFY_DATE_ASC = 2;
//    public static final byte SORT_BY_MODIFY_DATE_DESC = 3;

    private static final byte HORIZONTAL_GAP = ViewConstant.SMALL_H_GAP;
    private static final byte VERTICAL_GAP = ViewConstant.SMALL_V_GAP;
    private static final LayoutManager MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT, HORIZONTAL_GAP, VERTICAL_GAP);

    private static final byte NOTIFY_LABEL_WIDTH = ViewConstant.NOTIFY_LABEL_WIDTH;
    private static final byte NOTIFY_LABEL_HEIGHT = ViewConstant.NOTIFY_LABEL_HEIGHT;

    private Label notifyLabel;

    private int preferHeight = ViewConstant.SMALL_RESERVE_HEIGHT;

    private final List<TaskPanel> incompleteTaskPanels = new ArrayList<>();
    private final List<TaskPanel> completedTaskPanels = new ArrayList<>();

    private Comparator<TaskPanel> comparator;

    public TaskPanelContainer(HomeFrameController homeFrameController) {
        super(homeFrameController);
        this.comparator = new TaskPanelCreateDateComparator();

        setLayout(MAIN_LAYOUT);
        init();
    }

    protected abstract boolean hasNotifyLabel();

    private void initNotifyLabel() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.notifyLabel = UIComponentFactory.createLabel(messageLoader.getMessage("label.notify.text"));
        this.notifyLabel.setVisible(false);
    }

    private void init() {
        if (hasNotifyLabel()) {
            initNotifyLabel();
            addChildComponent(this.notifyLabel);
        }
    }

    private void loadTaskPanelsSize(Iterator<TaskPanel> taskPanelIterator, int taskPanelWidth) {
        while (taskPanelIterator.hasNext()) {
            final TaskPanel taskPanel = taskPanelIterator.next();
            this.childComponentSizeMap.put(taskPanel, new Dimension(taskPanelWidth, taskPanel.getPreferHeight()));
        }
    }

    @Override
    protected void loadChildComponentsSize() {
        final int preferChildComponentWidth = getPreferChildComponentWidth();

        loadTaskPanelsSize(this.incompleteTaskPanels.iterator(), preferChildComponentWidth);
        loadTaskPanelsSize(this.completedTaskPanels.iterator(), preferChildComponentWidth);

        if (hasNotifyLabel() && this.notifyLabel.isVisible()) {
            this.childComponentSizeMap.put(this.notifyLabel, new Dimension(NOTIFY_LABEL_WIDTH, NOTIFY_LABEL_HEIGHT));
        }
    }

    public void resizeHeightWithoutResizeChildComponent(int height) {
        this.component.setPreferredSize(new Dimension(getSize().width, height));
    }

    @Override
    protected void setNotResizableChildComponents() {
    }

    private int getPositionToAddingIncompleteTaskPanelInUI(TaskPanel taskPanel) {
        return this.incompleteTaskPanels.indexOf(taskPanel);
    }

    private int getPositionToAddingCompletedTaskPanelInUI(TaskPanel taskPanel) {
        final int startIndexOfCompletedTaskPanel = getChildComponentPosition(this.notifyLabel) + 1;
        return startIndexOfCompletedTaskPanel + this.completedTaskPanels.indexOf(taskPanel);
    }

    public void addTaskPanel(TaskPanel taskPanel) {
        taskPanel.resize(new Dimension(getPreferChildComponentWidth(), taskPanel.getPreferHeight()));

        int positionToAddInUI;

        if (taskPanel.isCompleted()) {
            this.completedTaskPanels.add(taskPanel);
            this.completedTaskPanels.sort(this.comparator);
            positionToAddInUI = getPositionToAddingIncompleteTaskPanelInUI(taskPanel);
        } else {
            this.incompleteTaskPanels.add(taskPanel);
            this.incompleteTaskPanels.sort(this.comparator);
            positionToAddInUI = getPositionToAddingCompletedTaskPanelInUI(taskPanel);
        }

        addChildComponent(taskPanel, positionToAddInUI);

        if (hasNotifyLabel() && !(this.notifyLabel.isVisible())) {
            if (this.completedTaskPanels.size() > 0) {
                this.notifyLabel.setVisible(true);
                this.preferHeight += VERTICAL_GAP + NOTIFY_LABEL_HEIGHT;
            }
        }

        this.preferHeight += VERTICAL_GAP + taskPanel.getSize().height;
        resizeHeightWithoutResizeChildComponent(this.preferHeight);
    }

    public void deleteTaskPanel(TaskPanel taskPanel) {
        removeChildComponent(taskPanel);

        if (taskPanel.isCompleted()) {
            this.completedTaskPanels.remove(taskPanel);
        } else {
            this.incompleteTaskPanels.remove(taskPanel);
        }

        if (hasNotifyLabel() && this.notifyLabel.isVisible()) {
            if (this.completedTaskPanels.size() == 0) {
                this.notifyLabel.setVisible(false);
                this.preferHeight -= VERTICAL_GAP + NOTIFY_LABEL_HEIGHT;
            }
        }

        this.preferHeight -= VERTICAL_GAP + taskPanel.getSize().height;
        resizeHeightWithoutResizeChildComponent(this.preferHeight);
    }

    protected int getPreferChildComponentWidth() {
        final int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        return availableWidth - HORIZONTAL_GAP;
    }

    private void reSortTaskPanels(TaskPanelComparator taskPanelComparator) {
        this.comparator = taskPanelComparator;

        for (final TaskPanel taskPanel : this.incompleteTaskPanels) {
            removeChildComponent(taskPanel);
            addChildComponent(taskPanel, getPositionToAddingIncompleteTaskPanelInUI(taskPanel));
        }

        for (final TaskPanel taskPanel : this.completedTaskPanels) {
            removeChildComponent(taskPanel);
            addChildComponent(taskPanel, getPositionToAddingCompletedTaskPanelInUI(taskPanel));
        }

        refreshUI();
    }

    private void sortTaskPanelByCreateDate() {
        reSortTaskPanels(new TaskPanelCreateDateComparator());
    }

    private void sortTaskPanelByModifyDate() {
        reSortTaskPanels(new TaskPanelModifyDateComparator());
    }

    public void sortTaskPanel(byte typeOfSortBy) {
        switch (typeOfSortBy) {
            case SORT_BY_CREATE_DATE_ASC:
                sortTaskPanelByCreateDate();
                break;
            case SORT_BY_MODIFY_DATE_ASC:
                sortTaskPanelByModifyDate();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private void deactivateTaskPanels(Iterator<TaskPanel> taskPanelIterator) {
        while (taskPanelIterator.hasNext()) {
            final TaskPanel taskPanel = taskPanelIterator.next();
            taskPanel.deactivate();
        }
    }

    private void deactivateAllTaskPanels() {
        deactivateTaskPanels(this.incompleteTaskPanels.iterator());
        deactivateTaskPanels(this.completedTaskPanels.iterator());
    }

    private void onMousePressedForTaskPanel(TaskPanel taskPanel) {
        final boolean requestSuccess = this.homeFrameController.requestLoadTaskContent(
                taskPanel.getTaskTypeToRequest(), taskPanel.getTaskId());
        if (!requestSuccess) {
            requestFailureHandler();
            return;
        }

        deactivateAllTaskPanels();
        taskPanel.activate();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        final Object eventSource = e.getSource();

        if (eventSource instanceof TaskPanel) {
            onMousePressedForTaskPanel((TaskPanel) eventSource);
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
