package org.swing.app.view.components;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Color;

public abstract class FrameWrapperComponent extends WrapperComponent {

    protected FrameWrapperComponent() {
        super();
        this.component = new JFrame();
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

    public void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(getComponent(), message);
    }
}
