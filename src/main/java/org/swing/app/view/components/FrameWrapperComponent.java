package org.swing.app.view.components;

import javax.swing.JFrame;
import java.awt.Color;

public abstract class FrameWrapperComponent extends WrapperComponent {

    protected FrameWrapperComponent() {
        super();
        this.component = new JFrame();
        initFrameState();
    }

    public void initFrameState() {
        setVisible(true);
        ((JFrame) this.component).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setFrameTitle(String title) {
        ((JFrame) this.component).setTitle(title);
    }

    @Override
    public void refreshUI() {
        ((JFrame) this.component).getContentPane().revalidate();
        ((JFrame) this.component).getContentPane().repaint();
        ((JFrame) this.component).pack();
        ((JFrame) this.component).setLocationRelativeTo(null);
    }

    @Override
    public void setBackgroundColor(Color color) {
        ((JFrame) this.component).getContentPane().setBackground(color);
    }
}
