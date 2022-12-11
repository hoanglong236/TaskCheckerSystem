package org.swing.app.view.home.components;

import org.swing.app.util.MessageLoader;
import org.swing.app.view.components.ViewComponent;
import org.swing.app.view.components.ui.Label;
import org.swing.app.view.components.factory.UIComponentFactory;

import java.awt.Component;
import java.awt.Dimension;

public class TaskContainerViewportViewWithNotify extends TaskContainerViewportView {

    private Label notifyLabel;

    public TaskContainerViewportViewWithNotify() {
        super();
        init();
    }

    private void initNotifyLabel() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.notifyLabel = UIComponentFactory.createLabel(messageLoader.getMessage("label.notify.text"));
    }

    private void init() {
        initNotifyLabel();
        addChildComponent(this.notifyLabel);
        this.notifyLabel.setVisible(false);
    }

    @Override
    protected void loadChildComponentsSize() {
        super.loadChildComponentsSize();

        final int notifyLabelWidth = getPreferChildComponentWidth();
        final int notifyLabelHeight = 40;
        this.childComponentSizeMap.put(this.notifyLabel, new Dimension(notifyLabelWidth, notifyLabelHeight));

        this.preferHeight += notifyLabelHeight;
    }

    @Override
    public void addChildComponent(ViewComponent childComponent) {
        super.addChildComponent(childComponent);
        if (!this.notifyLabel.isVisible()) {
            this.notifyLabel.setVisible(true);
        }
    }

    @Override
    public void addChildComponentToTheFirstPosition(ViewComponent childComponent) {
        super.addChildComponentToTheFirstPosition(childComponent);
        if (isChildComponentInTheLastPositionInUI(this.notifyLabel)) {
            this.notifyLabel.setVisible(false);
        }
    }

    @Override
    public void removeChildComponent(ViewComponent childComponent) {
        super.removeChildComponent(childComponent);
        if (isChildComponentInTheLastPositionInUI(this.notifyLabel)) {
            this.notifyLabel.setVisible(false);
        }
    }

    private boolean isChildComponentInTheLastPositionInUI(ViewComponent childComponent) {
        final Component[] components = this.component.getComponents();
        final int componentCount = components.length;
        final Component component = childComponent.getComponent();

        return (components[componentCount - 1] == component);
    }
}
