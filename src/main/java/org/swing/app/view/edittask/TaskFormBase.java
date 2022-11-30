package org.swing.app.view.edittask;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.PanelWrapperComponent;
import org.swing.app.view.components.ViewComponent;
import org.swing.app.view.components.form.components.wrapper.LabelAndInputWrapper;
import org.swing.app.view.components.form.components.wrapper.LabelAndInputWrapperFactory;

import java.awt.FlowLayout;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class TaskFormBase extends PanelWrapperComponent implements TaskForm {

    protected static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT,
            ViewConstant.LARGE_H_GAP, ViewConstant.LARGE_V_GAP);

    protected static final String TITLE_LABEL_TEXT = "Title: ";
    protected static final String IMPORTANT_LABEL_TEXT = "Important: ";
    protected static final String START_DATETIME_LABEL_TEXT = "Start datetime: ";
    protected static final String FINISH_DATETIME_LABEL_TEXT = "Finish datetime: ";
    protected static final String CANCELABLE_LABEL_TEXT = "Cancelable: ";
    protected static final String COMPLETED_LABEL_TEXT = "Completed: ";
    protected static final String NOTE_LABEL_TEXT = "Note: ";

    protected LabelAndInputWrapper titleInputWrapper;
    protected LabelAndInputWrapper importantInputWrapper;
    protected LabelAndInputWrapper startDatetimeInputWrapper;
    protected LabelAndInputWrapper finishDatetimeInputWrapper;
    protected LabelAndInputWrapper cancelableInputWrapper;
    protected LabelAndInputWrapper completedInputWrapper;
    protected LabelAndInputWrapper noteInputWrapper;

    protected int labelWidthInWrapper;
    protected float rateOfLabelWidthInWrapper;

    public TaskFormBase() {
        super();
        setLayout(MAIN_LAYOUT);
    }

    public void setLabelWidthInWrapper(int labelWidthInWrapper) {
        this.labelWidthInWrapper = labelWidthInWrapper;
    }

    public void setRateOfLabelWidthInWrapper(float rateOfLabelWidthInWrapper) {
        this.rateOfLabelWidthInWrapper = rateOfLabelWidthInWrapper;
    }

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
    public void initStartDatetimeInputWrapper() {
        this.startDatetimeInputWrapper = LabelAndInputWrapperFactory
                .createLabelAndDateTimeChooserWrapper(START_DATETIME_LABEL_TEXT);
    }

    @Override
    public void initStartDatetimeInputWrapper(LocalDateTime startDatetime) {
        this.startDatetimeInputWrapper = LabelAndInputWrapperFactory
                .createLabelAndDateTimeChooserWrapper(START_DATETIME_LABEL_TEXT, startDatetime);
    }

    @Override
    public void initFinishDatetimeInputWrapper() {
        this.finishDatetimeInputWrapper = LabelAndInputWrapperFactory
                .createLabelAndDateTimeChooserWrapper(FINISH_DATETIME_LABEL_TEXT);
    }

    @Override
    public void initFinishDatetimeInputWrapper(LocalDateTime finishDateTime) {
        this.finishDatetimeInputWrapper = LabelAndInputWrapperFactory
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

    @Override
    public void clear() {
        final Iterator<ViewComponent> childComponentIterator = getChildComponentIterator();

        while (childComponentIterator.hasNext()) {
            final ViewComponent childComponent = childComponentIterator.next();
            if (childComponent instanceof LabelAndInputWrapper) {
                ((LabelAndInputWrapper) childComponent).clear();
            }
        }
    }
}
