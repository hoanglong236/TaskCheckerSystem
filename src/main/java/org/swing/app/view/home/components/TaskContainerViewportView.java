package org.swing.app.view.home.components;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.ViewComponent;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.components.ui.Label;
import org.swing.app.view.components.ui.VerticalViewportView;
import org.swing.app.view.home.HomeWrapperComponent;
import org.swing.app.view.home.components.taskbase.TaskPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class TaskContainerViewportView extends HomeWrapperComponent
        implements MouseListener, VerticalViewportView {

    private Label notifyLabel;
    private static final int NOTIFY_LABEL_WIDTH = ViewConstant.NOTIFY_LABEL_WIDTH;
    private static final int NOTIFY_LABEL_HEIGHT = ViewConstant.NOTIFY_LABEL_HEIGHT;

    protected static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT,
            ViewConstant.SMALL_H_GAP, ViewConstant.SMALL_V_GAP);

    protected int preferHeightOfViewportView;

    protected final Set<TaskPanel> taskPanels = new LinkedHashSet<>();

    public TaskContainerViewportView(HomeFrameController homeFrameController) {
        super(homeFrameController);
        setLayout(MAIN_LAYOUT);
        this.preferHeightOfViewportView = ViewConstant.SMALL_RESERVE_HEIGHT;
        init();
    }

    protected abstract boolean hasNotifyLabel();

    private void initNotifyLabel() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.notifyLabel = UIComponentFactory.createLabel(messageLoader.getMessage("label.notify.text"));
        this.notifyLabel.setVisible(false);
    }

    private void init() {
        if (hasNotifyLabel()) {
            initNotifyLabel();
            addChildComponent(this.notifyLabel);
        }
    }

    @Override
    protected void loadChildComponentsSize() {
        this.childComponentSizeMap.clear();
        final int preferChildComponentWidth = getPreferChildComponentWidth();

        for (final TaskPanel taskPanel : this.taskPanels) {
            final int taskPanelPreferHeight = taskPanel.getPreferHeight();
            this.childComponentSizeMap.put(taskPanel, new Dimension(preferChildComponentWidth, taskPanelPreferHeight));
        }

        if (hasNotifyLabel() || this.notifyLabel.isVisible()) {
            this.childComponentSizeMap.put(this.notifyLabel, new Dimension(NOTIFY_LABEL_WIDTH, NOTIFY_LABEL_HEIGHT));
        }
    }

    public void resizeWidth(int width) {
        resize(new Dimension(width, this.preferHeightOfViewportView));
    }

    @Override
    public void resizeHeightWithoutResizeChildComponent(int height) {
        this.component.setPreferredSize(new Dimension(getSize().width, height));
    }

    @Override
    protected void setNotResizableChildComponents() {
    }

    @Override
    public void addChildComponent(ViewComponent childComponent, int position) {
        super.addChildComponent(childComponent, position);

        if (!(childComponent instanceof TaskPanel)) {
            return;
        }

        final TaskPanel taskPanel = (TaskPanel) childComponent;
        this.taskPanels.add(taskPanel);
        this.preferHeightOfViewportView += taskPanel.getPreferHeight() + MAIN_LAYOUT.getVgap();

        if (hasNotifyLabel() && !(this.notifyLabel.isVisible())) {
            final int positionOfNotifyLabelIfVisible = this.childComponents.indexOf(this.notifyLabel);
            if (position == -1 || position > positionOfNotifyLabelIfVisible) {
                this.notifyLabel.setVisible(true);
                this.preferHeightOfViewportView += NOTIFY_LABEL_HEIGHT + MAIN_LAYOUT.getVgap();
            }
        }

        resizeHeightWithoutResizeChildComponent(this.preferHeightOfViewportView);
    }

    @Override
    public void removeChildComponent(ViewComponent childComponent) {
        super.removeChildComponent(childComponent);
        if (!(childComponent instanceof TaskPanel)) {
            return;
        }

        final TaskPanel taskPanelToRemove = (TaskPanel) childComponent;
        this.taskPanels.remove(taskPanelToRemove);
        this.preferHeightOfViewportView -= taskPanelToRemove.getPreferHeight() + MAIN_LAYOUT.getVgap();

        if (hasNotifyLabel() && this.notifyLabel.isVisible()) {
            final int positionOfNotifyLabelIfVisible = this.childComponents.indexOf(this.notifyLabel);
            if (positionOfNotifyLabelIfVisible == this.childComponents.size() - 1) {
                this.notifyLabel.setVisible(false);
                this.preferHeightOfViewportView -= NOTIFY_LABEL_HEIGHT + MAIN_LAYOUT.getVgap();
            }
        }

        resizeHeightWithoutResizeChildComponent(this.preferHeightOfViewportView);
    }

    protected int getPreferChildComponentWidth() {
        final int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        return availableWidth - MAIN_LAYOUT.getHgap();
    }

    private void deactivateAllTaskPanels() {
        for (final TaskPanel taskPanel : this.taskPanels) {
            taskPanel.deactivate();
        }
    }

    private void onMousePressedForTaskPanel(TaskPanel taskPanel) {
        deactivateAllTaskPanels();
        taskPanel.activate();

        final boolean requestSuccess = taskPanel.requestLoadContent();
        if (!requestSuccess) {
            requestFailureHandler();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        final Object eventSource = e.getSource();

        if (eventSource instanceof TaskPanel) {
            onMousePressedForTaskPanel((TaskPanel) eventSource);
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
