package org.swing.app.view.home.components;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.WrapperComponent;
import org.swing.app.view.components.ui.TextLabel;

import java.awt.Dimension;
import java.awt.FlowLayout;

public abstract class TaskCenterPanel extends WrapperComponent {

    protected static FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT,
            ViewConstant.SMALL_H_GAP, ViewConstant.SMALL_V_GAP);

    private TextLabel titleLabel;

    public TaskCenterPanel(TaskPanelDto taskPanelDto) {
        super();
        this.component.setLayout(MAIN_LAYOUT);
        init(taskPanelDto);
    }

    private boolean hasOtherChildComponents() {
        return this.childComponents.size() > 1;
    }

    protected abstract void loadOtherChildComponentsSize();

    protected void init(TaskPanelDto taskPanelDto) {
        this.titleLabel = new TextLabel(taskPanelDto.getTitle());
        addChildComponent(this.titleLabel);
    }

    public void update(TaskPanelDto taskPanelDto) {
        this.titleLabel.update(taskPanelDto.getTitle());
    }

    @Override
    protected void loadChildComponentsSize() {
        final int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;

        final int maxChildComponentWidth = availableWidth - MAIN_LAYOUT.getHgap();
        final int maxChildComponentHeight = availableHeight - MAIN_LAYOUT.getVgap();

        int titleLabelHeight = maxChildComponentHeight;
        if (hasOtherChildComponents()) {
            titleLabelHeight = availableHeight / 2 - MAIN_LAYOUT.getVgap();
            loadOtherChildComponentsSize();
        }
        this.childComponentSizeMap.put(this.titleLabel, new Dimension(maxChildComponentWidth, titleLabelHeight));
    }
}