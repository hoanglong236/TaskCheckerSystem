package org.swing.app.view.home.components.roottask;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.ui.ActivationLabel;
import org.swing.app.view.components.ui.Popup;
import org.swing.app.view.components.ui.PopupItem;
import org.swing.app.view.home.components.TaskPanel;
import org.swing.app.view.home.components.factory.TaskCenterPanelFactory;

import java.awt.Dimension;

public class RootTaskPanel extends TaskPanel {

    private ActivationLabel activationLabel;
    private Popup popup;

    public RootTaskPanel(TaskPanelDto taskPanelDto, TaskCenterPanelFactory taskCenterPanelFactory) {
        super(taskPanelDto, taskCenterPanelFactory);
        this.activationLabel = null;
    }

    private void initActivationLabel() {
        this.activationLabel = new ActivationLabel();
        this.activationLabel.setResizable(false);
    }

    // TODO: pack popup
    private void initPopup() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        final PopupItem editPopupItem = new PopupItem(messageLoader.getMessage("popup.item.edit"));
        final PopupItem removePopupItem = new PopupItem(messageLoader.getMessage("popup.item.remove"));

        this.popup.addPopupItem(editPopupItem);
        this.popup.addPopupItem(removePopupItem);
    }

    @Override
    protected void init(TaskPanelDto taskPanelDto) {
        super.init(taskPanelDto);

        initActivationLabel();
        addChildComponentToTheFirstPosition(this.activationLabel);

        initPopup();
        setPopup(this.popup);
    }

    @Override
    protected void setNotResizableChildComponents() {
        this.activationLabel.setResizable(false);
    }

    @Override
    protected void loadOtherChildComponentsSize() {
        this.childComponentSizeMap.clear();

        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;
        final int maxChildComponentHeight = availableHeight - MAIN_LAYOUT.getHgap();

        final byte activationLabelWidth = 5;
        this.childComponentSizeMap.put(this.activationLabel,
                new Dimension(activationLabelWidth, maxChildComponentHeight));
    }

    public void activate() {
        this.activationLabel.activate();
    }

    public void deactivate() {
        this.activationLabel.deactivate();
    }

    // TODO: handle dispose popup
    public void disposePopup() {
    }
}
