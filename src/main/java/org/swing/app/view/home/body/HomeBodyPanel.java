package org.swing.app.view.home.body;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.WrapperComponent;
import org.swing.app.view.home.components.TaskContentPanel;
import org.swing.app.view.home.components.nodetask.NodeTaskContentPanel;
import org.swing.app.view.home.components.roottask.RootTaskContentPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Set;

public class HomeBodyPanel extends WrapperComponent {

    private static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT,
            ViewConstant.MEDIUM_H_GAP, ViewConstant.MEDIUM_V_GAP);

    private TaskContentPanel rootTaskContentPanel = null;
    private TaskContentPanel nodeTaskContentPanel = null;

    public HomeBodyPanel() {
        super();
        setLayout(MAIN_LAYOUT);
    }

    public void loadRootTaskContentPanel(String taskTitle, Set<TaskPanelDto> childTaskPanelDtos) {
        this.rootTaskContentPanel = new RootTaskContentPanel(taskTitle, childTaskPanelDtos);
    }

    public void loadNodeTaskContentPanel(String taskTitle, Set<TaskPanelDto> childTaskPanelDtos) {
        this.nodeTaskContentPanel = new NodeTaskContentPanel(taskTitle, childTaskPanelDtos);
        resize(getSize());
    }

    public void removeNodeTaskContentPanel() {
        this.nodeTaskContentPanel.dispose();
        this.nodeTaskContentPanel = null;
        resize(getSize());
    }

    @Override
    protected void loadChildComponentsSize() {
        int availableWidth = getSize().width - ViewConstant.MEDIUM_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ViewConstant.MEDIUM_RESERVE_HEIGHT;

        final int maxChildComponentHeight = availableHeight - MAIN_LAYOUT.getVgap();

        if (this.nodeTaskContentPanel != null) {
            final int nodeTaskContentPanelWidth = (int) (((float) 0.4) * availableWidth);
            this.childComponentSizeMap.put(this.nodeTaskContentPanel,
                    new Dimension(nodeTaskContentPanelWidth, maxChildComponentHeight));
            availableWidth -= nodeTaskContentPanelWidth + MAIN_LAYOUT.getHgap();
        }

        if (this.rootTaskContentPanel != null) {
            final int rootTaskContentPanelWidth = availableWidth - MAIN_LAYOUT.getHgap();
            this.childComponentSizeMap.put(this.rootTaskContentPanel,
                    new Dimension(rootTaskContentPanelWidth, maxChildComponentHeight));
        }
    }

    @Override
    protected void setNotResizableChildComponents() {
    }
}
