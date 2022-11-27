package org.swing.app.view.home.components;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.PanelWrapperComponent;
import org.swing.app.view.home.components.factory.TaskCenterPanelFactory;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Iterator;

public abstract class TaskPanel extends PanelWrapperComponent {

    protected static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT,
            ViewConstant.SMALL_H_GAP, ViewConstant.SMALL_V_GAP);

    // task center panel la vung chua chinh
    // left side hay right chi don gian la addChildComponent thoi
    private TaskCenterPanel taskCenterPanel;
    private TaskCenterPanelFactory taskCenterPanelFactory;

    public TaskPanel(TaskPanelDto taskPanelDto, TaskCenterPanelFactory taskCenterPanelFactory) {
        super();
        this.component.setLayout(MAIN_LAYOUT);
        this.taskCenterPanelFactory = taskCenterPanelFactory;
        init(taskPanelDto);
    }

    private boolean hasOtherChildComponents() {
        return this.childComponents.size() > 1;
    }
    protected abstract void loadOtherChildComponentsSize();

    private void initTaskCenterPanel(TaskPanelDto taskPanelDto) {
        this.taskCenterPanel = this.taskCenterPanelFactory.createTaskCenterPanel(taskPanelDto);
        this.taskCenterPanel.setResizable(true);
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
