package org.swing.app.view.components;

import javax.swing.JFrame;

public abstract class FrameWrapperComponent extends WrapperComponent implements Frame {

    protected FrameWrapperComponent() {
        super();
        this.component = new JFrame();
        initFrameState();
    }

    @Override
    public void initFrameState() {
        setVisible(true);
        ((JFrame) this.component).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void setFrameTitle(String title) {
        ((JFrame) this.component).setTitle(title);
    }

    @Override
    public void refreshUI() {
        super.refreshUI();
        ((JFrame) this.component).pack();
        ((JFrame) this.component).setLocationRelativeTo(null);
    }
}
