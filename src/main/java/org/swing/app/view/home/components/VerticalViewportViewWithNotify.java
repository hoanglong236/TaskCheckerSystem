package org.swing.app.view.home.components;

import org.swing.app.util.MessageLoader;
import org.swing.app.view.components.ViewComponent;
import org.swing.app.view.components.ui.TextLabel;
import org.swing.app.view.components.ui.VerticalViewportView;

import java.awt.Component;

public class VerticalViewportViewWithNotify extends VerticalViewportView {

    private TextLabel notifyLabel;

    public VerticalViewportViewWithNotify() {
        super();
        this.notifyLabel = null;
        init();
    }

    private void initNotifyLabel() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.notifyLabel = new TextLabel(messageLoader.getMessage("label.notify.text"));
    }

    private void init() {
        initNotifyLabel();
        addChildComponent(this.notifyLabel);
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
        final Component component = childComponent.getComponent();
        return (components[components.length] == component);
    }
}
