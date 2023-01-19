package org.swing.app.view.home.components.taskpanel;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskDto;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.modal.OptionPane;
import org.swing.app.view.components.ui.label.ActivationLabel;
import org.swing.app.view.components.ui.label.Label;
import org.swing.app.view.components.ui.Popup;
import org.swing.app.view.components.ui.button.CheckBox;
import org.swing.app.view.components.ui.button.PopupItem;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.home.HomeWrapperComponent;
import org.swing.app.view.home.components.TaskCenterPanel;
import org.swing.app.view.home.components.listeners.DeleteTaskActionListener;
import org.swing.app.view.home.components.listeners.UpdateTaskActionListener;
import org.swing.app.view.home.components.listeners.UpdateTaskMouseListener;
import org.swing.app.view.home.components.listeners.subjects.DeleteTaskListenerSubject;
import org.swing.app.view.home.components.listeners.subjects.UpdateTaskListenerSubject;
import org.swing.app.view.home.observer.TaskPanelDeleteEventSubject;
import org.swing.app.view.home.observer.TaskPanelModificationEventObserver;
import org.swing.app.view.home.observer.TaskPanelUpdateEventSubject;
import org.swing.app.view.taskform.taskformmodal.factory.TaskFormModalFactory;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;
import java.util.EventObject;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public abstract class TaskPanel extends HomeWrapperComponent implements TaskPanelUpdateEventSubject,
        TaskPanelDeleteEventSubject, UpdateTaskListenerSubject, DeleteTaskListenerSubject {

    private static final byte HORIZONTAL_GAP = ViewConstant.SMALL_H_GAP;
    private static final byte VERTICAL_GAP = ViewConstant.SMALL_V_GAP;
    private static final LayoutManager MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT, HORIZONTAL_GAP, VERTICAL_GAP);

    private ActivationLabel activationLabel;
    private CheckBox statusChecker;
    protected TaskCenterPanel taskCenterPanel;
    private Label importantLabel;
    private Popup popup;
    private PopupItem editPopupItem;
    private PopupItem deletePopupItem;

    private final Set<TaskPanelModificationEventObserver> taskPanelModificationEventObservers = new LinkedHashSet<>();

    private final int preferHeight;

    private TaskPanelDto taskPanelDto;

    private final TaskFormModalFactory taskFormModalFactory;

    public TaskPanel(HomeFrameController homeFrameController, TaskFormModalFactory taskFormModalFactory,
            int preferHeight, TaskPanelDto taskPanelDto) {

        super(homeFrameController);
        this.preferHeight = preferHeight;
        this.taskPanelDto = taskPanelDto;
        this.taskFormModalFactory = taskFormModalFactory;

        setLayout(MAIN_LAYOUT);
        init(taskPanelDto);
    }

    public TaskPanelDto getTaskPanelDto() {
        return taskPanelDto;
    }

    public String getTaskId() {
        final TaskDto taskDto = this.taskPanelDto.getTaskDto();
        return taskDto.getId();
    }

    public boolean isCompleted() {
        final TaskDto taskDto = this.taskPanelDto.getTaskDto();
        return taskDto.isCompleted();
    }

    public int getPreferHeight() {
        return preferHeight;
    }

    protected abstract boolean isNeedStatusChecker();

    protected abstract boolean isNeedImportantLabel();

    private void initActivationLabel() {
        this.activationLabel = UIComponentFactory.createActivationLabel();
    }

    private void initStatusChecker(boolean checked) {
        this.statusChecker = UIComponentFactory.createCheckBox("");
        this.statusChecker.setSelected(checked);

        final ActionListener updateTaskPanelActionListener = new UpdateTaskActionListener(
                this.homeFrameController, this);
        this.statusChecker.addActionListener(updateTaskPanelActionListener);
    }

    private void initTaskCenterPanel(TaskPanelDto taskPanelDto) {
        this.taskCenterPanel = new TaskCenterPanel(this.homeFrameController, taskPanelDto);
    }

    private void initImportantLabel(boolean important) {
        if (important) {
            this.importantLabel = UIComponentFactory.createLabel(ViewConstant.ICON_LOCATION_IMPORTANT);
        } else {
            this.importantLabel = UIComponentFactory.createLabel(ViewConstant.ICON_LOCATION_UNIMPORTANT);
        }

        final MouseListener mouseListener = new UpdateTaskMouseListener(
                this.homeFrameController, this);
        this.importantLabel.addMouseListener(mouseListener);
    }

    private void initEditPopupItem() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.editPopupItem = UIComponentFactory.createPopupItem(
                messageLoader.getMessage("edit.popup.item.title"));

        final ActionListener actionListener = new UpdateTaskActionListener(
                this.homeFrameController, this);
        this.editPopupItem.addActionListener(actionListener);
    }

    private void initDeletePopupItem() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.deletePopupItem = UIComponentFactory.createPopupItem(
                messageLoader.getMessage("remove.popup.item.title"));

        final ActionListener actionListener = new DeleteTaskActionListener(
                this.homeFrameController, this);
        this.deletePopupItem.addActionListener(actionListener);
    }

    private void initPopup() {
        this.popup = UIComponentFactory.createPopup();

        initEditPopupItem();
        this.popup.addPopupItem(this.editPopupItem);

        initDeletePopupItem();
        this.popup.addPopupItem(this.deletePopupItem);
    }

    private void init(TaskPanelDto taskPanelDto) {
        final TaskDto taskDto = taskPanelDto.getTaskDto();

        initActivationLabel();
        addChildComponent(this.activationLabel);

        if (isNeedStatusChecker()) {
            initStatusChecker(taskDto.isCompleted());
            addChildComponent(this.statusChecker);
        }

        initTaskCenterPanel(taskPanelDto);
        addChildComponent(this.taskCenterPanel);

        if (isNeedImportantLabel()) {
            initImportantLabel(taskDto.isImportant());
            addChildComponent(this.importantLabel);
        }

        initPopup();
        setPopup(this.popup);
    }

    private void updateStatusChecker(boolean checked) {
        this.statusChecker.setSelected(checked);
    }

    private void updateTaskCenterPanel(TaskPanelDto taskPanelDto) {
        this.taskCenterPanel.update(taskPanelDto);
    }

    private void updateImportantLabel(boolean important) {
        if (important) {
            this.importantLabel.setIcon(ViewConstant.ICON_LOCATION_IMPORTANT);
        } else {
            this.importantLabel.setIcon(ViewConstant.ICON_LOCATION_UNIMPORTANT);
        }
    }

    public void update(TaskPanelDto taskPanelDto) {
        this.taskPanelDto = taskPanelDto;
        updateTaskCenterPanel(this.taskPanelDto);

        final TaskDto taskDto = taskPanelDto.getTaskDto();
        if (isNeedStatusChecker()) {
            updateStatusChecker(taskDto.isCompleted());
        }
        if (isNeedImportantLabel()) {
            updateImportantLabel(taskDto.isImportant());
        }
    }

    public void updateTask(TaskDto taskDto) {
        this.taskPanelDto.setTaskDto(taskDto);
        updateTaskCenterPanel(this.taskPanelDto);

        if (isNeedStatusChecker()) {
            updateStatusChecker(taskDto.isCompleted());
        }
        if (isNeedImportantLabel()) {
            updateImportantLabel(taskDto.isImportant());
        }
    }

    public void updateTaskCompletionRate(int completedChildTaskCount, int childTaskCount) {
        this.taskPanelDto.setCompletedChildTaskCount(completedChildTaskCount);
        this.taskPanelDto.setChildTaskCount(childTaskCount);

        this.taskCenterPanel.updateCompletionRate(completedChildTaskCount, childTaskCount);
    }

    @Override
    protected void loadChildComponentsSize() {
        int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;

        final int maxChildComponentHeight = availableHeight - VERTICAL_GAP;

        final byte activationLabelWidth = 5;
        this.childComponentSizeMap.put(this.activationLabel,
                new Dimension(activationLabelWidth, maxChildComponentHeight));
        availableWidth -= HORIZONTAL_GAP + activationLabelWidth;

        if (isNeedStatusChecker()) {
            final int statusCheckerWidth = 30;
            this.childComponentSizeMap.put(this.statusChecker,
                    new Dimension(statusCheckerWidth, maxChildComponentHeight));
            availableWidth -= HORIZONTAL_GAP + activationLabelWidth;
        }
        if (isNeedImportantLabel()) {
            final int importantLabelWidth = 30;
            this.childComponentSizeMap.put(this.importantLabel,
                    new Dimension(importantLabelWidth, maxChildComponentHeight));
            availableWidth -= HORIZONTAL_GAP + importantLabelWidth;
        }

        final int taskCenterPanelWidth = availableWidth - HORIZONTAL_GAP;
        this.childComponentSizeMap.put(this.taskCenterPanel,
                new Dimension(taskCenterPanelWidth, maxChildComponentHeight));
    }

    @Override
    protected void setNotResizableChildComponents() {
        this.activationLabel.setResizable(false);
        if (isNeedStatusChecker()) {
            this.statusChecker.setResizable(false);
        }
        if (isNeedImportantLabel()) {
            this.importantLabel.setResizable(false);
        }
    }

    public void activate() {
        this.activationLabel.activate();
    }

    public void deactivate() {
        this.activationLabel.deactivate();
    }

    @Override
    public void cancelAllEventListeners() {
        super.cancelAllEventListeners();
        this.popup.cancelAllEventListeners();
    }

    @Override
    public void registerObserver(TaskPanelModificationEventObserver observer) {
        this.taskPanelModificationEventObservers.add(observer);
    }

    @Override
    public void removeObserver(TaskPanelModificationEventObserver observer) {
        this.taskPanelModificationEventObservers.remove(observer);
    }

    @Override
    public void notifyObserversToUpdate(TaskPanelDto updatedTaskPanelDto) {
        for (final TaskPanelModificationEventObserver observer : this.taskPanelModificationEventObservers) {
            observer.handleUpdateTaskPanel(this, updatedTaskPanelDto);
        }
    }

    @Override
    public void notifyObserversToUpdateTask(TaskDto updatedTaskDto) {
        for (final TaskPanelModificationEventObserver observer : this.taskPanelModificationEventObservers) {
            observer.handleUpdateTaskInTaskPanel(this, updatedTaskDto);
        }
    }

    @Override
    public void notifyObserversToUpdateTaskCompletionRate(int completedChildTaskCount, int childTaskCount) {
        for (final TaskPanelModificationEventObserver observer : this.taskPanelModificationEventObservers) {
            observer.handleUpdateTaskCompletionRateInTaskPanel(this, completedChildTaskCount, childTaskCount);
        }
    }

    @Override
    public void notifyObserversToDelete() {
        for (final TaskPanelModificationEventObserver observer : this.taskPanelModificationEventObservers) {
            observer.handleDeleteTaskPanel(this);
        }
    }

    @Override
    public Optional<TaskDto> getTaskDtoToUpdate(EventObject eventObject) {
        final Object eventSource = eventObject.getSource();

        if (eventSource == this.editPopupItem.getSourceComponent()) {
            final TaskDto currentTaskDto = this.taskPanelDto.getTaskDto();
            return this.taskFormModalFactory.showUpdatingTaskFormModal(getRootFrame(), currentTaskDto);
        }
        if (isNeedStatusChecker() && eventSource == this.statusChecker.getSourceComponent()) {
            final TaskDto taskDtoToUpdate = this.taskPanelDto.getTaskDto();
            taskDtoToUpdate.setCompleted(!isCompleted());
            taskDtoToUpdate.setSubmitDateTime(LocalDateTime.now());

            return Optional.of(taskDtoToUpdate);
        }
        if (isNeedImportantLabel() && eventSource == this.importantLabel.getSourceComponent()) {
            final TaskDto taskDtoToUpdate = this.taskPanelDto.getTaskDto();
            taskDtoToUpdate.setImportant(!taskDtoToUpdate.isImportant());

            return Optional.of(taskDtoToUpdate);
        }
        return Optional.empty();
    }

    @Override
    public void onUpdateTaskSuccess(EventObject eventObject, TaskDto updatedTaskDto) {
        notifyObserversToUpdateTask(updatedTaskDto);

        final Object eventSource = eventObject.getSource();
        final MessageLoader messageLoader = MessageLoader.getInstance();

        if (eventSource == this.editPopupItem.getSourceComponent()) {
            OptionPane.showMessageDialog(messageLoader.getMessage("update.task.success.dialog"));
        }
    }

    @Override
    public void onUpdateTaskFailure(EventObject eventObject) {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        OptionPane.showMessageDialog(messageLoader.getMessage("update.task.failure.dialog"));

        final Object eventSource = eventObject.getSource();
        if (isNeedStatusChecker() && eventSource == this.statusChecker.getSourceComponent()) {
            this.statusChecker.setSelected(isCompleted());
        }
    }

    @Override
    public Optional<String> getTaskIdToDelete(EventObject eventObject) {
        return Optional.of(getTaskId());
    }

    @Override
    public void onDeleteTaskSuccess(EventObject eventObject) {
        notifyObserversToDelete();

        final MessageLoader messageLoader = MessageLoader.getInstance();
        OptionPane.showMessageDialog(messageLoader.getMessage("delete.task.success.dialog"));
    }

    @Override
    public void onDeleteTaskFailure(EventObject eventObject) {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        OptionPane.showMessageDialog(messageLoader.getMessage("delete.task.failure.dialog"));
    }
}
