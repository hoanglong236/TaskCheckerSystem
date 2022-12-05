package org.swing.app.view.home.components.nodetask;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.components.form.components.input.AllowTypingComponent;
import org.swing.app.view.components.form.components.input.InputComponent;
import org.swing.app.view.components.form.components.input.InputComponentFactory;
import org.swing.app.view.components.ui.UIComponentFactory;
import org.swing.app.view.home.components.TaskContentPanel;
import org.swing.app.view.home.components.leaftask.LeafTaskPanelContainerFactory;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Set;

public class NodeTaskContentPanel extends TaskContentPanel implements MouseListener, KeyListener {

    private InputComponent textField;

    public NodeTaskContentPanel(String title, Set<TaskPanelDto> taskPanelDtos) {
        super(new LeafTaskPanelContainerFactory(), title, taskPanelDtos);
        setupTextField();
    }

    // TODO: handle this
    private void setupTextField() {
        this.textField = InputComponentFactory.createTextField();
        ((AllowTypingComponent) this.textField).addKeyListener(this);
    }

    private void onKeyPressedTextField() {
        // TODO: controler handle

        removeChildComponent(this.textField);

        addChildComponent(this.addNewTaskComponent);
        this.addNewTaskComponent.requestFocusInWindow();
    }

    @Override
    protected void initAddNewTaskComponent() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.addNewTaskComponent = UIComponentFactory.createLabel(
                messageLoader.getMessage("add.task.component.text"));
        this.addNewTaskComponent.addMouseListener(this);
    }

    private void onMousePressedForAddNewTaskComponent() {
        removeChildComponent(this.addNewTaskComponent);

        addChildComponent(this.textField);
        this.textField.clear();
        this.textField.requestFocusInWindow();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        final Object eventSource = e.getSource();

        if (eventSource == this.addNewTaskComponent.getComponent()) {
            onMousePressedForAddNewTaskComponent();
        }
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

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        final int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_ENTER) {
            onKeyPressedTextField();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}