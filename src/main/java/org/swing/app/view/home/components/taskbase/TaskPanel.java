package org.swing.app.view.home.components.taskbase;

import org.swing.app.controller.ControllerResponse;
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
import org.swing.app.view.home.observer.TaskPanelModificationEventObserver;
import org.swing.app.view.home.observer.TaskPanelModificationEventSubject;
import org.swing.app.view.taskform.factory.TaskFormModalFactory;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public abstract class TaskPanel extends HomeWrapperComponent
        implements ActionListener, MouseListener, TaskPanelModificationEventSubject {

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

    // TODO: handle this
    public String getTaskId() {
        final TaskDto taskDto = this.taskPanelDto.getTaskDto();
        return taskDto.getId();
    }

    // TODO: handle this
    public LocalDateTime getCreateDateTime() {
        return this.taskPanelDto.getCreatedAt();
    }

    // TODO: handle this
    public LocalDateTime getModifyDateTime() {
        return this.taskPanelDto.getUpdatedAt();
    }

    // TODO: handle this
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
        this.statusChecker.addActionListener(this);
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
        this.importantLabel.addMouseListener(this);
    }

    private void initEditPopupItem() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.editPopupItem = UIComponentFactory.createPopupItem(
                messageLoader.getMessage("edit.popup.item.title"));
        this.editPopupItem.addActionListener(this);
    }

    private void initDeletePopupItem() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.deletePopupItem = UIComponentFactory.createPopupItem(
                messageLoader.getMessage("remove.popup.item.title"));
        this.deletePopupItem.addActionListener(this);
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
        final TaskDto taskDto = taskPanelDto.getTaskDto();

        if (isNeedStatusChecker()) {
            updateStatusChecker(taskDto.isCompleted());
        }

        updateTaskCenterPanel(taskPanelDto);

        if (isNeedImportantLabel()) {
            updateImportantLabel(taskDto.isImportant());
        }
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

    private void handleForUpdateTaskPanelResponse(ControllerResponse response, boolean isShowSuccessDialog) {
        final MessageLoader messageLoader = MessageLoader.getInstance();

        if (response.getResponseType() == ControllerResponse.RESPONSE_TYPE_SUCCESS) {
            final Optional<Object> updatedTaskPanelDtoOptional = response.getData(
                    messageLoader.getMessage("inserted.task.panel.dto"));

            if (updatedTaskPanelDtoOptional.isPresent()) {
                update((TaskPanelDto) updatedTaskPanelDtoOptional.get());
                notifyObserversWhenUpdateTaskPanel();

                if (isShowSuccessDialog) {
                    OptionPane.showMessageDialog(messageLoader.getMessage("update.task.success.dialog"));
                }
                return;
            }

            OptionPane.showMessageDialog(messageLoader.getMessage("insert.task.failure.dialog"));
            return;
        }
        if (response.getResponseType() == ControllerResponse.RESPONSE_TYPE_ERROR) {
            OptionPane.showMessageDialog(messageLoader.getMessage("insert.task.failure.dialog"));
        }
    }

    private void onActionPerformedForEditPopupItem() {
        final TaskDto currentTaskDto = this.taskPanelDto.getTaskDto();
        final Optional<TaskDto> newTaskDtoOptional = this.taskFormModalFactory.showUpdatingTaskFormModal(
                getRootFrame(), currentTaskDto);

        if (!newTaskDtoOptional.isPresent()) {
            return;
        }

        final TaskDto taskDtoToUpdate = newTaskDtoOptional.get();
        final ControllerResponse response = this.homeFrameController.requestUpdateTaskPanel(taskDtoToUpdate);
        handleForUpdateTaskPanelResponse(response, true);
    }

    private void handleForDeleteTaskPanelResponse(ControllerResponse response) {
        final MessageLoader messageLoader = MessageLoader.getInstance();

        if (response.getResponseType() == ControllerResponse.RESPONSE_TYPE_SUCCESS) {
            cancelAllEventListeners();
            notifyObserversWhenDeleteTaskPanel();

            OptionPane.showMessageDialog(messageLoader.getMessage("delete.task.success.dialog"));
            return;
        }
        if (response.getResponseType() == ControllerResponse.RESPONSE_TYPE_ERROR) {
            OptionPane.showMessageDialog(messageLoader.getMessage("delete.task.failure.dialog"));
        }
    }

    private void onActionPerformedForDeletePopupItem() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        final byte confirmResult = OptionPane.showConfirmDialog(
                messageLoader.getMessage("confirm.dialog.question"),
                messageLoader.getMessage("confirm.dialog.delete.task.title"));

        if (confirmResult != OptionPane.YES_DIALOG_OPTION) {
            return;
        }

        final ControllerResponse response = this.homeFrameController.requestDeleteTaskPanel(getTaskId());
        handleForDeleteTaskPanelResponse(response);
    }

    private void onActionPerformedForStatusChecker() {
        final boolean isCompleted = this.statusChecker.isSelected();

        final TaskDto taskDtoToUpdate = this.taskPanelDto.getTaskDto();
        taskDtoToUpdate.setCompleted(isCompleted);
        taskDtoToUpdate.setSubmitDateTime(LocalDateTime.now());

        final ControllerResponse response = this.homeFrameController.requestUpdateTaskPanel(taskDtoToUpdate);
        handleForUpdateTaskPanelResponse(response, false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final Object eventSource = e.getSource();

        if (eventSource == this.editPopupItem.getSourceComponent()) {
            onActionPerformedForEditPopupItem();
            return;
        }
        if (eventSource == this.deletePopupItem.getSourceComponent()) {
            onActionPerformedForDeletePopupItem();
            return;
        }
        if (isNeedStatusChecker()) {
            if (eventSource == this.statusChecker.getSourceComponent()) {
                onActionPerformedForStatusChecker();
                return;
            }
        }
        throw new IllegalArgumentException();
    }

    private void onMousePressedForImportantLabel() {
        final TaskDto taskDtoToUpdate = this.taskPanelDto.getTaskDto();
        taskDtoToUpdate.setImportant(!taskDtoToUpdate.isImportant());

        final ControllerResponse response = this.homeFrameController.requestUpdateTaskPanel(taskDtoToUpdate);
        handleForUpdateTaskPanelResponse(response, false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        final Object eventSource = e.getSource();

        if (isNeedImportantLabel()) {
            if (eventSource == this.importantLabel.getSourceComponent()) {
                onMousePressedForImportantLabel();
                return;
            }
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

    @Override
    public void registerObserver(TaskPanelModificationEventObserver observer) {
        this.taskPanelModificationEventObservers.add(observer);
    }

    @Override
    public void removeObserver(TaskPanelModificationEventObserver observer) {
        this.taskPanelModificationEventObservers.remove(observer);
    }

    @Override
    public void notifyObserversWhenInsertTaskPanel() {
    }

    @Override
    public void notifyObserversWhenUpdateTaskPanel() {
        for (final TaskPanelModificationEventObserver observer : this.taskPanelModificationEventObservers) {
            observer.handleUpdateTaskPanel(this);
        }
    }

    @Override
    public void notifyObserversWhenDeleteTaskPanel() {
        for (final TaskPanelModificationEventObserver observer : this.taskPanelModificationEventObservers) {
            observer.handleDeleteTaskPanel(this);
        }
    }
}
