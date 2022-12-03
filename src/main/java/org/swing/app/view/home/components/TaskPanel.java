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
import org.swing.app.view.home.components.factory.TaskCenterPanelFactory;

import java.awt.FlowLayout;

public abstract class TaskPanel extends PanelWrapperComponent {

    protected static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT,
            ViewConstant.SMALL_H_GAP, ViewConstant.SMALL_V_GAP);

    protected ActivationLabel activationLabel;
    protected Checker statusChecker;
    protected Label importantLabel;
    protected Popup popup;
    protected TaskCenterPanel taskCenterPanel;

    private TaskCenterPanelFactory taskCenterPanelFactory;

    public TaskPanel(TaskPanelDto taskPanelDto, TaskCenterPanelFactory taskCenterPanelFactory) {
        super();
        this.component.setLayout(MAIN_LAYOUT);
        this.taskCenterPanelFactory = taskCenterPanelFactory;
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

    // TODO: pack popup
    protected void initPopup() {
        this.popup = UIComponentFactory.createPopup();

        final MessageLoader messageLoader = MessageLoader.getInstance();
        final PopupItem editPopupItem = UIComponentFactory.createPopupItem(
                messageLoader.getMessage("popup.item.edit"));
        final PopupItem removePopupItem = UIComponentFactory.createPopupItem(
                messageLoader.getMessage("popup.item.remove"));

        this.popup.addPopupItem(editPopupItem);
        this.popup.addPopupItem(removePopupItem);
    }

    protected void initTaskCenterPanel(TaskPanelDto taskPanelDto) {
        this.taskCenterPanel = this.taskCenterPanelFactory.createTaskCenterPanel(taskPanelDto);
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
}
