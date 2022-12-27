package org.swing.app.view.taskform.nodetask;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.form.components.InputComponentWrapper;
import org.swing.app.view.components.form.components.factory.InputComponentWrapperFactory;
import org.swing.app.view.taskform.TaskForm;

import java.awt.Dimension;

// TODO: should we use template design pattern for this
public class NodeTaskForm extends TaskForm {

    private static final String IMPORTANT_LABEL_TEXT = "Important: ";
    private static final String NOTE_LABEL_TEXT = "Note: ";

    private InputComponentWrapper<Boolean> importantInputWrapper;
    private InputComponentWrapper<String> noteInputWrapper;

    public NodeTaskForm() {
        super();
    }

    public NodeTaskForm(TaskDto taskDto) {
        super(taskDto);
    }

    private void initImportantInputWrapper() {
        this.importantInputWrapper = InputComponentWrapperFactory
                .createYesNoOptionChooserWrapper(IMPORTANT_LABEL_TEXT);
    }

    private void initImportantInputWrapper(boolean important) {
        this.importantInputWrapper = InputComponentWrapperFactory
                .createYesNoOptionChooserWrapper(IMPORTANT_LABEL_TEXT, important);
    }

    private void initNoteInputWrapper() {
        this.noteInputWrapper = InputComponentWrapperFactory.createTextAreaWrapper(NOTE_LABEL_TEXT);
    }

    private void initNoteInputWrapper(String note) {
        this.noteInputWrapper = InputComponentWrapperFactory.createTextAreaWrapper(NOTE_LABEL_TEXT, note);
    }

    @Override
    protected void init() {
        initTitleInputWrapper();
        addChildComponent(this.titleInputWrapper);

        initImportantInputWrapper();
        addChildComponent(this.importantInputWrapper);

        initStartDatetimeInputWrapper();
        addChildComponent(this.startDatetimeInputWrapper);

        initFinishDatetimeInputWrapper();
        addChildComponent(this.finishDatetimeInputWrapper);

        initNoteInputWrapper();
        addChildComponent(this.noteInputWrapper);
    }

    @Override
    protected void init(TaskDto taskDto) {
        initTitleInputWrapper(taskDto.getTitle());
        addChildComponent(this.titleInputWrapper);

        initImportantInputWrapper(taskDto.isImportant());
        addChildComponent(this.importantInputWrapper);

        initStartDatetimeInputWrapper(taskDto.getStartDatetime());
        addChildComponent(this.startDatetimeInputWrapper);

        initFinishDatetimeInputWrapper(taskDto.getFinishDatetime());
        addChildComponent(this.finishDatetimeInputWrapper);

        initNoteInputWrapper(taskDto.getNote());
        addChildComponent(this.noteInputWrapper);
    }

    @Override
    protected void loadChildComponentsSize() {
        final int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;

        final int maxChildComponentWidth = availableWidth - HORIZONTAL_GAP;
        final byte smallInputWrapperHeight = 50;

        this.childComponentSizeMap.put(this.titleInputWrapper,
                new Dimension(maxChildComponentWidth, smallInputWrapperHeight));
        this.childComponentSizeMap.put(this.importantInputWrapper,
                new Dimension(maxChildComponentWidth, smallInputWrapperHeight));
        this.childComponentSizeMap.put(this.startDatetimeInputWrapper,
                new Dimension(maxChildComponentWidth, smallInputWrapperHeight));
        this.childComponentSizeMap.put(this.finishDatetimeInputWrapper,
                new Dimension(maxChildComponentWidth, smallInputWrapperHeight));

        final int noteInputWrapperHeight = availableHeight - (VERTICAL_GAP + smallInputWrapperHeight) * 4;
        this.childComponentSizeMap.put(this.noteInputWrapper,
                new Dimension(maxChildComponentWidth, noteInputWrapperHeight));
    }

    @Override
    protected void setNotResizableChildComponents() {
    }

    // TODO: handle this
    @Override
    public String validate() {
        final StringBuilder validateMessage = new StringBuilder();

        final String validateTitleInputWrapperResult = validateTitleInputWrapper();
        validateMessage.append(validateTitleInputWrapperResult);

        return validateMessage.toString();
    }

    @Override
    public TaskDto getFormData() {
//        final String title = (String) this.titleInputWrapper.getValue();
//        final boolean important = this.importantInputWrapper.getValue().equals("Yes");
//        final LocalDateTime startDatetime = (LocalDateTime) this.startDatetimeInputWrapper.getValue();
//        final LocalDateTime finishDatetime = (LocalDateTime) this.finishDatetimeInputWrapper.getValue();
//        final String note = (String) this.noteInputWrapper.getValue();

        final TaskDto taskDto = new TaskDto();
//        taskDto.setTitle(title);
//        taskDto.setImportant(important);
//        taskDto.setStartDatetime(startDatetime);
//        taskDto.setFinishDatetime(finishDatetime);
//        taskDto.setNote(note);

        return taskDto;
    }

    @Override
    public void setFormData(TaskDto taskDto) {
        if (taskDto == null) {
            clear();
            return;
        }
        this.titleInputWrapper.setValue(taskDto.getTitle());
        this.importantInputWrapper.setValue(taskDto.isImportant());
        this.startDatetimeInputWrapper.setValue(taskDto.getStartDatetime());
        this.finishDatetimeInputWrapper.setValue(taskDto.getFinishDatetime());
        this.noteInputWrapper.setValue(taskDto.getNote());
    }
}
