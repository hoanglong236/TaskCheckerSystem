package org.swing.app.view.home.components.leaftask;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.ui.Label;
import org.swing.app.view.components.ui.UIComponentFactory;
import org.swing.app.view.home.components.TaskPanel;
import org.swing.app.view.home.components.factory.TaskCenterPanelFactory;

import java.awt.Dimension;

public class LeafTaskPanel extends TaskPanel {

    private Label removeLabel;

    public LeafTaskPanel(TaskPanelDto taskPanelDto,
            TaskCenterPanelFactory taskCenterPanelFactory) {
        super(taskPanelDto, taskCenterPanelFactory);
    }

    private void initRemoveLabel() {
        this.removeLabel = UIComponentFactory.createLabel(ViewConstant.ICON_LOCATION_REMOVE);
        this.removeLabel.setResizable(false);
    }

    @Override
    protected void init(TaskPanelDto taskPanelDto) {
        super.init(taskPanelDto);

        initStatusChecker(taskPanelDto.isCompleted());
        addChildComponentToTheFirstPosition(this.statusChecker);

        initRemoveLabel();
        addChildComponent(this.removeLabel);
    }

    private void updateStatusChecker(boolean checked) {
        this.statusChecker.setChecked(checked);
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
