package org.swing.app.view.home.components;

import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.ViewComponent;
import org.swing.app.view.components.ui.VerticalViewportView;
import org.swing.app.view.home.components.taskbase.TaskPanel;

import java.awt.Dimension;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class TaskContainerViewportView extends VerticalViewportView {

    private Set<TaskPanel> taskPanels = new LinkedHashSet<>();

    public TaskContainerViewportView() {
        super();
    }

    @Override
    protected void loadChildComponentsSize() {
        this.childComponentSizeMap.clear();

        final int preferChildComponentWidth = getPreferChildComponentWidth();
        final Iterator<TaskPanel> taskPanelIterator = this.taskPanels.iterator();

        while (taskPanelIterator.hasNext()) {
            final TaskPanel taskPanel = taskPanelIterator.next();
            final int taskPanelPreferHeight = taskPanel.getPreferHeight();
            this.childComponentSizeMap.put(taskPanel, new Dimension(preferChildComponentWidth, taskPanelPreferHeight));

            this.preferHeight += taskPanelPreferHeight + MAIN_LAYOUT.getVgap();
        }
    }

    @Override
    public void resizeWidth(int width) {
        this.preferWidth = width;
        this.preferHeight = ViewConstant.SMALL_RESERVE_HEIGHT;

        // preferHeight will be change in this method
        loadChildComponentsSize();
        this.component.setPreferredSize(new Dimension(this.preferWidth, this.preferHeight));

        resizeChildComponents();
        setNotResizableChildComponents();
        refreshUI();
    }

    @Override
    protected void setNotResizableChildComponents() {
    }

    @Override
    public void addChildComponent(ViewComponent childComponent, int position) {
        if (!(childComponent instanceof TaskPanel)) {
            throw new IllegalArgumentException();
        }
        super.addChildComponent(childComponent, position);
        this.taskPanels.add((TaskPanel) childComponent);
    }
}
