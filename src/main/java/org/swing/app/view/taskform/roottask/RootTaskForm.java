package org.swing.app.view.taskform.roottask;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.taskform.TaskForm;

public class RootTaskForm extends TaskForm {

    public RootTaskForm() {
        super();
    }

    public RootTaskForm(TaskDto taskDto) {
        super(taskDto);
    }

    @Override
    protected boolean isNeedImportantInputWrapper() {
        return false;
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
        return false;
    }
}
