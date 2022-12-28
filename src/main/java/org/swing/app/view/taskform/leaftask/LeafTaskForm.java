package org.swing.app.view.taskform.leaftask;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.taskform.TaskForm;

public class LeafTaskForm extends TaskForm {

    public LeafTaskForm() {
        super();
    }

    public LeafTaskForm(TaskDto taskDto) {
        super(taskDto);
    }

    @Override
    protected boolean isNeedImportantInputWrapper() {
        return false;
    }

    @Override
    protected boolean isNeedStartDateTimeInputWrapper() {
        return false;
    }

    @Override
    protected boolean isNeedFinishDateTimeInputWrapper() {
        return false;
    }

    @Override
    protected boolean isNeedNoteInputWrapper() {
        return false;
    }
}
