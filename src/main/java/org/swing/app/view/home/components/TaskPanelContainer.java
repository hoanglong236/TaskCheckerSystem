package org.swing.app.view.home.components;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ComponentSizeConstants;
import org.swing.app.view.common.LayoutGapConstants;
import org.swing.app.view.common.ReserveSizeConstants;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.components.ui.label.Label;
import org.swing.app.view.home.HomeWrapperComponent;
import org.swing.app.view.home.comparetor.TaskPanelComparator;
import org.swing.app.view.home.comparetor.TaskPanelCreateDateComparator;
import org.swing.app.view.home.comparetor.TaskPanelUpdateDateComparator;
import org.swing.app.view.home.components.taskpanel.TaskPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

class TaskPanelContainer extends HomeWrapperComponent {

    public static final byte SORT_BY_CREATE_DATE_ASC = 0;
    public static final byte SORT_BY_UPDATE_DATE_ASC = 2;

    private static final byte HORIZONTAL_GAP = LayoutGapConstants.SMALL_H_GAP;
    private static final byte VERTICAL_GAP = LayoutGapConstants.SMALL_V_GAP;
    private static final LayoutManager MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT, HORIZONTAL_GAP, VERTICAL_GAP);

    private static final byte NOTIFY_LABEL_WIDTH = ComponentSizeConstants.NOTIFY_LABEL_WIDTH;
    private static final byte NOTIFY_LABEL_HEIGHT = ComponentSizeConstants.NOTIFY_LABEL_HEIGHT;

    private Label notifyLabel;
    private final List<TaskPanel> incompleteTaskPanels = new ArrayList<>();
    private final List<TaskPanel> completedTaskPanels = new ArrayList<>();

    private int preferHeight = ReserveSizeConstants.SMALL_RESERVE_HEIGHT;

    private Comparator<TaskPanel> comparator = new TaskPanelCreateDateComparator();

    public TaskPanelContainer(HomeFrameController homeFrameController) {
        super(homeFrameController);
        setLayout(MAIN_LAYOUT);
        init();
    }

    private void initNotifyLabel() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.notifyLabel = UIComponentFactory.createLabel(messageLoader.getMessage("label.notify.text"));
        this.notifyLabel.setVisible(false);
    }

    private void init() {
        initNotifyLabel();
        addChildComponent(this.notifyLabel);
    }

    private int getPreferChildComponentWidth() {
        final int availableWidth = getSize().width - ReserveSizeConstants.SMALL_RESERVE_WIDTH;
        return availableWidth - HORIZONTAL_GAP;
    }

    private void loadTaskPanelsSize(Iterator<TaskPanel> taskPanelIterator) {
        final int taskPanelWidth = getPreferChildComponentWidth();

        while (taskPanelIterator.hasNext()) {
            final TaskPanel taskPanel = taskPanelIterator.next();
            this.childComponentSizeMap.put(taskPanel, new Dimension(taskPanelWidth, taskPanel.getPreferHeight()));
        }
    }

    public void resizeWidth(int width) {
        resize(new Dimension(width, this.preferHeight));
    }

    @Override
    protected void loadChildComponentsSize() {
        loadTaskPanelsSize(this.incompleteTaskPanels.iterator());
        loadTaskPanelsSize(this.completedTaskPanels.iterator());

        this.childComponentSizeMap.put(this.notifyLabel, new Dimension(NOTIFY_LABEL_WIDTH, NOTIFY_LABEL_HEIGHT));
    }

    public void resizeTaskPanel(TaskPanel taskPanel) {
        final int taskPanelWidth = getPreferChildComponentWidth();
        final int taskPanelHeight = taskPanel.getPreferHeight();

        taskPanel.resize(new Dimension(taskPanelWidth, taskPanelHeight));
    }

    private void resizeHeightWithoutResizeChildComponent(int height) {
        this.sourceComponent.setPreferredSize(new Dimension(getSize().width, height));
    }

    private void displayNotifyLabelIfNecessary() {
        if (this.notifyLabel.isVisible()) {
            return;
        }
        if (this.completedTaskPanels.size() > 0) {
            this.notifyLabel.setVisible(true);
            this.preferHeight += VERTICAL_GAP + NOTIFY_LABEL_HEIGHT;
            resizeHeightWithoutResizeChildComponent(this.preferHeight);
        }
    }

    private int getTaskPanelPositionToAdding(TaskPanel taskPanel) {
        final TaskDto taskDto = taskPanel.getTaskDto();

        if (!taskDto.isCompleted()) {
            this.incompleteTaskPanels.add(taskPanel);
            this.incompleteTaskPanels.sort(this.comparator);

            return this.incompleteTaskPanels.indexOf(taskPanel);
        } else {
            this.completedTaskPanels.add(taskPanel);
            this.completedTaskPanels.sort(this.comparator);

            final int notifyLabelPosition = getChildComponentPosition(this.notifyLabel);
            return notifyLabelPosition + this.completedTaskPanels.indexOf(taskPanel);
        }
    }

    public void addTaskPanel(TaskPanel taskPanel) {
        final int positionToAddInUI = getTaskPanelPositionToAdding(taskPanel);
        addChildComponent(taskPanel, positionToAddInUI);

        this.preferHeight += VERTICAL_GAP + taskPanel.getSize().height;
        resizeHeightWithoutResizeChildComponent(this.preferHeight);

        displayNotifyLabelIfNecessary();
        refreshUI();
    }

    private void hiddenNotifyLabelIfNecessary() {
        if (!this.notifyLabel.isVisible()) {
            return;
        }
        if (this.completedTaskPanels.size() == 0) {
            this.notifyLabel.setVisible(false);
            this.preferHeight -= VERTICAL_GAP + NOTIFY_LABEL_HEIGHT;
            resizeHeightWithoutResizeChildComponent(this.preferHeight);
        }
    }

    public void removeTaskPanel(TaskPanel taskPanel) {
        final TaskDto taskDto = taskPanel.getTaskDto();

        if (taskDto.isCompleted()) {
            this.completedTaskPanels.remove(taskPanel);
        } else {
            this.incompleteTaskPanels.remove(taskPanel);
        }

        removeChildComponent(taskPanel);

        this.preferHeight -= VERTICAL_GAP + taskPanel.getSize().height;
        resizeHeightWithoutResizeChildComponent(this.preferHeight);

        hiddenNotifyLabelIfNecessary();
        refreshUI();
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

    private void sortTaskPanelByUpdateDate() {
        reSortTaskPanels(new TaskPanelUpdateDateComparator());
    }

    public void sortTaskPanel(byte typeOfSortBy) {
        switch (typeOfSortBy) {
            case SORT_BY_CREATE_DATE_ASC:
                sortTaskPanelByCreateDate();
                break;
            case SORT_BY_UPDATE_DATE_ASC:
                sortTaskPanelByUpdateDate();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }
}
