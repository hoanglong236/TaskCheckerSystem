package org.swing.app.view.taskform.roottask;

import org.swing.app.dto.TaskDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.taskform.TaskForm;

import java.awt.Dimension;

public class RootTaskForm extends TaskForm {

    public RootTaskForm() {
        super();
    }

    public RootTaskForm(TaskDto taskDto) {
        super(taskDto);
    }

    @Override
    protected void init() {
        initTitleInputWrapper();
        addChildComponent(this.titleInputWrapper);

        initStartDatetimeInputWrapper();
        addChildComponent(this.startDatetimeInputWrapper);

        initFinishDatetimeInputWrapper();
        addChildComponent(this.finishDatetimeInputWrapper);
    }

    @Override
    protected void init(TaskDto taskDto) {
        initTitleInputWrapper(taskDto.getTitle());
        addChildComponent(this.titleInputWrapper);

        initStartDatetimeInputWrapper(taskDto.getStartDatetime());
        addChildComponent(this.startDatetimeInputWrapper);

        initFinishDatetimeInputWrapper(taskDto.getFinishDatetime());
        addChildComponent(this.finishDatetimeInputWrapper);
    }

    @Override
    protected void loadChildComponentsSize() {
        final int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        final int maxChildComponentWidth = availableWidth - HORIZONTAL_GAP;
        final int smallInputWrapperHeight = 50;

        this.childComponentSizeMap.put(this.titleInputWrapper,
                new Dimension(maxChildComponentWidth, smallInputWrapperHeight));
        this.childComponentSizeMap.put(this.startDatetimeInputWrapper,
                new Dimension(maxChildComponentWidth, smallInputWrapperHeight));
        this.childComponentSizeMap.put(this.finishDatetimeInputWrapper,
                new Dimension(maxChildComponentWidth, smallInputWrapperHeight));
    }

    @Override
    protected void setNotResizableChildComponents() {
    }

    @Override
    public String validate() {
        final StringBuilder validateMessage = new StringBuilder();
        final MessageLoader messageLoader = MessageLoader.getInstance();

//        final String emptyText = "";
//        final String title = (String) this.titleInputWrapper.getValue();
//
//        if (emptyText.equals(title)) {
//            return messageLoader.getMessage("title.valid");
//        }
//
//        final LocalDateTime startDatetime = (LocalDateTime) this.startDatetimeInputWrapper.getValue();
//        final LocalDateTime finishDatetime = (LocalDateTime) this.finishDatetimeInputWrapper.getValue();
//
//        if (startDatetime != null )
//        if (!finishDatetime.isAfter(startDatetime)) {
//            validateMessage.append(messageLoader.getMessage("finish.datetime.valid"));
//            validateMessage.append("\n");
//        }

        return validateMessage.toString();
    }

    @Override
    public TaskDto getFormData() {
//        final String title = (String) this.titleInputWrapper.getValue();
//        final LocalDateTime startDatetime = (LocalDateTime) this.startDatetimeInputWrapper.getValue();
//        final LocalDateTime finishDatetime = (LocalDateTime) this.finishDatetimeInputWrapper.getValue();

        final TaskDto taskDto = new TaskDto();
//        taskDto.setTitle(title);
//        taskDto.setStartDatetime(startDatetime);
//        taskDto.setFinishDatetime(finishDatetime);

        return taskDto;
    }

    @Override
    public void setFormData(TaskDto taskDto) {
        if (taskDto == null) {
            clear();
            return;
        }
        this.titleInputWrapper.setValue(taskDto.getTitle());
        this.startDatetimeInputWrapper.setValue(taskDto.getStartDatetime());
        this.finishDatetimeInputWrapper.setValue(taskDto.getFinishDatetime());
    }
}
