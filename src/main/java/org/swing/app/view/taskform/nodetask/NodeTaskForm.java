package org.swing.app.view.taskform.nodetask;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.taskform.TaskForm;

public class NodeTaskForm extends TaskForm {

    public NodeTaskForm() {
        super();
    }

    public NodeTaskForm(TaskDto taskDto) {
        super(taskDto);
    }

    @Override
    protected boolean isNeedImportantInputWrapper() {
        return true;
    }

    @Override
    protected boolean isNeedStartDateTimeInputWrapper() {
        return true;
    }

    @Override
    protected boolean isNeedFinishDateTimeInputWrapper() {
        return true;
    }

    @Override
    protected boolean isNeedNoteInputWrapper() {
        return true;
    }


}
