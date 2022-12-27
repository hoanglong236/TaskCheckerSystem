package org.swing.app.view.components;

import javax.swing.JFrame;
import java.awt.Color;

public abstract class FrameWrapperComponent extends WrapperComponent {

    protected FrameWrapperComponent() {
        super();
        this.sourceComponent = new JFrame();
        ((JFrame) this.sourceComponent).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setFrameTitle(String title) {
        ((JFrame) this.sourceComponent).setTitle(title);
    }

    @Override
    public void refreshUI() {
        ((JFrame) this.sourceComponent).getContentPane().revalidate();
        ((JFrame) this.sourceComponent).getContentPane().repaint();
        ((JFrame) this.sourceComponent).pack();
        ((JFrame) this.sourceComponent).setLocationRelativeTo(null);
    }

    @Override
    public void setBackgroundColor(Color color) {
        ((JFrame) this.sourceComponent).getContentPane().setBackground(color);
    }
}
