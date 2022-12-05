package org.swing.app.view.home.components.nodetask;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.components.form.components.input.InputComponent;
import org.swing.app.view.components.form.components.input.InputComponentFactory;
import org.swing.app.view.components.ui.UIComponentFactory;
import org.swing.app.view.home.components.TaskContentPanel;
import org.swing.app.view.home.components.leaftask.LeafTaskPanelContainerFactory;

import java.util.Set;

public class NodeTaskContentPanel extends TaskContentPanel {

    private InputComponent textField;

    public NodeTaskContentPanel(String title, Set<TaskPanelDto> taskPanelDtos) {
        super(new LeafTaskPanelContainerFactory(), title, taskPanelDtos);
    }
    // TODO: handle this
    private void initTextField() {
        this.textField = InputComponentFactory.createTextField();
    }

    @Override
    protected void initAddNewTaskComponent() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.addNewTaskComponent = UIComponentFactory.createLabel(
                messageLoader.getMessage("add.task.component.text"));
    }
}
