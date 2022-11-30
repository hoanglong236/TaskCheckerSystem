package org.swing.app.view.edittask.roottask;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.edittask.TaskFormBase;

import java.awt.Dimension;
import java.time.LocalDateTime;

public class RootTaskForm extends TaskFormBase {

    public RootTaskForm() {
        super();
        init();
    }

    public RootTaskForm(TaskDto taskDto) {
        super();
        init(taskDto);
    }

    private void init() {
        initTitleInputWrapper();
        addChildComponent(this.titleInputWrapper);

        initStartDatetimeInputWrapper();
        addChildComponent(this.startDatetimeInputWrapper);

        initFinishDatetimeInputWrapper();
        addChildComponent(this.finishDatetimeInputWrapper);
    }

    private void init(TaskDto taskDto) {
        initTitleInputWrapper(taskDto.getTitle());
        addChildComponent(this.titleInputWrapper);

        initStartDatetimeInputWrapper(taskDto.getStartDatetime());
        addChildComponent(this.startDatetimeInputWrapper);

        initFinishDatetimeInputWrapper(taskDto.getFinishDatetime());
        addChildComponent(this.finishDatetimeInputWrapper);
    }

    @Override
    protected void loadChildComponentsSize() {
        this.childComponentSizeMap.clear();

        final int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        final int maxChildComponentWidth = availableWidth - MAIN_LAYOUT.getHgap();
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
    public boolean validate() {
        return false;
    }

    @Override
    public TaskDto getFormData() {
        final String title = (String) this.titleInputWrapper.getValue();
        final LocalDateTime startDatetime = (LocalDateTime) this.startDatetimeInputWrapper.getValue();
        final LocalDateTime finishDatetime = (LocalDateTime) this.finishDatetimeInputWrapper.getValue();

        final TaskDto taskDto = new TaskDto();
        taskDto.setTitle(title);
        taskDto.setStartDatetime(startDatetime);
        taskDto.setFinishDatetime(finishDatetime);

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
