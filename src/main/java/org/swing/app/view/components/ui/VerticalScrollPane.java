package org.swing.app.view.components.ui;

import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.ViewComponent;
import org.swing.app.view.components.factory.JComponentFactory;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Dimension;

public class VerticalScrollPane extends SimpleComponent {

    private VerticalViewportView viewportView;

    public VerticalScrollPane(VerticalViewportView viewportViewPanel) {
        this.component = JComponentFactory.createJScrollPane();
        if (viewportViewPanel == null) {
            throw new IllegalArgumentException();
        }
        this.viewportView = viewportViewPanel;
        init();
    }

    private void customVerticalScrollBar() {
        final JScrollBar verticalScrollBar = ((JScrollPane) this.component).getVerticalScrollBar();
        final int scrollBarHeight = verticalScrollBar.getPreferredSize().height;
        verticalScrollBar.setPreferredSize(new Dimension(ViewConstant.VERTICAL_SCROLLBAR_WIDTH, scrollBarHeight));
    }

    private void init() {
        ((JScrollPane) this.component).setViewportView(this.viewportView.getComponent());
        ((JScrollPane) this.component).setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        ((JScrollPane) this.component).setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        customVerticalScrollBar();
    }

    @Override
    public void resize(Dimension dimension) {
        this.component.setPreferredSize(dimension);

        final int viewportViewPanelWidth = dimension.width - ViewConstant.SCROLL_RESERVE_WIDTH;
        this.viewportView.resizeWidth(viewportViewPanelWidth);

        this.component.revalidate();
        this.component.repaint();
    }

    public void addChildComponent(ViewComponent childComponent) {
        this.viewportView.addChildComponent(childComponent);
    }

    public void addChildComponent(ViewComponent childComponent, int position) {
        this.viewportView.addChildComponent(childComponent, position);
    }

    public void addChildComponentToTheFirstPosition(ViewComponent childComponent) {
        this.viewportView.addChildComponentToTheFirstPosition(childComponent);
    }

    public void removeChildComponent(ViewComponent childComponent) {
        this.viewportView.removeChildComponent(childComponent);
    }
}