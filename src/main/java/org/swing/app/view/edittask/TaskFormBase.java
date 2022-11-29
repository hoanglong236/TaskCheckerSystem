package org.swing.app.view.edittask;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.components.form.FormBase;
import org.swing.app.view.components.form.components.wrapper.LabelAndInputWrapper;
import org.swing.app.view.components.form.components.wrapper.LabelAndInputWrapperFactory;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class TaskFormBase extends FormBase<TaskDto> implements TaskForm {

    protected static final String TITLE_LABEL_TEXT = "Title: ";
    protected static final String IMPORTANT_LABEL_TEXT = "Important: ";
    protected static final String START_DATETIME_LABEL_TEXT = "Start datetime: ";
    protected static final String FINISH_DATETIME_LABEL_TEXT = "Finish datetime: ";
    protected static final String CANCELABLE_LABEL_TEXT = "Cancelable: ";
    protected static final String COMPLETED_LABEL_TEXT = "Completed: ";
    protected static final String NOTE_LABEL_TEXT = "Note: ";

    protected LabelAndInputWrapper titleInputWrapper;
    protected LabelAndInputWrapper importantInputWrapper;
    protected LabelAndInputWrapper startDateTimeInputWrapper;
    protected LabelAndInputWrapper finishDateTimeInputWrapper;
    protected LabelAndInputWrapper cancelableInputWrapper;
    protected LabelAndInputWrapper completedInputWrapper;
    protected LabelAndInputWrapper noteInputWrapper;

    private int labelWidthInWrapper;
    private float rateOfLabelWidthInWrapper;

    @Override
    public void initTitleInputWrapper() {
        this.titleInputWrapper = LabelAndInputWrapperFactory.createLabelAndTextFieldWrapper(TITLE_LABEL_TEXT);
    }

    @Override
    public void initTitleInputWrapper(String title) {
        this.titleInputWrapper = LabelAndInputWrapperFactory.createLabelAndTextFieldWrapper(TITLE_LABEL_TEXT, title);
    }

    @Override
    public void initImportantInputWrapper() {
        final Set<String> importantValueRange = new LinkedHashSet<>();
        importantValueRange.add("Yes");
        importantValueRange.add("No");
        this.importantInputWrapper = LabelAndInputWrapperFactory
                .createLabelAndComboBoxWrapper(IMPORTANT_LABEL_TEXT, importantValueRange);
    }

    @Override
    public void initImportantInputWrapper(boolean important) {
        final Set<String> importantValueRange = new LinkedHashSet<>();
        importantValueRange.add("Yes");
        importantValueRange.add("No");
        final String initValue = important ? "Yes" : "No";

        this.importantInputWrapper = LabelAndInputWrapperFactory
                .createLabelAndComboBoxWrapper(IMPORTANT_LABEL_TEXT, importantValueRange, initValue);
    }

    @Override
    public void initStartDateTimeInputWrapper() {
        this.startDateTimeInputWrapper = LabelAndInputWrapperFactory
                .createLabelAndDateTimeChooserWrapper(START_DATETIME_LABEL_TEXT);
    }

    @Override
    public void initStartDateTimeInputWrapper(LocalDateTime startDateTime) {
        this.startDateTimeInputWrapper = LabelAndInputWrapperFactory
                .createLabelAndDateTimeChooserWrapper(START_DATETIME_LABEL_TEXT, startDateTime);
    }

    @Override
    public void initFinishDateTimeInputWrapper() {
        this.finishDateTimeInputWrapper = LabelAndInputWrapperFactory
                .createLabelAndDateTimeChooserWrapper(FINISH_DATETIME_LABEL_TEXT);
    }

    @Override
    public void initFinishDateTimeInputWrapper(LocalDateTime finishDateTime) {
        this.finishDateTimeInputWrapper = LabelAndInputWrapperFactory
                .createLabelAndDateTimeChooserWrapper(FINISH_DATETIME_LABEL_TEXT, finishDateTime);
    }

    @Override
    public void initCancelableInputWrapper() {
        final Set<String> cancelableValueRange = new LinkedHashSet<>();
        cancelableValueRange.add("Yes");
        cancelableValueRange.add("No");

        this.cancelableInputWrapper = LabelAndInputWrapperFactory
                .createLabelAndComboBoxWrapper(CANCELABLE_LABEL_TEXT, cancelableValueRange);
    }

    @Override
    public void initCancelableInputWrapper(boolean cancelable) {
        final Set<String> cancelableValueRange = new LinkedHashSet<>();
        cancelableValueRange.add("Yes");
        cancelableValueRange.add("No");
        final String initValue = cancelable ? "Yes" : "No";

        this.cancelableInputWrapper = LabelAndInputWrapperFactory
                .createLabelAndComboBoxWrapper(CANCELABLE_LABEL_TEXT, cancelableValueRange, initValue);
    }

    @Override
    public void initCompletedInputWrapper() {
        final Set<String> completedValueRange = new LinkedHashSet<>();
        completedValueRange.add("Yes");
        completedValueRange.add("No");

        this.completedInputWrapper = LabelAndInputWrapperFactory
                .createLabelAndComboBoxWrapper(COMPLETED_LABEL_TEXT, completedValueRange);
    }

    @Override
    public void initCompletedInputWrapper(boolean completed) {
        final Set<String> completedValueRange = new LinkedHashSet<>();
        completedValueRange.add("Yes");
        completedValueRange.add("No");
        final String initValue = completed ? "Yes" : "No";

        this.completedInputWrapper = LabelAndInputWrapperFactory
                .createLabelAndComboBoxWrapper(COMPLETED_LABEL_TEXT, completedValueRange, initValue);
    }

    @Override
    public void initNoteInputWrapper() {
        this.noteInputWrapper = LabelAndInputWrapperFactory.createLabelAndTextAreaWrapper(NOTE_LABEL_TEXT);
    }

    @Override
    public void initNoteInputWrapper(String note) {
        this.noteInputWrapper = LabelAndInputWrapperFactory.createLabelAndTextAreaWrapper(NOTE_LABEL_TEXT, note);
    }
}
