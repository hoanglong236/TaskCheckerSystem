package org.swing.app.view.taskform;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.PanelWrapperComponent;
import org.swing.app.view.components.ViewComponent;
import org.swing.app.view.components.form.components.LabelAndInputWrapper;
import org.swing.app.view.components.form.components.factory.LabelAndInputWrapperFactory;

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
    protected static final String NOTE_LABEL_TEXT = "Note: ";

    protected LabelAndInputWrapper titleInputWrapper;
    protected LabelAndInputWrapper importantInputWrapper;
    protected LabelAndInputWrapper startDatetimeInputWrapper;
    protected LabelAndInputWrapper finishDatetimeInputWrapper;
    protected LabelAndInputWrapper noteInputWrapper;

    protected int labelWidthInWrapper;
    protected float rateOfLabelWidthInWrapper;

    public TaskFormBase() {
        super();
        setLayout(MAIN_LAYOUT);
        init();
    }

    public TaskFormBase(TaskDto taskDto) {
        super();
        setLayout(MAIN_LAYOUT);
        init(taskDto);
    }

    @Override
    public void setLabelWidthInWrapper(int labelWidthInWrapper) {
        this.labelWidthInWrapper = labelWidthInWrapper;
    }

    @Override
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
    public void initNoteInputWrapper() {
        this.noteInputWrapper = LabelAndInputWrapperFactory.createLabelAndTextAreaWrapper(NOTE_LABEL_TEXT);
    }

    @Override
    public void initNoteInputWrapper(String note) {
        this.noteInputWrapper = LabelAndInputWrapperFactory.createLabelAndTextAreaWrapper(NOTE_LABEL_TEXT, note);
    }

    protected abstract void init();

    protected abstract void init(TaskDto taskDto);

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
