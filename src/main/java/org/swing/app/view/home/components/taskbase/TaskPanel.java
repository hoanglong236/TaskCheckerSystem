package org.swing.app.view.home.components.taskbase;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.OptionPane;
import org.swing.app.view.components.ui.ActivationLabel;
import org.swing.app.view.components.ui.Checker;
import org.swing.app.view.components.ui.Popup;
import org.swing.app.view.components.ui.PopupItem;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.home.DeletableTaskComponent;
import org.swing.app.view.home.HomeWrapperComponent;
import org.swing.app.view.home.UpdatableTaskComponent;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public abstract class TaskPanel extends HomeWrapperComponent
        implements ActionListener, UpdatableTaskComponent, DeletableTaskComponent {

    protected static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT,
            ViewConstant.SMALL_H_GAP, ViewConstant.SMALL_V_GAP);

    protected ActivationLabel activationLabel;
    protected Checker statusChecker;
    protected TaskCenterPanel taskCenterPanel;
    protected Popup popup;
    protected PopupItem editPopupItem;
    protected PopupItem deletePopupItem;

    private final int preferHeight;

    protected TaskPanelDto taskPanelDto;

    public TaskPanel(HomeFrameController homeFrameController, int preferHeight, TaskPanelDto taskPanelDto) {
        super(homeFrameController);
        this.preferHeight = preferHeight;
        this.taskPanelDto = taskPanelDto;
        setLayout(MAIN_LAYOUT);
        init(taskPanelDto);
    }

    @Override
    public String getTaskId() {
        return this.taskPanelDto.getId();
    }

    public LocalDateTime getStartDateTime() {
        return this.taskPanelDto.getStartDatetime();
    }

    public LocalDateTime getFinishDateTime() {
        return this.taskPanelDto.getFinishDatetime();
    }

    public boolean isImportant() {
        return this.taskPanelDto.isImportant();
    }

    public int getPreferHeight() {
        return preferHeight;
    }

    protected void initActivationLabel() {
        this.activationLabel = UIComponentFactory.createActivationLabel();
    }

    protected void initStatusChecker(boolean checked) {
        this.statusChecker = UIComponentFactory.createChecker(checked);
    }

    protected abstract void initTaskCenterPanel(TaskPanelDto taskPanelDto);

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

    protected void initPopup() {
        this.popup = UIComponentFactory.createPopup();

        initEditPopupItem();
        this.popup.addPopupItem(this.editPopupItem);

        initDeletePopupItem();
        this.popup.addPopupItem(this.deletePopupItem);
    }

    protected abstract void init(TaskPanelDto taskPanelDto);

    protected void updateStatusChecker(boolean checked) {
        this.statusChecker.setChecked(checked);
    }

    protected void updateTaskCenterPanel(TaskPanelDto taskPanelDto) {
        this.taskCenterPanel.update(taskPanelDto);
    }

    public abstract void update(TaskPanelDto taskPanelDto);

    public abstract boolean requestLoadContent();

    protected abstract boolean requestUpdate();

    private void onActionPerformedForEditPopupItem() {
        boolean requestSuccess = requestUpdate();

        if (!requestSuccess) {
            requestFailureHandler();
        }
    }

    private void onActionPerformedForDeletePopupItem() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        final boolean hasRequestingComponent = this.homeFrameController.hasRequestingComponent();

        if (!hasRequestingComponent) {
            requestFailureHandler();
            return;
        }

        final byte result = OptionPane.showConfirmDialog(messageLoader.getMessage("confirm.dialog.question"),
                messageLoader.getMessage("confirm.dialog.add.task.title"));
        if (result == OptionPane.YES_DIALOG_OPTION) {
            final boolean requestSuccess = this.homeFrameController.requestDeleteTaskPanel(this);
            if (!requestSuccess) {
                requestFailureHandler();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final Object eventSource = e.getSource();

        if (eventSource == this.editPopupItem) {
            onActionPerformedForEditPopupItem();
            return;
        }
        if (eventSource == this.deletePopupItem) {
            onActionPerformedForDeletePopupItem();
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void handlerForResultOfUpdateTaskAction(boolean isSuccess, TaskPanelDto taskPanelDto) {
        final MessageLoader messageLoader = MessageLoader.getInstance();

        if (isSuccess) {
            update(taskPanelDto);
            OptionPane.showMessageDialog(messageLoader.getMessage("update.success.dialog"));
        } else {
            OptionPane.showMessageDialog(messageLoader.getMessage("update.failure.dialog"));
        }
    }

    @Override
    public void handlerForResultOfDeleteTaskAction(boolean isSuccess) {
        final MessageLoader messageLoader = MessageLoader.getInstance();

        if (isSuccess) {
            dispose();
            this.parent.removeChildComponent(this);
            OptionPane.showMessageDialog(messageLoader.getMessage("delete.success.dialog"));
        } else {
            OptionPane.showMessageDialog(messageLoader.getMessage("delete.failure.dialog"));
        }
    }

    public void activate() {
        this.activationLabel.activate();
    }

    public void deactivate() {
        this.activationLabel.deactivate();
    }

    private void disposePopup() {
        this.popup.dispose();
    }

    @Override
    public void dispose() {
        super.dispose();
        disposePopup();
    }
}
