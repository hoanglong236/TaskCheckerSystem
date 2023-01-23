package org.swing.app.view.components;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;

public abstract class FrameWrapperComponent extends WrapperComponent {

    public static final int DO_NOTHING_ON_CLOSE = JFrame.DO_NOTHING_ON_CLOSE;
    public static final int HIDE_ON_CLOSE = JFrame.HIDE_ON_CLOSE;
    public static final int DISPOSE_ON_CLOSE = JFrame.DISPOSE_ON_CLOSE;
    public static final int EXIT_ON_CLOSE = JFrame.EXIT_ON_CLOSE;

    public FrameWrapperComponent() {
        super();
        this.sourceComponent = new JFrame();
    }

    public void setDefaultCloseOperation(int operation) {
        ((JFrame) this.sourceComponent).setDefaultCloseOperation(operation);
    }

    public void setFrameTitle(String title) {
        ((JFrame) this.sourceComponent).setTitle(title);
    }

    @Override
    public void setBackgroundColor(Color color) {
        ((JFrame) this.sourceComponent).getContentPane().setBackground(color);
    }

    @Override
    public void resize(Dimension dimension) {
        super.resize(dimension);
        ((JFrame) this.sourceComponent).pack();
        ((JFrame) this.sourceComponent).setLocationRelativeTo(null);
    }

    @Override
    public void refreshUI() {
        ((JFrame) this.sourceComponent).getContentPane().revalidate();
        ((JFrame) this.sourceComponent).getContentPane().repaint();
    }

    public void dispose() {
        ((JFrame) this.sourceComponent).dispose();
        cancelAllEventListeners();
    }
}
