package org.swing.app.view.edittask;

import java.time.LocalDateTime;

public interface TaskForm {

    void initTitleInputWrapper();
    void initTitleInputWrapper(String title);
    void initImportantInputWrapper();
    void initImportantInputWrapper(boolean important);
    void initStartDateTimeInputWrapper();
    void initStartDateTimeInputWrapper(LocalDateTime startDateTime);
    void initFinishDateTimeInputWrapper();
    void initFinishDateTimeInputWrapper(LocalDateTime finishDateTime);
    void initCancelableInputWrapper();
    void initCancelableInputWrapper(boolean cancelable);
    void initCompletedInputWrapper();
    void initCompletedInputWrapper(boolean completed);

    void initNoteInputWrapper();
    void initNoteInputWrapper(String note);
}
