package org.swing.app.view.home.components.leaftask;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.ui.Checker;
import org.swing.app.view.components.ui.IconLabel;
import org.swing.app.view.home.components.TaskPanel;
import org.swing.app.view.home.components.factory.TaskCenterPanelFactory;

import java.awt.Dimension;

public class LeafTaskPanel extends TaskPanel {

    private Checker statusChecker;
    private IconLabel removeLabel;

    public LeafTaskPanel(TaskPanelDto taskPanelDto,
            TaskCenterPanelFactory taskCenterPanelFactory) {
        super(taskPanelDto, taskCenterPanelFactory);
    }

    private void initStatusChecker(boolean checked) {
        this.statusChecker = new Checker(checked);
        this.statusChecker.setResizable(false);
    }

    private void initRemoveLabel() {
        this.removeLabel = new IconLabel(ViewConstant.ICON_LOCATION_REMOVE);
        this.removeLabel.setResizable(false);
    }

    @Override
    protected void init(TaskPanelDto taskPanelDto) {
        super.init(taskPanelDto);

        initStatusChecker(taskPanelDto.isCompleted());
        addChildComponent(this.statusChecker);

        initRemoveLabel();
        addChildComponent(this.removeLabel);
    }

    private void updateStatusChecker(boolean checked) {
        this.statusChecker.update(checked);
    }

    @Override
    public void update(TaskPanelDto taskPanelDto) {
        super.update(taskPanelDto);
        updateStatusChecker(taskPanelDto.isCompleted());
    }

    @Override
    protected void setNotResizableChildComponents() {
        this.statusChecker.setResizable(false);
        this.removeLabel.setResizable(false);
    }

    @Override
    protected void loadOtherChildComponentsSize() {
        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;
        final int maxChildComponentHeight = availableHeight - MAIN_LAYOUT.getVgap();

        final int statusCheckerWidth = 30;
        this.childComponentSizeMap.put(this.statusChecker, new Dimension(statusCheckerWidth, maxChildComponentHeight));

        final int removeLabelWidth = 30;
        this.childComponentSizeMap.put(this.removeLabel, new Dimension(removeLabelWidth, maxChildComponentHeight));
    }
}
