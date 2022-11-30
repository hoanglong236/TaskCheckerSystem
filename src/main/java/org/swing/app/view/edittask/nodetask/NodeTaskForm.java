package org.swing.app.view.edittask.nodetask;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.edittask.TaskFormBase;

import java.awt.Dimension;
import java.time.LocalDateTime;

class NodeTaskForm extends TaskFormBase {

    public NodeTaskForm() {
        super();
        init();
    }

    public NodeTaskForm(TaskDto taskDto) {
        super();
        init(taskDto);
    }

    private void init() {
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

    private void init(TaskDto taskDto) {
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
        this.childComponentSizeMap.clear();

        final int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        final int maxChildComponentWidth = availableWidth - MAIN_LAYOUT.getHgap();
        final int smallInputWrapperHeight = 50;

        this.childComponentSizeMap.put(this.titleInputWrapper,
                new Dimension(maxChildComponentWidth, smallInputWrapperHeight));
        this.childComponentSizeMap.put(this.importantInputWrapper,
                new Dimension(maxChildComponentWidth, smallInputWrapperHeight));
        this.childComponentSizeMap.put(this.startDatetimeInputWrapper,
                new Dimension(maxChildComponentWidth, smallInputWrapperHeight));
        this.childComponentSizeMap.put(this.finishDatetimeInputWrapper,
                new Dimension(maxChildComponentWidth, smallInputWrapperHeight));
        this.childComponentSizeMap.put(this.noteInputWrapper,
                new Dimension(maxChildComponentWidth, smallInputWrapperHeight));
    }

    @Override
    protected void setNotResizableChildComponents() {
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TaskDto getFormData() {
        final String title = (String) this.titleInputWrapper.getValue();
        final boolean important = this.importantInputWrapper.getValue().equals("Yes") ? true : false;
        final LocalDateTime startDatetime = (LocalDateTime) this.startDatetimeInputWrapper.getValue();
        final LocalDateTime finishDatetime = (LocalDateTime) this.finishDatetimeInputWrapper.getValue();
        final String note = (String) this.noteInputWrapper.getValue();

        final TaskDto taskDto = new TaskDto();
        taskDto.setTitle(title);
        taskDto.setImportant(important);
        taskDto.setStartDatetime(startDatetime);
        taskDto.setFinishDatetime(finishDatetime);
        taskDto.setNote(note);

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
