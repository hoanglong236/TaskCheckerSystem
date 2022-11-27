package org.swing.app.view.home.components.nodetask;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.ui.ActivationLabel;
import org.swing.app.view.components.ui.Checker;
import org.swing.app.view.components.ui.Label;
import org.swing.app.view.components.ui.Popup;
import org.swing.app.view.components.ui.PopupItem;
import org.swing.app.view.components.ui.UIComponentFactory;
import org.swing.app.view.home.components.TaskPanel;
import org.swing.app.view.home.components.factory.TaskCenterPanelFactory;

import java.awt.Dimension;

public class NodeTaskPanel extends TaskPanel {

    private ActivationLabel  activationLabel;
    private Checker statusChecker;
    private Label importantLabel;
    private Popup popup;

    public NodeTaskPanel(TaskPanelDto taskPanelDto,
            TaskCenterPanelFactory taskCenterPanelFactory) {
        super(taskPanelDto, taskCenterPanelFactory);
    }

    private void initActivationLabel() {
        this.activationLabel = UIComponentFactory.createActivationLabel();
        this.activationLabel.setResizable(false);
    }

    private void initStatusChecker(boolean checked) {
        this.statusChecker = UIComponentFactory.createChecker(checked);
        this.statusChecker.setResizable(false);
    }

    private void initImportantLabel(boolean important) {
        if (important) {
            this.importantLabel = UIComponentFactory.createLabel(ViewConstant.ICON_LOCATION_IMPORTANT);
        } else {
            this.importantLabel = UIComponentFactory.createLabel(ViewConstant.ICON_LOCATION_UNIMPORTANT);
        }
        this.importantLabel.setResizable(false);
    }

    // TODO: pack popup
    private void initPopup() {
        this.popup = UIComponentFactory.createPopup();

        final MessageLoader messageLoader = MessageLoader.getInstance();
        final PopupItem editPopupItem = UIComponentFactory.createPopupItem(
                messageLoader.getMessage("popup.item.edit"));
        final PopupItem removePopupItem = UIComponentFactory.createPopupItem(
                messageLoader.getMessage("popup.item.remove"));

        this.popup.addPopupItem(editPopupItem);
        this.popup.addPopupItem(removePopupItem);
    }

    @Override
    protected void init(TaskPanelDto taskPanelDto) {
        super.init(taskPanelDto);

        initActivationLabel();
        addChildComponent(this.activationLabel);

        initStatusChecker(taskPanelDto.isCompleted());
        addChildComponent(this.statusChecker);

        initImportantLabel(taskPanelDto.isImportant());
        addChildComponent(this.importantLabel);

        initPopup();
        setPopup(this.popup);
    }

    private void updateStatusChecker(boolean checked) {
        this.statusChecker.setChecked(checked);
    }

    private void updateImportantLabel(boolean important) {
        if (important) {
            this.importantLabel.setIcon(ViewConstant.ICON_LOCATION_IMPORTANT);
        } else {
            this.importantLabel.setIcon(ViewConstant.ICON_LOCATION_UNIMPORTANT);
        }
    }

    @Override
    public void update(TaskPanelDto taskPanelDto) {
        super.update(taskPanelDto);
        updateStatusChecker(taskPanelDto.isCompleted());
        updateImportantLabel(taskPanelDto.isImportant());
    }

    @Override
    protected void setNotResizableChildComponents() {
        this.statusChecker.setResizable(false);
        this.importantLabel.setResizable(false);
    }

    @Override
    protected void loadOtherChildComponentsSize() {
        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;
        final int maxChildComponentHeight = availableHeight - MAIN_LAYOUT.getVgap();

        final byte activationLabelWidth = 5;
        this.childComponentSizeMap.put(this.activationLabel,
                new Dimension(activationLabelWidth, maxChildComponentHeight));

        final int statusCheckerWidth = 30;
        this.childComponentSizeMap.put(this.statusChecker, new Dimension(statusCheckerWidth, maxChildComponentHeight));

        final int importantLabelWidth = 30;
        this.childComponentSizeMap.put(this.importantLabel,
                new Dimension(importantLabelWidth, maxChildComponentHeight));
    }
}
