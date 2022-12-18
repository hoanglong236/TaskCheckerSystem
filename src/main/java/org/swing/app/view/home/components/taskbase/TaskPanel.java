package org.swing.app.view.home.components.taskbase;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.OptionPane;
import org.swing.app.view.components.ui.ActivationLabel;
import org.swing.app.view.components.ui.Checker;
import org.swing.app.view.components.ui.Label;
import org.swing.app.view.components.ui.Popup;
import org.swing.app.view.components.ui.PopupItem;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.components.request.DeletableTaskComponent;
import org.swing.app.view.home.HomeWrapperComponent;
import org.swing.app.view.components.request.UpdatableTaskComponent;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public abstract class TaskPanel extends HomeWrapperComponent
        implements ActionListener, UpdatableTaskComponent, DeletableTaskComponent {

    private static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT,
            ViewConstant.SMALL_H_GAP, ViewConstant.SMALL_V_GAP);

    private ActivationLabel activationLabel;
    private Checker statusChecker;
    protected TaskCenterPanel taskCenterPanel;
    private Label importantLabel;
    private Popup popup;
    private PopupItem editPopupItem;
    private PopupItem deletePopupItem;

    private final int preferHeight;

    private TaskPanelDto taskPanelDto;

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

    public LocalDateTime getFinishDateTime() {
        return this.taskPanelDto.getFinishDatetime();
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
        this.statusChecker = UIComponentFactory.createChecker(checked);
    }

    protected abstract void initTaskCenterPanel(TaskPanelDto taskPanelDto);

    private void initImportantLabel(boolean important) {
        if (important) {
            this.importantLabel = UIComponentFactory.createLabel(ViewConstant.ICON_LOCATION_IMPORTANT);
        } else {
            this.importantLabel = UIComponentFactory.createLabel(ViewConstant.ICON_LOCATION_UNIMPORTANT);
        }
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
        initActivationLabel();
        addChildComponent(this.activationLabel);

        if (isNeedStatusChecker()) {
            initStatusChecker(taskPanelDto.isCompleted());
            addChildComponent(this.statusChecker);
        }

        initTaskCenterPanel(taskPanelDto);
        addChildComponent(this.taskCenterPanel);

        if (isNeedImportantLabel()) {
            initImportantLabel(taskPanelDto.isImportant());
            addChildComponent(this.importantLabel);
        }

        initPopup();
        setPopup(this.popup);
    }

    private void updateStatusChecker(boolean checked) {
        this.statusChecker.setChecked(checked);
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
        if (isNeedStatusChecker()) {
            updateStatusChecker(taskPanelDto.isCompleted());
        }

        updateTaskCenterPanel(taskPanelDto);

        if (isNeedImportantLabel()) {
            updateImportantLabel(taskPanelDto.isImportant());
        }
    }

    @Override
    protected void loadChildComponentsSize() {
        this.childComponentSizeMap.clear();

        int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;

        final int hGap = MAIN_LAYOUT.getHgap();
        final int maxChildComponentHeight = availableHeight - MAIN_LAYOUT.getVgap();

        final byte activationLabelWidth = 5;
        this.childComponentSizeMap.put(this.activationLabel,
                new Dimension(activationLabelWidth, maxChildComponentHeight));
        availableWidth -= hGap + activationLabelWidth;

        if (this.statusChecker != null) {
            final int statusCheckerWidth = 30;
            this.childComponentSizeMap.put(this.statusChecker,
                    new Dimension(statusCheckerWidth, maxChildComponentHeight));
            availableWidth -= hGap + activationLabelWidth;
        }

        if (this.importantLabel != null) {
            final int importantLabelWidth = 30;
            this.childComponentSizeMap.put(this.importantLabel,
                    new Dimension(importantLabelWidth, maxChildComponentHeight));
            availableWidth -= hGap + importantLabelWidth;
        }

        final int taskCenterPanelWidth = availableWidth - hGap;
        this.childComponentSizeMap.put(this.taskCenterPanel,
                new Dimension(taskCenterPanelWidth, maxChildComponentHeight));
    }

    @Override
    protected void setNotResizableChildComponents() {
        this.activationLabel.setResizable(false);

        if (this.statusChecker != null) {
            this.statusChecker.setResizable(false);
        }

        if (this.importantLabel != null) {
            this.importantLabel.setResizable(false);
        }
    }

    public abstract boolean requestLoadContent();

    protected abstract boolean requestUpdate();

    private void onActionPerformedForEditPopupItem() {
        boolean requestSuccess = requestUpdate();

        if (!requestSuccess) {
            requestFailureHandler();
        }
    }

    private void onActionPerformedForDeletePopupItem() {
        if (this.homeFrameController.hasRequestingComponent()) {
            requestFailureHandler();
            return;
        }

        final MessageLoader messageLoader = MessageLoader.getInstance();
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
