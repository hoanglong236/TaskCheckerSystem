package org.swing.app.view.home.components.roottask;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.home.components.TaskPanel;
import org.swing.app.view.home.components.factory.TaskCenterPanelFactory;

import java.awt.Dimension;

public class RootTaskPanel extends TaskPanel {

    public RootTaskPanel(TaskPanelDto taskPanelDto, TaskCenterPanelFactory taskCenterPanelFactory) {
        super(taskPanelDto, taskCenterPanelFactory);
    }

    @Override
    protected void init(TaskPanelDto taskPanelDto) {
        initActivationLabel();
        addChildComponent(this.activationLabel);

        initTaskCenterPanel(taskPanelDto);
        addChildComponent(this.taskCenterPanel);

        initPopup();
        setPopup(this.popup);
    }

    @Override
    public void update(TaskPanelDto taskPanelDto) {
        updateTaskCenterPanel(taskPanelDto);
    }

    @Override
    protected void setNotResizableChildComponents() {
        this.activationLabel.setResizable(false);
    }

    @Override
    protected void loadChildComponentsSize() {
        this.childComponentSizeMap.clear();

        final int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;

        final int hGap = MAIN_LAYOUT.getHgap();
        final int maxChildComponentHeight = availableHeight - MAIN_LAYOUT.getVgap();

        final byte activationLabelWidth = 5;
        this.childComponentSizeMap.put(this.activationLabel,
                new Dimension(activationLabelWidth, maxChildComponentHeight));

        final int taskCenterPanelWidth = availableWidth - hGap - activationLabelWidth - hGap;
        this.childComponentSizeMap.put(this.taskCenterPanel,
                new Dimension(taskCenterPanelWidth, maxChildComponentHeight));
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
