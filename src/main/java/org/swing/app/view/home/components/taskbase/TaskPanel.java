package org.swing.app.view.home.components.taskbase;

import com.google.protobuf.DescriptorProtos;
import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.components.OptionPane;
import org.swing.app.view.components.ui.ActivationLabel;
import org.swing.app.view.components.ui.Checker;
import org.swing.app.view.components.ui.Popup;
import org.swing.app.view.components.ui.PopupItem;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.home.HomeWrapperComponent;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class TaskPanel extends HomeWrapperComponent implements ActionListener {

    protected static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT,
            ViewConstant.SMALL_H_GAP, ViewConstant.SMALL_V_GAP);

    protected ActivationLabel activationLabel;
    protected Checker statusChecker;
    protected TaskCenterPanel taskCenterPanel;
    protected Popup popup;
    protected PopupItem editPopupItem;
    protected PopupItem deletePopupItem;

    private final int preferHeight;

    protected final String taskId;

    public TaskPanel(HomeFrameController homeFrameController, int preferHeight, TaskPanelDto taskPanelDto) {
        super(homeFrameController);
        this.preferHeight = preferHeight;
        this.taskId = taskPanelDto.getId();
        setLayout(MAIN_LAYOUT);
        init(taskPanelDto);
    }

    public String getTaskId() {
        return taskId;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        final Object eventSource = e.getSource();

        if (eventSource == this.editPopupItem) {
            this.homeFrameController.updateTaskPanel(this);
            return;
        }
        if (eventSource == this.deletePopupItem) {
            final MessageLoader messageLoader = MessageLoader.getInstance();
            final byte result = OptionPane.showConfirmDialog(messageLoader.getMessage("confirm.dialog.question"),
                    messageLoader.getMessage("confirm.dialog.add.task.title"));

            if (result == OptionPane.YES_DIALOG_OPTION) {
                final boolean isDeleteSuccess = this.homeFrameController.deleteTaskById(this.taskId);
                if (isDeleteSuccess) {
                    deleteSuccessHandler();
                }
            }
            return;
        }
    }

    public void updateSuccessHandler(TaskPanelDto taskPanelDto) {
        update(taskPanelDto);

        final MessageLoader messageLoader = MessageLoader.getInstance();
        OptionPane.showMessageDialog(messageLoader.getMessage("update.success.dialog"));
    }

    private void deleteSuccessHandler() {
        dispose();
        this.parent.removeChildComponent(this);

        final MessageLoader messageLoader = MessageLoader.getInstance();
        OptionPane.showMessageDialog(messageLoader.getMessage("delete.success.dialog"));
    }
}
