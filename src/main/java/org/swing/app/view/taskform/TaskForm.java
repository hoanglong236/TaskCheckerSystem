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

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Optional;

public abstract class TaskForm extends PanelWrapperComponent implements Form<TaskDto> {

    private static final int TITLE_MAX_LENGTH = 150;
    private static final int NOTE_MAX_LENGTH = 300;

    private static final byte HORIZONTAL_GAP = ViewConstant.MEDIUM_H_GAP;
    private static final byte VERTICAL_GAP = ViewConstant.MEDIUM_V_GAP;
    private static final LayoutManager MAIN_LAYOUT = new FlowLayout(FlowLayout.CENTER, HORIZONTAL_GAP, VERTICAL_GAP);

    private static final String TITLE_LABEL_TEXT = "Title: ";
    private static final String START_DATETIME_LABEL_TEXT = "Start datetime: ";
    private static final String FINISH_DATETIME_LABEL_TEXT = "Finish datetime: ";
    private static final String IMPORTANT_LABEL_TEXT = "Important: ";
    private static final String NOTE_LABEL_TEXT = "Note: ";

    private InputComponentWrapper<String> titleInputWrapper;
    private InputComponentWrapper<LocalDateTime> startDateTimeInputWrapper;
    private InputComponentWrapper<LocalDateTime> finishDateTimeInputWrapper;
    private InputComponentWrapper<Boolean> importantInputWrapper;
    private InputComponentWrapper<String> noteInputWrapper;

    private int labelWidthInWrapper;
    private float rateOfLabelWidthInWrapper;

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

    protected abstract boolean isNeedImportantInputWrapper();
    protected abstract boolean isNeedStartDateTimeInputWrapper();
    protected abstract boolean isNeedFinishDateTimeInputWrapper();
    protected abstract boolean isNeedNoteInputWrapper();

    private void initTitleInputWrapper() {
        this.titleInputWrapper = InputComponentWrapperFactory.createTextFieldWrapper(TITLE_LABEL_TEXT);
    }

    private void initTitleInputWrapper(String title) {
        this.titleInputWrapper = InputComponentWrapperFactory.createTextFieldWrapper(TITLE_LABEL_TEXT, title);
    }

    private void initImportantInputWrapper() {
        this.importantInputWrapper = InputComponentWrapperFactory
                .createYesNoOptionChooserWrapper(IMPORTANT_LABEL_TEXT);
    }

    private void initImportantInputWrapper(boolean important) {
        this.importantInputWrapper = InputComponentWrapperFactory
                .createYesNoOptionChooserWrapper(IMPORTANT_LABEL_TEXT, important);
    }

    private void initStartDateTimeInputWrapper() {
        this.startDateTimeInputWrapper = InputComponentWrapperFactory
                .createDateTimeChooserWrapper(START_DATETIME_LABEL_TEXT);
    }

    private void initStartDateTimeInputWrapper(LocalDateTime startDateTime) {
        this.startDateTimeInputWrapper = InputComponentWrapperFactory
                .createDateTimeChooserWrapper(START_DATETIME_LABEL_TEXT, startDateTime);
    }

    private void initFinishDateTimeInputWrapper() {
        this.finishDateTimeInputWrapper = InputComponentWrapperFactory
                .createDateTimeChooserWrapper(FINISH_DATETIME_LABEL_TEXT);
    }

    private void initFinishDateTimeInputWrapper(LocalDateTime finishDateTime) {
        this.finishDateTimeInputWrapper = InputComponentWrapperFactory
                .createDateTimeChooserWrapper(FINISH_DATETIME_LABEL_TEXT, finishDateTime);
    }

    private void initNoteInputWrapper() {
        this.noteInputWrapper = InputComponentWrapperFactory.createTextAreaWrapper(NOTE_LABEL_TEXT);
    }

    private void initNoteInputWrapper(String note) {
        this.noteInputWrapper = InputComponentWrapperFactory.createTextAreaWrapper(NOTE_LABEL_TEXT, note);
    }

    private void init() {
        initTitleInputWrapper();
        addChildComponent(this.titleInputWrapper);

        if (isNeedImportantInputWrapper()) {
            initImportantInputWrapper();
            addChildComponent(this.importantInputWrapper);
        }

        if (isNeedStartDateTimeInputWrapper()) {
            initStartDateTimeInputWrapper();
            addChildComponent(this.startDateTimeInputWrapper);
        }

        if (isNeedFinishDateTimeInputWrapper()) {
            initFinishDateTimeInputWrapper();
            addChildComponent(this.finishDateTimeInputWrapper);
        }

        if (isNeedNoteInputWrapper()) {
            initNoteInputWrapper();
            addChildComponent(this.noteInputWrapper);
        }
    }

    private void init(TaskDto taskDto) {
        initTitleInputWrapper(taskDto.getTitle());
        addChildComponent(this.titleInputWrapper);

        if (isNeedImportantInputWrapper()) {
            initImportantInputWrapper(taskDto.isImportant());
            addChildComponent(this.importantInputWrapper);
        }

        if (isNeedStartDateTimeInputWrapper()) {
            initStartDateTimeInputWrapper(taskDto.getStartDateTime());
            addChildComponent(this.startDateTimeInputWrapper);
        }

        if (isNeedFinishDateTimeInputWrapper()) {
            initFinishDateTimeInputWrapper(taskDto.getFinishDateTime());
            addChildComponent(this.finishDateTimeInputWrapper);
        }

        if (isNeedNoteInputWrapper()) {
            initNoteInputWrapper(taskDto.getNote());
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
        if (this.importantInputWrapper != null) {
            this.childComponentSizeMap.put(this.importantInputWrapper,
                    new Dimension(maxChildComponentWidth, smallInputWrapperHeight));
        }
        if (this.startDateTimeInputWrapper != null) {
            this.childComponentSizeMap.put(this.startDateTimeInputWrapper,
                    new Dimension(maxChildComponentWidth, smallInputWrapperHeight));
        }
        if (this.finishDateTimeInputWrapper != null) {
            this.childComponentSizeMap.put(this.finishDateTimeInputWrapper,
                    new Dimension(maxChildComponentWidth, smallInputWrapperHeight));
        }
        if (this.noteInputWrapper != null) {
            final int noteInputWrapperHeight = availableHeight - (VERTICAL_GAP + smallInputWrapperHeight) * 4;
            this.childComponentSizeMap.put(this.noteInputWrapper,
                    new Dimension(maxChildComponentWidth, noteInputWrapperHeight));
        }
    }

    @Override
    protected void setNotResizableChildComponents() {
    }

    private String validateForTitleInputWrapper() {
        final String emptyMessage = "";
        final MessageLoader messageLoader = MessageLoader.getInstance();
        final Optional<String> optionalTitle = this.titleInputWrapper.getValue();

        if (!TextValidator.validateNotEmpty(optionalTitle)) {
            return messageLoader.getMessage("title.not.empty");
        }
        if (!TextValidator.validateMaxLength(optionalTitle, TITLE_MAX_LENGTH)) {
            return messageLoader.getMessage("title.length.invalid") + TITLE_MAX_LENGTH;
        }
        return emptyMessage;
    }

    private String validateForStartAndFinishDateTimeWrapper() {
        final String emptyMessage = "";
        final MessageLoader messageLoader = MessageLoader.getInstance();
        final Optional<LocalDateTime> optionalStartDateTime = this.startDateTimeInputWrapper.getValue();
        final Optional<LocalDateTime> optionalFinishDateTime = this.finishDateTimeInputWrapper.getValue();

        if (optionalFinishDateTime.isPresent()) {
            if (optionalStartDateTime.isPresent()) {
                final LocalDateTime startDateTime = optionalStartDateTime.get();
                final LocalDateTime finishDateTime = optionalFinishDateTime.get();

                if (!finishDateTime.isAfter(startDateTime)) {
                    return messageLoader.getMessage("finish.datetime.must.be.after.start.datetime");
                }
                return emptyMessage;
            }
            return messageLoader.getMessage("datetime.range.invalid");
        }

        if (optionalStartDateTime.isPresent()) {
            return messageLoader.getMessage("datetime.range.invalid");
        }
        return emptyMessage;
    }

    public String validateForNoteInputWrapper() {
        final String emptyMessage = "";
        final MessageLoader messageLoader = MessageLoader.getInstance();
        final Optional<String> optionalNote = this.noteInputWrapper.getValue();

        if (!TextValidator.validateMaxLength(optionalNote, TITLE_MAX_LENGTH)) {
            return messageLoader.getMessage("note.length.invalid") + NOTE_MAX_LENGTH;
        }
        return emptyMessage;
    }

    @Override
    public String validate() {
        final StringBuilder validateMessage = new StringBuilder();

        final String validateTitleInputWrapperResult = validateForTitleInputWrapper();
        validateMessage.append(validateTitleInputWrapperResult);

        if (this.startDateTimeInputWrapper != null && this.finishDateTimeInputWrapper != null) {
            final String validateStartAndFinishDateTimeWrapperResult = validateForStartAndFinishDateTimeWrapper();
            validateMessage.append(validateStartAndFinishDateTimeWrapperResult);
        }

        if (this.noteInputWrapper != null) {
            final String validateNoteResult = validateForNoteInputWrapper();
            validateMessage.append(validateNoteResult);
        }

        return validateMessage.toString();
    }

    @Override
    public TaskDto getFormData() {
        final TaskDto taskDto = new TaskDto();

        final Optional<String> optionalTitle = this.titleInputWrapper.getValue();
        optionalTitle.ifPresent(title -> taskDto.setTitle(title.trim()));
        if (this.importantInputWrapper != null) {
            final Optional<Boolean> optionalImportant = this.importantInputWrapper.getValue();
            optionalImportant.ifPresent(important -> taskDto.setImportant(important));
        }
        if (this.startDateTimeInputWrapper != null) {
            final Optional<LocalDateTime> optionalStartDateTime = this.startDateTimeInputWrapper.getValue();
            optionalStartDateTime.ifPresent(startDateTime -> taskDto.setStartDateTime(startDateTime));
        }
        if (this.finishDateTimeInputWrapper != null) {
            final Optional<LocalDateTime> optionalFinishDateTime = this.startDateTimeInputWrapper.getValue();
            optionalFinishDateTime.ifPresent(finishDateTime -> taskDto.setFinishDateTime(finishDateTime));
        }
        if (this.noteInputWrapper != null) {
            final Optional<String> optionalNote = this.noteInputWrapper.getValue();
            optionalNote.ifPresent(note -> taskDto.setNote(note));
        }

        return taskDto;
    }

    @Override
    public void setFormData(TaskDto taskDto) {
        if (taskDto == null) {
            clear();
            return;
        }
        this.titleInputWrapper.setValue(taskDto.getTitle());
        if (this.importantInputWrapper != null) {
            this.importantInputWrapper.setValue(taskDto.isImportant());
        }
        if (this.startDateTimeInputWrapper != null) {
            this.startDateTimeInputWrapper.setValue(taskDto.getStartDateTime());
        }
        if (this.finishDateTimeInputWrapper != null) {
            this.finishDateTimeInputWrapper.setValue(taskDto.getFinishDateTime());
        }
        if (this.noteInputWrapper != null) {
            this.noteInputWrapper.setValue(taskDto.getNote());
        }
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
