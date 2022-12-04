package org.swing.app.view.home.components;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.PanelWrapperComponent;
import org.swing.app.view.components.ui.ActivationLabel;
import org.swing.app.view.components.ui.Checker;
import org.swing.app.view.components.ui.Label;
import org.swing.app.view.components.ui.Popup;
import org.swing.app.view.components.ui.PopupItem;
import org.swing.app.view.components.ui.UIComponentFactory;
import org.swing.app.view.home.components.factory.TaskComponentFactory;

import javax.swing.JMenuItem;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class TaskPanel extends PanelWrapperComponent implements ActionListener {

    protected static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT,
            ViewConstant.SMALL_H_GAP, ViewConstant.SMALL_V_GAP);

    protected ActivationLabel activationLabel;
    protected Checker statusChecker;
    protected Label importantLabel;
    protected TaskCenterPanel taskCenterPanel;
    protected Popup popup;
    private PopupItem editPopupItem;
    private PopupItem removePopupItem;

    private TaskComponentFactory taskComponentFactory;

    public TaskPanel(TaskComponentFactory taskComponentFactory, TaskPanelDto taskPanelDto) {
        super();
        this.component.setLayout(MAIN_LAYOUT);
        this.taskComponentFactory = taskComponentFactory;
        init(taskPanelDto);
    }

    protected void initActivationLabel() {
        this.activationLabel = UIComponentFactory.createActivationLabel();
        this.activationLabel.setResizable(false);
    }

    protected void initStatusChecker(boolean checked) {
        this.statusChecker = UIComponentFactory.createChecker(checked);
        this.statusChecker.setResizable(false);
    }

    protected void initImportantLabel(boolean important) {
        if (important) {
            this.importantLabel = UIComponentFactory.createLabel(ViewConstant.ICON_LOCATION_IMPORTANT);
        } else {
            this.importantLabel = UIComponentFactory.createLabel(ViewConstant.ICON_LOCATION_UNIMPORTANT);
        }
        this.importantLabel.setResizable(false);
    }

    protected void initTaskCenterPanel(TaskPanelDto taskPanelDto) {
        this.taskCenterPanel = this.taskComponentFactory.createTaskCenterPanel(taskPanelDto);
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

    protected void updateImportantLabel(boolean important) {
        if (important) {
            this.importantLabel.setIcon(ViewConstant.ICON_LOCATION_IMPORTANT);
        } else {
            this.importantLabel.setIcon(ViewConstant.ICON_LOCATION_UNIMPORTANT);
        }
    }

    protected void updateTaskCenterPanel(TaskPanelDto taskPanelDto) {
        this.taskCenterPanel.update(taskPanelDto);
    }

    public abstract void update(TaskPanelDto taskPanelDto);

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem eventSource = (JMenuItem) e.getSource();

        if (eventSource == this.editPopupItem.getMenuItem()) {
            return;
        }
        if (eventSource == this.removePopupItem.getMenuItem()) {
            return;
        }
    }
}
