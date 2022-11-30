package org.swing.app.view.edittask.roottask;

import org.swing.app.view.edittask.TaskFormFrame;

public class AddingRootTaskFormFrame extends TaskFormFrame {

    public AddingRootTaskFormFrame() {
        super(new RootTaskFormFactory());
    }
}
