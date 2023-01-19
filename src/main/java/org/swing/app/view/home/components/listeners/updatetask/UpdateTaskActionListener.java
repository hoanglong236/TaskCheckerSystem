package org.swing.app.view.home.components.listeners.updatetask;

import org.swing.app.controller.HomeFrameController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateTaskActionListener extends UpdateTaskListener implements ActionListener {

    public UpdateTaskActionListener(HomeFrameController homeFrameController,
            UpdateTaskListenerSubject updateTaskListenerSubject) {

        super(homeFrameController, updateTaskListenerSubject);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        handleUpdateTaskEvent(e);
    }
}
