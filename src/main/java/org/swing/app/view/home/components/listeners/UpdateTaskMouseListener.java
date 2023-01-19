package org.swing.app.view.home.components.listeners;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.view.home.components.listeners.subjects.UpdateTaskListenerSubject;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UpdateTaskMouseListener extends UpdateTaskListener implements MouseListener {


    public UpdateTaskMouseListener(HomeFrameController homeFrameController,
            UpdateTaskListenerSubject updateTaskListenerSubject) {

        super(homeFrameController, updateTaskListenerSubject);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        handleUpdateTaskEvent(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
