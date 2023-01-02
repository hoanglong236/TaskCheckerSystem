package org.swing.app.view.home.components.taskbase;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.ui.label.ActivationLabel;
import org.swing.app.view.components.ui.label.Label;
import org.swing.app.view.components.ui.Popup;
import org.swing.app.view.components.ui.button.CheckBox;
import org.swing.app.view.components.ui.button.PopupItem;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.home.HomeWrapperComponent;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public abstract class TaskPanel extends HomeWrapperComponent implements ActionListener {

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

    private final TaskPanelManager taskPanelManager;

    private final int preferHeight;

    private TaskPanelDto taskPanelDto;

    public TaskPanel(HomeFrameController homeFrameController, TaskPanelManager taskPanelManager,
            int preferHeight, TaskPanelDto taskPanelDto) {

        super(homeFrameController);
        this.taskPanelManager = taskPanelManager;
        this.preferHeight = preferHeight;
        this.taskPanelDto = taskPanelDto;
        setLayout(MAIN_LAYOUT);
        init(taskPanelDto);
    }

    public String getTaskId() {
        return this.taskPanelDto.getId();
    }

    public LocalDateTime getCreateDateTime() {
        return this.taskPanelDto.getCreatedAt();
    }

    public LocalDateTime getModifyDateTime() {
        return this.taskPanelDto.getUpdatedAt();
    }

    public boolean isCompleted() {
        return this.taskPanelDto.isCompleted();
    }

    public int getPreferHeight() {
        return preferHeight;
    }

    protected abstract boolean isNeedStatusChecker();

    protected abstract boolean isNeedImportantLabel();

    public abstract byte getTaskTypeToRequest();

    private void initActivationLabel() {
        this.activationLabel = UIComponentFactory.createActivationLabel();
    }

    private void initStatusChecker(boolean checked) {
        this.statusChecker = UIComponentFactory.createCheckBox("");
        this.statusChecker.setSelected(checked);
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
        int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;

        final int maxChildComponentHeight = availableHeight - VERTICAL_GAP;

        final byte activationLabelWidth = 5;
        this.childComponentSizeMap.put(this.activationLabel,
                new Dimension(activationLabelWidth, maxChildComponentHeight));
        availableWidth -= HORIZONTAL_GAP + activationLabelWidth;

        if (this.statusChecker != null) {
            final int statusCheckerWidth = 30;
            this.childComponentSizeMap.put(this.statusChecker,
                    new Dimension(statusCheckerWidth, maxChildComponentHeight));
            availableWidth -= HORIZONTAL_GAP + activationLabelWidth;
        }

        if (this.importantLabel != null) {
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

        if (this.statusChecker != null) {
            this.statusChecker.setResizable(false);
        }

        if (this.importantLabel != null) {
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

    private void onActionPerformedForEditPopupItem() {
        this.taskPanelManager.updateTaskPanelHandler(this);
    }

    private void onActionPerformedForDeletePopupItem() {
        this.taskPanelManager.deleteTaskPanelHandler(this);
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
        throw new IllegalArgumentException();
    }
}
