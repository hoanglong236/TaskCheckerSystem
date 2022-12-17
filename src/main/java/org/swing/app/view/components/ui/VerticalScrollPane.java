package org.swing.app.view.components.ui;

import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.PanelWrapperComponent;
import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.ViewComponent;
import org.swing.app.view.components.factory.JComponentFactory;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Dimension;

public class VerticalScrollPane extends SimpleComponent {

    private final PanelWrapperComponent viewportViewPanel;

    public VerticalScrollPane(PanelWrapperComponent viewportViewPanel) {
        this.component = JComponentFactory.createJScrollPane();
        this.viewportViewPanel = viewportViewPanel;
        init();
    }

    private void customVerticalScrollBar() {
        final JScrollBar verticalScrollBar = ((JScrollPane) this.component).getVerticalScrollBar();
        final int scrollBarHeight = verticalScrollBar.getPreferredSize().height;
        verticalScrollBar.setPreferredSize(new Dimension(ViewConstant.VERTICAL_SCROLLBAR_WIDTH, scrollBarHeight));
    }

    private void init() {
        ((JScrollPane) this.component).setViewportView(this.viewportViewPanel.getComponent());
        ((JScrollPane) this.component).setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        ((JScrollPane) this.component).setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        customVerticalScrollBar();
    }

    @Override
    public void resize(Dimension dimension) {
        this.component.setPreferredSize(dimension);

        final int viewportViewPanelWidth = dimension.width - ViewConstant.SCROLL_RESERVE_WIDTH;
        ((VerticalViewportView) this.viewportViewPanel).resizeWidth(viewportViewPanelWidth);

        this.component.revalidate();
        this.component.repaint();
    }

    public void addChildComponent(ViewComponent childComponent) {
        this.viewportViewPanel.addChildComponent(childComponent);
    }

    public void addChildComponentToTheFirstPosition(ViewComponent childComponent) {
        this.viewportViewPanel.addChildComponentToTheFirstPosition(childComponent);
    }

    public void removeChildComponent(ViewComponent childComponent) {
        this.viewportViewPanel.removeChildComponent(childComponent);
    }
}