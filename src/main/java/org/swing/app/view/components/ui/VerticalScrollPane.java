package org.swing.app.view.components.ui;

import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.ViewComponentBase;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Dimension;

public class VerticalScrollPane extends SimpleComponent {

    private VerticalViewportView viewportView;

    public VerticalScrollPane() {
        this.component = JCOMPONENT_FACTORY.createJScrollPane();
        init();
    }

    public VerticalScrollPane(VerticalViewportView viewportViewPanel) {
        this.component = JCOMPONENT_FACTORY.createJScrollPane();
        this.viewportView = viewportViewPanel;
        init();
    }

    private void customVerticalScrollBar() {
        final JScrollBar verticalScrollBar = ((JScrollPane) this.component).getVerticalScrollBar();
        final int scrollBarHeight = verticalScrollBar.getPreferredSize().height;
        verticalScrollBar.setPreferredSize(new Dimension(ViewConstant.VERTICAL_SCROLLBAR_WIDTH, scrollBarHeight));
    }

    private void initViewportViewPanel() {
        if (this.viewportView == null) {
            this.viewportView = new VerticalViewportView();
        }
        this.viewportView.resize(new Dimension(0, ViewConstant.SMALL_RESERVE_HEIGHT));
    }

    private void init() {
        initViewportViewPanel();
        ((JScrollPane) this.component).setViewportView(this.viewportView.getComponent());
        ((JScrollPane) this.component).setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        ((JScrollPane) this.component).setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        customVerticalScrollBar();

        this.component.revalidate();
        this.component.repaint();
    }

    @Override
    public void resize(Dimension dimension) {
        this.component.setPreferredSize(dimension);

        final int viewportViewPanelWidth = dimension.width - ViewConstant.SCROLL_RESERVE_WIDTH;
        final int viewportViewPanelHeight = this.viewportView.getSize().height;
        this.viewportView.resize(new Dimension(viewportViewPanelWidth, viewportViewPanelHeight));

        this.component.revalidate();
        this.component.repaint();
    }

    public void addChildComponent(ViewComponentBase childComponent) {
        this.viewportView.addChildComponent(childComponent);
    }

    public void addChildComponent(ViewComponentBase childComponent, int position) {
        this.viewportView.addChildComponent(childComponent, position);
    }

    public void addChildComponentToTheFirstPosition(ViewComponentBase childComponent, int position) {
        this.viewportView.addChildComponentToTheFirstPosition(childComponent);
    }

    public void removeChildComponent(ViewComponentBase childComponent) {
        this.viewportView.removeChildComponent(childComponent);
    }
}