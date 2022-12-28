package org.swing.app.view.home.components.taskbase;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.components.request.LoadableTaskComponent;
import org.swing.app.view.components.ui.label.Label;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class TaskPanelContainer extends HomeWrapperComponent
        implements LoadableTaskComponent, MouseListener {

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

    private final Map<Object, TaskPanel> sourceComponentTaskPanelMap = new HashMap<>();

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
        this.sourceComponent.setPreferredSize(new Dimension(getSize().width, height));
    }

    @Override
    protected void setNotResizableChildComponents() {
    }

    private void displayNotifyLabelIfNecessary() {
        if (hasNotifyLabel() && !(this.notifyLabel.isVisible())) {
            if (this.completedTaskPanels.size() > 0) {
                this.notifyLabel.setVisible(true);
                this.preferHeight += VERTICAL_GAP + NOTIFY_LABEL_HEIGHT;
                resizeHeightWithoutResizeChildComponent(this.preferHeight);
            }
        }
    }

    private void hiddenNotifyLabelIfNecessary() {
        if (hasNotifyLabel() && this.notifyLabel.isVisible()) {
            if (this.completedTaskPanels.size() == 0) {
                this.notifyLabel.setVisible(false);
                this.preferHeight -= VERTICAL_GAP + NOTIFY_LABEL_HEIGHT;
                resizeHeightWithoutResizeChildComponent(this.preferHeight);
            }
        }
    }

    private int getTaskPanelPositionToAdding(TaskPanel taskPanel) {
        if (taskPanel.isCompleted()) {
            this.completedTaskPanels.add(taskPanel);
            this.completedTaskPanels.sort(this.comparator);

            final int notifyLabelPosition = getChildComponentPosition(this.notifyLabel);
            return notifyLabelPosition + this.completedTaskPanels.indexOf(taskPanel);
        } else {
            this.incompleteTaskPanels.add(taskPanel);
            this.incompleteTaskPanels.sort(this.comparator);

            return this.incompleteTaskPanels.indexOf(taskPanel);
        }
    }

    public void addTaskPanel(TaskPanel taskPanel) {
        this.sourceComponentTaskPanelMap.put(taskPanel.getSourceComponent(), taskPanel);
        taskPanel.resize(new Dimension(getPreferChildComponentWidth(), taskPanel.getPreferHeight()));

        final int positionToAddInUI = getTaskPanelPositionToAdding(taskPanel);
        addChildComponent(taskPanel, positionToAddInUI);

        this.preferHeight += VERTICAL_GAP + taskPanel.getSize().height;
        resizeHeightWithoutResizeChildComponent(this.preferHeight);

        displayNotifyLabelIfNecessary();
    }

    public void deleteTaskPanel(TaskPanel taskPanel) {
        this.sourceComponentTaskPanelMap.remove(taskPanel.getSourceComponent());

        if (taskPanel.isCompleted()) {
            this.completedTaskPanels.remove(taskPanel);
        } else {
            this.incompleteTaskPanels.remove(taskPanel);
        }

        removeChildComponent(taskPanel);

        this.preferHeight -= VERTICAL_GAP + taskPanel.getSize().height;
        resizeHeightWithoutResizeChildComponent(this.preferHeight);

        hiddenNotifyLabelIfNecessary();
    }

    protected int getPreferChildComponentWidth() {
        final int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        return availableWidth - HORIZONTAL_GAP;
    }

    private void reSortTaskPanels(TaskPanelComparator taskPanelComparator) {
        this.comparator = taskPanelComparator;

        this.incompleteTaskPanels.sort(this.comparator);
        for (final TaskPanel taskPanel : this.incompleteTaskPanels) {
            removeChildComponent(taskPanel);

            final int incompleteTaskPanelPosition = this.incompleteTaskPanels.indexOf(taskPanel);
            addChildComponent(taskPanel, incompleteTaskPanelPosition);
        }

        final int notifyLabelPosition = getChildComponentPosition(this.notifyLabel);

        this.completedTaskPanels.sort(this.comparator);
        for (final TaskPanel taskPanel : this.completedTaskPanels) {
            removeChildComponent(taskPanel);

            final int completedTaskPanelPosition = notifyLabelPosition + this.completedTaskPanels.indexOf(taskPanel);
            addChildComponent(taskPanel, completedTaskPanelPosition);
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

    @Override
    public void handlerForResultOfLoadTaskAction() {
    }

    private void onMousePressedForTaskPanel(TaskPanel taskPanel) {
        final boolean requestSuccess = this.homeFrameController.requestLoadTaskContent(this,
                taskPanel.getTaskTypeToRequest(), taskPanel.getTaskId());

        if (!requestSuccess) {
            this.requestResultProcessor.requestWaitingHandler();
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

        if (this.sourceComponentTaskPanelMap.containsKey(eventSource)) {
            final TaskPanel taskPanel = this.sourceComponentTaskPanelMap.get(eventSource);
            onMousePressedForTaskPanel(taskPanel);
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
