package org.swing.app.view.taskform;

import org.swing.app.dto.TaskDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.PanelWrapperComponent;
import org.swing.app.view.components.ViewComponent;
import org.swing.app.view.components.form.Form;
import org.swing.app.view.components.form.components.InputComponentWrapper;
import org.swing.app.view.components.form.components.factory.InputComponentWrapperFactory;
import org.swing.app.view.components.form.validators.TextValidator;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Optional;

public abstract class TaskForm extends PanelWrapperComponent implements Form<TaskDto> {

    private static final int TITLE_MAX_LENGTH = 256;

    protected static final byte HORIZONTAL_GAP = ViewConstant.LARGE_H_GAP;
    protected static final byte VERTICAL_GAP = ViewConstant.LARGE_V_GAP;
    private static final LayoutManager MAIN_LAYOUT = new FlowLayout(FlowLayout.CENTER, HORIZONTAL_GAP, VERTICAL_GAP);

    protected static final String TITLE_LABEL_TEXT = "Title: ";
    protected static final String START_DATETIME_LABEL_TEXT = "Start datetime: ";
    protected static final String FINISH_DATETIME_LABEL_TEXT = "Finish datetime: ";

    protected InputComponentWrapper<String> titleInputWrapper;
    protected InputComponentWrapper<LocalDateTime> startDatetimeInputWrapper;
    protected InputComponentWrapper<LocalDateTime> finishDatetimeInputWrapper;

    protected int labelWidthInWrapper;
    protected float rateOfLabelWidthInWrapper;

    public TaskForm() {
        super();
        setLayout(MAIN_LAYOUT);
        init();
    }

    public TaskForm(TaskDto taskDto) {
        super();
        setLayout(MAIN_LAYOUT);
        init(taskDto);
    }

    public void setLabelWidthInWrapper(int labelWidthInWrapper) {
        this.labelWidthInWrapper = labelWidthInWrapper;
    }

    public void setRateOfLabelWidthInWrapper(float rateOfLabelWidthInWrapper) {
        this.rateOfLabelWidthInWrapper = rateOfLabelWidthInWrapper;
    }

    protected void initTitleInputWrapper() {
        this.titleInputWrapper = InputComponentWrapperFactory.createTextFieldWrapper(TITLE_LABEL_TEXT);
    }

    protected void initTitleInputWrapper(String title) {
        this.titleInputWrapper = InputComponentWrapperFactory.createTextFieldWrapper(TITLE_LABEL_TEXT, title);
    }

    protected void initStartDatetimeInputWrapper() {
        this.startDatetimeInputWrapper = InputComponentWrapperFactory
                .createDatetimeChooserWrapper(START_DATETIME_LABEL_TEXT);
    }

    protected void initStartDatetimeInputWrapper(LocalDateTime startDatetime) {
        this.startDatetimeInputWrapper = InputComponentWrapperFactory
                .createDatetimeChooserWrapper(START_DATETIME_LABEL_TEXT, startDatetime);
    }

    protected void initFinishDatetimeInputWrapper() {
        this.finishDatetimeInputWrapper = InputComponentWrapperFactory
                .createDatetimeChooserWrapper(FINISH_DATETIME_LABEL_TEXT);
    }

    protected void initFinishDatetimeInputWrapper(LocalDateTime finishDateTime) {
        this.finishDatetimeInputWrapper = InputComponentWrapperFactory
                .createDatetimeChooserWrapper(FINISH_DATETIME_LABEL_TEXT, finishDateTime);
    }

    protected abstract void init();

    protected abstract void init(TaskDto taskDto);

    @Override
    public void clear() {
        final Iterator<ViewComponent> childComponentIterator = getChildComponentIterator();

        while (childComponentIterator.hasNext()) {
            final ViewComponent childComponent = childComponentIterator.next();
            if (childComponent instanceof InputComponentWrapper) {
                ((InputComponentWrapper) childComponent).clear();
            }
        }
    }

    protected String validateTitleInputWrapper() {
        final String emptyMessage = "";
        final MessageLoader messageLoader = MessageLoader.getInstance();
        final Optional<String> optionalTitleInputValue = this.titleInputWrapper.getValue();

        if (!TextValidator.validateNotEmpty(optionalTitleInputValue)) {
            return messageLoader.getMessage("title.value.invalid");
        }
        if (!TextValidator.validateMaxLength(optionalTitleInputValue, TITLE_MAX_LENGTH)) {
            return messageLoader.getMessage("title.length.invalid");
        }
        return emptyMessage;
    }
}
