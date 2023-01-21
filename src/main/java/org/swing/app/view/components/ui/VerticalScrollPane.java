package org.swing.app.view.components.ui;

import org.swing.app.view.common.ComponentSizeConstants;
import org.swing.app.view.components.PanelWrapperComponent;
import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.factory.JComponentFactory;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Dimension;

public class VerticalScrollPane extends SimpleComponent {

    public VerticalScrollPane() {
        this.sourceComponent = JComponentFactory.createJScrollPane();
        init();
    }

    private void customVerticalScrollBar() {
        final JScrollBar verticalScrollBar = ((JScrollPane) this.sourceComponent).getVerticalScrollBar();
        final int scrollBarHeight = verticalScrollBar.getPreferredSize().height;
        verticalScrollBar.setPreferredSize(new Dimension(ComponentSizeConstants.SCROLLBAR_WIDTH, scrollBarHeight));
    }

    private void init() {
        ((JScrollPane) this.sourceComponent).setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        ((JScrollPane) this.sourceComponent).setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        customVerticalScrollBar();
    }

    public void setViewportViewPanel(PanelWrapperComponent viewportViewPanel) {
        ((JScrollPane) this.sourceComponent).setViewportView(viewportViewPanel.getSourceComponent());
    }
}