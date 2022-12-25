package org.swing.app.view.taskform.leaftask;

import org.swing.app.dto.TaskDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.taskform.TaskForm;

import java.awt.Dimension;
import java.util.Optional;

public class LeafTaskForm extends TaskForm {

    private static final int TITLE_MAX_LENGTH = 256;

    public LeafTaskForm() {
        super();
    }

    public LeafTaskForm(TaskDto taskDto) {
        super(taskDto);
    }

    @Override
    protected void init() {
        initTitleInputWrapper();
        addChildComponent(this.titleInputWrapper);
    }

    @Override
    protected void init(TaskDto taskDto) {
        initTitleInputWrapper(taskDto.getTitle());
        addChildComponent(this.titleInputWrapper);
    }

    @Override
    protected void loadChildComponentsSize() {
        final int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        final int maxChildComponentWidth = availableWidth - HORIZONTAL_GAP;
        final int smallInputWrapperHeight = 50;

        this.childComponentSizeMap.put(this.titleInputWrapper,
                new Dimension(maxChildComponentWidth, smallInputWrapperHeight));
    }

    @Override
    protected void setNotResizableChildComponents() {
    }

    @Override
    public String validate() {
        final StringBuilder validateMessage = new StringBuilder();
        final MessageLoader messageLoader = MessageLoader.getInstance();

        final Optional<String> optionalTitleInputValue = this.titleInputWrapper.getValue();
        if (!optionalTitleInputValue.isPresent()) {
            validateMessage.append(messageLoader.getMessage("title.value.invalid"));
        } else {
            final String title = optionalTitleInputValue.get().trim();
            if (title.length() > TITLE_MAX_LENGTH) {
                validateMessage.append(messageLoader.getMessage("title.length.invalid"));
            }
        }

        return validateMessage.toString();
    }

    @Override
    public TaskDto getFormData() {
        final TaskDto taskDto = new TaskDto();

        final Optional<String> optionalTitleInputValue = this.titleInputWrapper.getValue();
        optionalTitleInputValue.ifPresent(title -> taskDto.setTitle(title.trim()));

        return taskDto;
    }

    @Override
    public void setFormData(TaskDto taskDto) {
        if (taskDto == null) {
            clear();
            return;
        }
        this.titleInputWrapper.setValue(taskDto.getTitle());
    }
}
