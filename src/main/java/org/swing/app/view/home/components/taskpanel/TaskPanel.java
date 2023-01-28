package org.swing.app.view.home.components.taskpanel;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskDto;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ComponentSizeConstants;
import org.swing.app.view.common.IconUrlConstants;
import org.swing.app.view.common.LayoutGapConstants;
import org.swing.app.view.common.ReserveSizeConstants;
import org.swing.app.view.components.modal.OptionPane;
import org.swing.app.view.components.ui.label.ActivationLabel;
import org.swing.app.view.components.ui.label.Label;
import org.swing.app.view.components.ui.Popup;
import org.swing.app.view.components.ui.button.CheckBox;
import org.swing.app.view.components.ui.button.PopupItem;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.home.HomeWrapperComponent;
import org.swing.app.view.home.components.TaskCenterPanel;
import org.swing.app.view.home.components.listeners.deletetask.DeleteTaskActionListener;
import org.swing.app.view.home.components.listeners.updatetask.UpdateTaskActionListener;
import org.swing.app.view.home.components.listeners.updatetask.UpdateTaskMouseListener;
import org.swing.app.view.home.components.listeners.deletetask.DeleteTaskListenerSubject;
import org.swing.app.view.home.components.listeners.updatetask.UpdateTaskListenerSubject;
import org.swing.app.view.home.observer.taskpanel.modification.TaskPanelModificationEventObserver;
import org.swing.app.view.home.observer.taskpanel.modification.TaskPanelModificationEventSubject;
import org.swing.app.view.taskform.taskformmodal.factory.TaskFormModalFactory;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;
import java.util.EventObject;
import java.util.Optional;

public abstract class TaskPanel extends HomeWrapperComponent
        implements UpdateTaskListenerSubject, DeleteTaskListenerSubject {

    private static final byte HORIZONTAL_GAP = LayoutGapConstants.SMALL_H_GAP;
    private static final byte VERTICAL_GAP = LayoutGapConstants.SMALL_V_GAP;
    private static final LayoutManager MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT, HORIZONTAL_GAP, VERTICAL_GAP);

    private ActivationLabel activationLabel;
    private CheckBox statusChecker;
    protected TaskCenterPanel taskCenterPanel;
    private Label importantLabel;
    private Popup popup;
    private PopupItem editPopupItem;
    private PopupItem deletePopupItem;

    private final TaskPanelModificationEventSubject taskPanelModificationEventSubject;

    private final TaskPanelDto taskPanelDto;

    public TaskPanel(HomeFrameController homeFrameController, TaskPanelDto taskPanelDto) {
        super(homeFrameController);
        this.taskPanelDto = taskPanelDto;
        this.taskPanelModificationEventSubject = new TaskPanelModificationEventSubject(this);

        setLayout(MAIN_LAYOUT);
        init(taskPanelDto);
    }

    public TaskDto getTaskDto() {
        return this.taskPanelDto.getTaskDto();
    }

    public abstract int getPreferHeight();

    public void registerModificationEventObserver(TaskPanelModificationEventObserver observer) {
        this.taskPanelModificationEventSubject.registerObserver(observer);
    }

    public void removeModificationEventObserver(TaskPanelModificationEventObserver observer) {
        this.taskPanelModificationEventSubject.removeObserver(observer);
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
        this.importantLabel = UIComponentFactory.createLabel("");
        if (important) {
            this.importantLabel.setIcon(IconUrlConstants.IMPORTANT_ICON,
                    ComponentSizeConstants.MEDIUM_ICON_WIDTH, ComponentSizeConstants.MEDIUM_ICON_HEIGHT);
        } else {
            this.importantLabel.setIcon(IconUrlConstants.UNIMPORTANT_ICON,
                    ComponentSizeConstants.MEDIUM_ICON_WIDTH, ComponentSizeConstants.MEDIUM_ICON_HEIGHT);
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

    @Override
    protected void loadChildComponentsSize() {
        int availableWidth = getSize().width - ReserveSizeConstants.SMALL_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ReserveSizeConstants.SMALL_RESERVE_HEIGHT;

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

    public void updateTask(TaskDto taskDto) {
        this.taskPanelModificationEventSubject.notifyObserversForUpdateTaskEvent(taskDto);

        this.taskPanelDto.setTaskDto(taskDto);
        this.taskCenterPanel.update(this.taskPanelDto);

        if (isNeedStatusChecker()) {
            this.statusChecker.setSelected(taskDto.isCompleted());
        }
        if (isNeedImportantLabel()) {
            final String importantIconUrl = taskDto.isImportant()
                    ? IconUrlConstants.IMPORTANT_ICON : IconUrlConstants.UNIMPORTANT_ICON;
            this.importantLabel.setIcon(importantIconUrl,
                    ComponentSizeConstants.MEDIUM_ICON_WIDTH, ComponentSizeConstants.MEDIUM_ICON_HEIGHT);
        }
    }

    public void updateTaskCompletionRate(int completedChildTaskCount, int childTaskCount) {
        this.taskPanelDto.setCompletedChildTaskCount(completedChildTaskCount);
        this.taskPanelDto.setChildTaskCount(childTaskCount);

        this.taskCenterPanel.updateCompletionRate(completedChildTaskCount, childTaskCount);
    }

    protected abstract TaskFormModalFactory createTaskFormModalFactory();

    @Override
    public Optional<TaskDto> getTaskDtoToUpdate(EventObject eventObject) {
        final Object eventSource = eventObject.getSource();

        if (eventSource == this.editPopupItem.getSourceComponent()) {
            final TaskFormModalFactory taskFormModalFactory = createTaskFormModalFactory();
            return taskFormModalFactory.showUpdatingTaskFormModal(getWindowComponent(), getTaskDto());
        }
        if (isNeedStatusChecker() && eventSource == this.statusChecker.getSourceComponent()) {
            final TaskDto taskDtoToUpdate = getTaskDto();
            taskDtoToUpdate.setCompleted(!taskDtoToUpdate.isCompleted());
            taskDtoToUpdate.setSubmitDateTime(LocalDateTime.now());

            return Optional.of(taskDtoToUpdate);
        }
        if (isNeedImportantLabel() && eventSource == this.importantLabel.getSourceComponent()) {
            final TaskDto taskDtoToUpdate = getTaskDto();
            taskDtoToUpdate.setImportant(!taskDtoToUpdate.isImportant());

            return Optional.of(taskDtoToUpdate);
        }
        return Optional.empty();
    }

    @Override
    public void onUpdateTaskSuccess(EventObject eventObject, TaskDto updatedTaskDto) {
        updateTask(updatedTaskDto);

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
            this.statusChecker.setSelected(!this.statusChecker.isSelected());
        }
    }

    @Override
    public Optional<String> getTaskIdToDelete(EventObject eventObject) {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        final int confirmResult = OptionPane.showConfirmDialog(
                messageLoader.getMessage("delete.task.confirm.dialog.question"),
                messageLoader.getMessage("delete.task.confirm.dialog.title"));

        if (confirmResult == OptionPane.YES_DIALOG_OPTION) {
            final TaskDto taskDto = getTaskDto();
            return Optional.of(taskDto.getId());
        }

        return Optional.empty();
    }

    @Override
    public void onDeleteTaskSuccess(EventObject eventObject) {
        this.taskPanelModificationEventSubject.notifyObserversForDeleteEvent();

        final MessageLoader messageLoader = MessageLoader.getInstance();
        OptionPane.showMessageDialog(messageLoader.getMessage("delete.task.success.dialog"));
    }

    @Override
    public void onDeleteTaskFailure(EventObject eventObject) {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        OptionPane.showMessageDialog(messageLoader.getMessage("delete.task.failure.dialog"));
    }
}
