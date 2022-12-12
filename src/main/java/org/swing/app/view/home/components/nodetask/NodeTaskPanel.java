package org.swing.app.view.home.components.nodetask;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.components.ui.Label;
import org.swing.app.view.home.components.taskbase.TaskPanel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

public class NodeTaskPanel extends TaskPanel {

    private Label importantLabel;

    public NodeTaskPanel(HomeFrameController homeFrameController, int preferHeight, TaskPanelDto taskPanelDto) {
        super(homeFrameController, preferHeight, taskPanelDto);
    }

    private void initImportantLabel(boolean important) {
        if (important) {
            this.importantLabel = UIComponentFactory.createLabel(ViewConstant.ICON_LOCATION_IMPORTANT);
        } else {
            this.importantLabel = UIComponentFactory.createLabel(ViewConstant.ICON_LOCATION_UNIMPORTANT);
        }
    }

    @Override
    protected void initTaskCenterPanel(TaskPanelDto taskPanelDto) {
        this.taskCenterPanel = new NodeTaskCenterPanel(this.homeFrameController, taskPanelDto);
    }

    @Override
    protected void init(TaskPanelDto taskPanelDto) {
        initActivationLabel();
        addChildComponent(this.activationLabel);

        initStatusChecker(taskPanelDto.isCompleted());
        addChildComponent(this.statusChecker);

        initTaskCenterPanel(taskPanelDto);
        addChildComponent(this.taskCenterPanel);

        initImportantLabel(taskPanelDto.isImportant());
        addChildComponent(this.importantLabel);

        initPopup();
        setPopup(this.popup);
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
        updateStatusChecker(taskPanelDto.isCompleted());
        updateTaskCenterPanel(taskPanelDto);
        updateImportantLabel(taskPanelDto.isImportant());
    }

    @Override
    protected void setNotResizableChildComponents() {
        this.activationLabel.setResizable(false);
        this.statusChecker.setResizable(false);
        this.importantLabel.setResizable(false);
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

        final int statusCheckerWidth = 30;
        this.childComponentSizeMap.put(this.statusChecker, new Dimension(statusCheckerWidth, maxChildComponentHeight));

        final int importantLabelWidth = 30;
        this.childComponentSizeMap.put(this.importantLabel,
                new Dimension(importantLabelWidth, maxChildComponentHeight));

        final int taskCenterPanelWidth = availableWidth - hGap - activationLabelWidth
                - hGap - statusCheckerWidth - hGap - importantLabelWidth - hGap;
        this.childComponentSizeMap.put(this.taskCenterPanel,
                new Dimension(taskCenterPanelWidth, maxChildComponentHeight));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
