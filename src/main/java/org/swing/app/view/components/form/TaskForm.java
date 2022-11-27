package org.swing.app.view.components.form;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.form.components.wrapper.LabelAndInputWrapper;
import org.swing.app.view.components.form.components.wrapper.LabelAndInputWrapperFactory;

import java.awt.Dimension;
import java.time.LocalDateTime;

public abstract class TaskForm extends FormBase<TaskDto> {

    protected static final String TITLE_LABEL = "Title: ";
    protected static final String START_DATETIME_LABEL = "Start datetime: ";
    protected static final String FINISH_DATETIME_LABEL = "Finish datetime: ";

    protected LabelAndInputWrapper titleWrapper;
    protected LabelAndInputWrapper startDateTimeWrapper;
    protected LabelAndInputWrapper finishDateTimeWrapper;

    private int labelWidthInWrapper;
    private float rateOfLabelWidthInWrapper;

    public TaskForm(TaskDto taskDto) {
        super();
        this.labelWidthInWrapper = ViewConstant.DEFAULT_LABEL_WIDTH;
        this.rateOfLabelWidthInWrapper = 0;
        this.init(taskDto);
    }

    public TaskForm() {
        super();
        this.labelWidthInWrapper = ViewConstant.DEFAULT_LABEL_WIDTH;
        this.rateOfLabelWidthInWrapper = 0;
        this.init();
    }

    protected void initTitleInputWrapper() {
        final String labelText = TITLE_LABEL;
        this.titleWrapper = LabelAndInputWrapperFactory.createLabelAndTextFieldWrapper(labelText);
    }

    protected void initTitleWrapper(String title) {
        final String labelText = TITLE_LABEL;
        this.titleWrapper = LabelAndInputWrapperFactory.createLabelAndTextFieldWrapper(labelText, title);
    }

    protected void initStartDateTimeInputWrapper() {
        final String labelText = START_DATETIME_LABEL;
        this.startDateTimeWrapper = LabelAndInputWrapperFactory.createLabelAndDateTimeChooserWrapper(labelText);
    }

    protected void initStartDateTimeInputWrapper(LocalDateTime startDateTime) {
        final String labelText = START_DATETIME_LABEL;
        this.startDateTimeWrapper = LabelAndInputWrapperFactory
                .createLabelAndDateTimeChooserWrapper(labelText, startDateTime);
    }

    protected void initFinishDateTimeInputWrapper() {
        final String labelText = FINISH_DATETIME_LABEL;
        this.finishDateTimeWrapper = LabelAndInputWrapperFactory.createLabelAndDateTimeChooserWrapper(labelText);
    }

    protected void initFinishDateTimeInputWrapper(LocalDateTime finishDateTime) {
        final String labelText = FINISH_DATETIME_LABEL;
        this.finishDateTimeWrapper = LabelAndInputWrapperFactory
                .createLabelAndDateTimeChooserWrapper(labelText, finishDateTime);
    }

    protected abstract void init();

    protected abstract void init(TaskDto taskDto);

    @Override
    protected void loadChildComponentsSize() {
        this.childComponentSizeMap.clear();

        final int availableWidth = getSize().width - ViewConstant.LARGE_RESERVE_WIDTH;
        final byte defaultInputAndLabelWrapperHeight = 60;

        if (this.rateOfLabelWidthInWrapper > 0) {
            this.labelWidthInWrapper = (int) (this.rateOfLabelWidthInWrapper * availableWidth);
        }
        this.childComponentSizeMap.put(this.titleWrapper,
                new Dimension(this.labelWidthInWrapper, defaultInputAndLabelWrapperHeight));
        this.childComponentSizeMap.put(this.startDateTimeWrapper,
                new Dimension(this.labelWidthInWrapper, defaultInputAndLabelWrapperHeight));
        this.childComponentSizeMap.put(this.finishDateTimeWrapper,
                new Dimension(this.labelWidthInWrapper, defaultInputAndLabelWrapperHeight));
    }

    @Override
    protected void setNotResizableChildComponents() {
    }
}
