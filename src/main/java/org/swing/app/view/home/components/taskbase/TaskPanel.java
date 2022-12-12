package org.swing.app.view.home.components.taskbase;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.ui.ActivationLabel;
import org.swing.app.view.components.ui.Checker;
import org.swing.app.view.components.ui.Label;
import org.swing.app.view.components.ui.Popup;
import org.swing.app.view.components.ui.PopupItem;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.home.HomeWrapperComponent;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

public abstract class TaskPanel extends HomeWrapperComponent implements ActionListener {

    protected static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT,
            ViewConstant.SMALL_H_GAP, ViewConstant.SMALL_V_GAP);

    protected ActivationLabel activationLabel;
    protected Checker statusChecker;
    protected TaskCenterPanel taskCenterPanel;
    protected Label removeLabel;
    protected Popup popup;
    protected PopupItem editPopupItem;
    protected PopupItem removePopupItem;

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

    protected void initRemoveLabel() {
        this.removeLabel = UIComponentFactory.createLabel(ViewConstant.ICON_LOCATION_REMOVE);
    }

    private void initEditPopupItem() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.editPopupItem = UIComponentFactory.createPopupItem(
                messageLoader.getMessage("edit.popup.item.title"));
        this.editPopupItem.addActionListener(this);
    }

    private void initRemovePopupItem() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.removePopupItem = UIComponentFactory.createPopupItem(
                messageLoader.getMessage("remove.popup.item.title"));
        this.removePopupItem.addActionListener(this);
    }

    protected void initPopup() {
        this.popup = UIComponentFactory.createPopup();

        initEditPopupItem();
        this.popup.addPopupItem(this.editPopupItem);

        initRemovePopupItem();
        this.popup.addPopupItem(this.removePopupItem);
    }

    protected abstract void init(TaskPanelDto taskPanelDto);

    protected void updateStatusChecker(boolean checked) {
        this.statusChecker.setChecked(checked);
    }

    protected void updateTaskCenterPanel(TaskPanelDto taskPanelDto) {
        this.taskCenterPanel.update(taskPanelDto);
    }

    public abstract void update(TaskPanelDto taskPanelDto);
}
