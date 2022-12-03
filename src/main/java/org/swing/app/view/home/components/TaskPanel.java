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

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Iterator;

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

    protected void init(TaskPanelDto taskPanelDto) {
        initTaskCenterPanel(taskPanelDto);
        addChildComponent(this.taskCenterPanel);
    }

    public void update(TaskPanelDto taskPanelDto) {
        this.taskCenterPanel.update(taskPanelDto);
    }

    private int getWidthUsedByOtherChildComponents() {
        final Iterator< Dimension> childComponentSizeIterator = this.childComponentSizeMap.values().iterator();
        int sumOfWidth = 0;

        while (childComponentSizeIterator.hasNext()) {
            final Dimension childComponentSize = childComponentSizeIterator.next();
            sumOfWidth += childComponentSize.width + MAIN_LAYOUT.getHgap();
        }

        return sumOfWidth;
    }

    private boolean hasOtherChildComponents() {
        return this.childComponents.size() > 1;
    }

    protected abstract void loadOtherChildComponentsSize();

    @Override
    protected void loadChildComponentsSize() {
        this.childComponentSizeMap.clear();

        final int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;

        final int maxChildComponentHeight = availableHeight - MAIN_LAYOUT.getVgap();

        if (hasOtherChildComponents()) {
            loadOtherChildComponentsSize();
        }

        final int taskCenterPanelWidth = availableWidth - getWidthUsedByOtherChildComponents() - MAIN_LAYOUT.getHgap();
        this.childComponentSizeMap.put(this.taskCenterPanel,
                new Dimension(taskCenterPanelWidth, maxChildComponentHeight));
    }
}
