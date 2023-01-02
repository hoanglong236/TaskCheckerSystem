package org.swing.app.view.components.modal;

import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.components.WrapperComponent;

import javax.swing.JDialog;
import java.awt.Frame;

public abstract class ModalWrapperComponent extends WrapperComponent {

    public static final int DO_NOTHING_ON_CLOSE = JDialog.DO_NOTHING_ON_CLOSE;
    public static final int HIDE_ON_CLOSE = JDialog.HIDE_ON_CLOSE;
    public static final int DISPOSE_ON_CLOSE = JDialog.DISPOSE_ON_CLOSE;
    public static final int EXIT_ON_CLOSE = JDialog.EXIT_ON_CLOSE;

    public ModalWrapperComponent(FrameWrapperComponent parentFrame) {
        super();
        this.sourceComponent = new JDialog((Frame) parentFrame.getSourceComponent(), true);
    }

    public void setDefaultCloseOperation(int operation) {
        ((JDialog) this.sourceComponent).setDefaultCloseOperation(operation);
    }

    public void dispose() {
        ((JDialog) this.sourceComponent).dispose();
        cancelAllEventListeners();
    }
}