package org.swing.app.view.taskform.taskformpanel;

import org.swing.app.dto.TaskDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.PanelWrapperComponent;
import org.swing.app.view.components.ViewComponent;
import org.swing.app.view.components.form.Form;
import org.swing.app.view.components.form.components.wrapper.InputComponentWrapper;
import org.swing.app.view.components.form.components.wrapper.factory.InputComponentWrapperFactory;
import org.swing.app.view.components.form.validators.TextValidator;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.time.LocalDateTime;
import java.util.Iterator;

public abstract class TaskFormPanel extends PanelWrapperComponent implements Form<TaskDto> {

    private static final int TITLE_MAX_LENGTH = 150;
    private static final int NOTE_MAX_LENGTH = 300;

    private static final byte HORIZONTAL_GAP = ViewConstant.MEDIUM_H_GAP;
    private static final byte VERTICAL_GAP = ViewConstant.MEDIUM_V_GAP;
    private static final LayoutManager MAIN_LAYOUT = new FlowLayout(FlowLayout.CENTER, HORIZONTAL_GAP, VERTICAL_GAP);

    private static final String TITLE_LABEL_TEXT = "Title: ";
    private static final String DEADLINE_LABEL_TEXT = "Deadline: ";
    private static final String IMPORTANT_LABEL_TEXT = "Important: ";
    private static final String NOTE_LABEL_TEXT = "Note: ";

    private InputComponentWrapper<String> titleInputWrapper;
    private InputComponentWrapper<LocalDateTime> deadlineInputWrapper;
    private InputComponentWrapper<Boolean> importantInputWrapper;
    private InputComponentWrapper<String> noteInputWrapper;

    private int labelWidthInWrapper;
    private float rateOfLabelWidthInWrapper;

    private TaskDto taskDto = null;

    public TaskFormPanel() {
        super();
        setLayout(MAIN_LAYOUT);
        init();
    }

    public void setLabelWidthInWrapper(int labelWidthInWrapper) {
        this.labelWidthInWrapper = labelWidthInWrapper;
    }

    public void setRateOfLabelWidthInWrapper(float rateOfLabelWidthInWrapper) {
        this.rateOfLabelWidthInWrapper = rateOfLabelWidthInWrapper;
    }

    protected abstract boolean isNeedImportantInputWrapper();
    protected abstract boolean isNeedDeadlineInputWrapper();
    protected abstract boolean isNeedNoteInputWrapper();

    private void initTitleInputWrapper() {
        this.titleInputWrapper = InputComponentWrapperFactory.createTextFieldWrapper(TITLE_LABEL_TEXT);
    }

    private void initImportantInputWrapper() {
        this.importantInputWrapper = InputComponentWrapperFactory
                .createYesNoOptionChooserWrapper(IMPORTANT_LABEL_TEXT);
    }

    private void initDeadlineInputWrapper() {
        this.deadlineInputWrapper = InputComponentWrapperFactory
                .createDateTimeChooserWrapper(DEADLINE_LABEL_TEXT);
    }

    private void initNoteInputWrapper() {
        this.noteInputWrapper = InputComponentWrapperFactory.createTextAreaWrapper(NOTE_LABEL_TEXT);
    }

    private void init() {
        initTitleInputWrapper();
        addChildComponent(this.titleInputWrapper);

        if (isNeedImportantInputWrapper()) {
            initImportantInputWrapper();
            addChildComponent(this.importantInputWrapper);
        }
        if (isNeedDeadlineInputWrapper()) {
            initDeadlineInputWrapper();
            addChildComponent(this.deadlineInputWrapper);
        }
        if (isNeedNoteInputWrapper()) {
            initNoteInputWrapper();
            addChildComponent(this.noteInputWrapper);
        }
    }

    @Override
    protected void loadChildComponentsSize() {
        final int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;

        final int maxChildComponentWidth = availableWidth - HORIZONTAL_GAP;
        final byte smallInputWrapperHeight = 50;

        this.childComponentSizeMap.put(this.titleInputWrapper,
                new Dimension(maxChildComponentWidth, smallInputWrapperHeight));

        if (isNeedImportantInputWrapper()) {
            this.childComponentSizeMap.put(this.importantInputWrapper,
                    new Dimension(maxChildComponentWidth, smallInputWrapperHeight));
        }
        if (isNeedDeadlineInputWrapper()) {
            this.childComponentSizeMap.put(this.deadlineInputWrapper,
                    new Dimension(maxChildComponentWidth, smallInputWrapperHeight));
        }
        if (isNeedNoteInputWrapper()) {
            final int noteInputWrapperHeight = availableHeight - (VERTICAL_GAP + smallInputWrapperHeight) * 4;
            this.childComponentSizeMap.put(this.noteInputWrapper,
                    new Dimension(maxChildComponentWidth, noteInputWrapperHeight));
        }
    }

    @Override
    protected void setNotResizableChildComponents() {
    }

    private String validateTitleInputWrapper() {
        final String emptyMessage = "";
        final MessageLoader messageLoader = MessageLoader.getInstance();
        final String title = this.titleInputWrapper.getValue();

        if (!TextValidator.validateNotEmpty(title)) {
            return messageLoader.getMessage("title.not.empty");
        }
        if (!TextValidator.validateMaxLength(title, TITLE_MAX_LENGTH)) {
            return messageLoader.getMessage("title.length.invalid") + TITLE_MAX_LENGTH;
        }
        return emptyMessage;
    }

    private String validateDeadlineDateTimeWrapper() {
        final String emptyMessage = "";
        final LocalDateTime deadline = this.deadlineInputWrapper.getValue();

        if (deadline == null) {
            return emptyMessage;
        }
        if (deadline.isAfter(LocalDateTime.now())) {
            return emptyMessage;
        }

        final MessageLoader messageLoader = MessageLoader.getInstance();
        return messageLoader.getMessage("deadline.invalid");
    }

    public String validateNoteInputWrapper() {
        final String emptyMessage = "";
        final MessageLoader messageLoader = MessageLoader.getInstance();
        final String note = this.noteInputWrapper.getValue();

        if (!TextValidator.validateMaxLength(note, TITLE_MAX_LENGTH)) {
            return messageLoader.getMessage("note.length.invalid") + NOTE_MAX_LENGTH;
        }
        return emptyMessage;
    }

    @Override
    public String validate() {
        final StringBuilder validateMessage = new StringBuilder();

        final String validateTitleInputWrapperResult = validateTitleInputWrapper();
        validateMessage.append(validateTitleInputWrapperResult);

        if (isNeedDeadlineInputWrapper()) {
            final String validateDeadlineDateTimeWrapperResult = validateDeadlineDateTimeWrapper();
            validateMessage.append(validateDeadlineDateTimeWrapperResult);
        }
        if (isNeedNoteInputWrapper()) {
            final String validateNoteResult = validateNoteInputWrapper();
            validateMessage.append(validateNoteResult);
        }

        return validateMessage.toString();
    }

    @Override
    public TaskDto getFormData() {
        TaskDto taskDtoFormData;

        if (this.taskDto == null) {
            taskDtoFormData = new TaskDto();
        } else {
            taskDtoFormData = this.taskDto.getCopy();
        }

        final String title = this.titleInputWrapper.getValue();
        taskDtoFormData.setTitle(title);

        if (isNeedImportantInputWrapper()) {
            final boolean important = this.importantInputWrapper.getValue();
            taskDtoFormData.setImportant(important);
        }
        if (isNeedDeadlineInputWrapper()) {
            final LocalDateTime deadline = this.deadlineInputWrapper.getValue();
            taskDtoFormData.setDeadline(deadline);
        }
        if (isNeedNoteInputWrapper()) {
            final String note = this.noteInputWrapper.getValue();
            taskDtoFormData.setNote(note);
        }

        return taskDtoFormData;
    }

    @Override
    public void setFormData(TaskDto taskDto) {
        this.taskDto = taskDto;

        if (taskDto == null) {
            clear();
            return;
        }

        this.titleInputWrapper.setValue(taskDto.getTitle());
        if (isNeedImportantInputWrapper()) {
            this.importantInputWrapper.setValue(taskDto.isImportant());
        }
        if (isNeedDeadlineInputWrapper()) {
            this.deadlineInputWrapper.setValue(taskDto.getDeadline());
        }
        if (isNeedNoteInputWrapper()) {
            this.noteInputWrapper.setValue(taskDto.getNote());
        }
    }

    public void reset() {
        setFormData(this.taskDto);
    }

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
}
