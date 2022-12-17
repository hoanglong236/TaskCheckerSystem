package org.swing.app.view.home.components.roottask;

import org.swing.app.controller.ControllerBase;
import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.home.components.taskbase.TaskPanel;

import java.awt.Dimension;

public class RootTaskPanel extends TaskPanel {

    public RootTaskPanel(HomeFrameController homeFrameController, int preferHeight, TaskPanelDto taskPanelDto) {
        super(homeFrameController, preferHeight, taskPanelDto);
    }

    @Override
    protected void initTaskCenterPanel(TaskPanelDto taskPanelDto) {
        this.taskCenterPanel = new RootTaskCenterPanel(this.homeFrameController, taskPanelDto);
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
    public boolean requestLoadContent() {
        return this.homeFrameController.requestLoadTaskContent(ControllerBase.ROOT_TASK_TYPE, this.taskId);
    }

    @Override
    protected boolean requestUpdate() {
        return this.homeFrameController.requestUpdateTaskPanel(
                ControllerBase.ROOT_TASK_TYPE, this);
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

    // TODO: handle dispose popup
    public void disposePopup() {
    }
}
