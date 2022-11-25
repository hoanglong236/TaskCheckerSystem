package org.swing.app.view.components.form;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.form.components.InputAndLabelWrapper;
import org.swing.app.view.components.form.components.factory.wrapper.InputAndLabelWrapperFactory;

import java.awt.Dimension;
import java.time.LocalDateTime;

public abstract class TaskForm extends FormBase<TaskDto> {

    protected static final String TITLE_LABEL = "Title: ";
    protected static final String START_DATETIME_LABEL = "Start datetime: ";
    protected static final String FINISH_DATETIME_LABEL = "Finish datetime: ";

    protected InputAndLabelWrapper titleInputWrapper;
    protected InputAndLabelWrapper startDateTimeInputWrapper;
    protected InputAndLabelWrapper finishDateTimeInputWrapper;

    private int labelFieldWidthInInputWrapper;
    private float rateOfLabelFieldWidthInInputWrapper;

    public TaskForm(TaskDto taskDto) {
        super();
        this.labelFieldWidthInInputWrapper = ViewConstant.DEFAULT_LABEL_WIDTH;
        this.rateOfLabelFieldWidthInInputWrapper = 0;
        this.init(taskDto);
    }

    public TaskForm() {
        super();
        this.labelFieldWidthInInputWrapper = ViewConstant.DEFAULT_LABEL_WIDTH;
        this.rateOfLabelFieldWidthInInputWrapper = 0;
        this.init();
    }

    protected void initTitleInputWrapper(String title) {
        final String labelText = TITLE_LABEL;
        this.titleInputWrapper = InputAndLabelWrapperFactory.createTextAndLabelWrapper(labelText, title);
    }

    protected void initTitleInputWrapper() {
        final String labelText = TITLE_LABEL;
        this.titleInputWrapper = InputAndLabelWrapperFactory.createTextAndLabelWrapper(labelText);
    }

    protected void initStartDateTimeInputWrapper(LocalDateTime startDateTime) {
        final String labelText = START_DATETIME_LABEL;
        this.startDateTimeInputWrapper = InputAndLabelWrapperFactory
                .createDateTimeChooserAndLabelWrapper(labelText);
    }

    protected void initStartDateTimeInputWrapper() {
        final String labelText = START_DATETIME_LABEL;
        this.startDateTimeInputWrapper = InputAndLabelWrapperFactory
                .createDateTimeChooserAndLabelWrapper(labelText);
    }

    protected void initFinishDateTimeInputWrapper(LocalDateTime finishDateTime) {
        final String labelText = FINISH_DATETIME_LABEL;
        this.finishDateTimeInputWrapper = InputAndLabelWrapperFactory
                .createDateTimeChooserAndLabelWrapper(labelText);
    }

    protected void initFinishDateTimeInputWrapper() {
        final String labelText = FINISH_DATETIME_LABEL;
        this.finishDateTimeInputWrapper = InputAndLabelWrapperFactory
                .createDateTimeChooserAndLabelWrapper(labelText);
    }

    protected abstract void init(TaskDto taskDto);

    protected abstract void init();

    @Override
    protected void loadChildComponentsSize() {
        this.childComponentSizeMap.clear();

        final int availableWidth = getSize().width - ViewConstant.LARGE_RESERVE_WIDTH;
        final byte defaultInputAndLabelWrapperHeight = 60;

        if (this.rateOfLabelFieldWidthInInputWrapper > 0) {
            this.labelFieldWidthInInputWrapper = (int) (this.rateOfLabelFieldWidthInInputWrapper * availableWidth);
        }
        this.childComponentSizeMap.put(this.titleInputWrapper,
                new Dimension(this.labelFieldWidthInInputWrapper, defaultInputAndLabelWrapperHeight));
        this.childComponentSizeMap.put(this.startDateTimeInputWrapper,
                new Dimension(this.labelFieldWidthInInputWrapper, defaultInputAndLabelWrapperHeight));
        this.childComponentSizeMap.put(this.finishDateTimeInputWrapper,
                new Dimension(this.labelFieldWidthInInputWrapper, defaultInputAndLabelWrapperHeight));
    }

    @Override
    protected void setNotResizableChildComponents() {
    }
}
