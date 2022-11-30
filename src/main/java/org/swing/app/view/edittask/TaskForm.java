package org.swing.app.view.edittask;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.components.form.Form;

import java.time.LocalDateTime;

public interface TaskForm extends Form<TaskDto> {

    void initTitleInputWrapper();
    void initTitleInputWrapper(String title);
    void initImportantInputWrapper();
    void initImportantInputWrapper(boolean important);
    void initStartDatetimeInputWrapper();
    void initStartDatetimeInputWrapper(LocalDateTime startDatetime);
    void initFinishDatetimeInputWrapper();
    void initFinishDatetimeInputWrapper(LocalDateTime finishDateTime);
    void initCancelableInputWrapper();
    void initCancelableInputWrapper(boolean cancelable);
    void initCompletedInputWrapper();
    void initCompletedInputWrapper(boolean completed);
    void initNoteInputWrapper();
    void initNoteInputWrapper(String note);
}
