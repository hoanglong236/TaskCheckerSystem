package org.swing.app.view.edittask;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.FrameWrapperComponent;

import java.awt.Dimension;
import java.awt.FlowLayout;

public abstract class TaskFormFrame extends FrameWrapperComponent {

    private static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT,
            ViewConstant.LARGE_H_GAP, ViewConstant.LARGE_V_GAP);

    private TaskFormBase taskForm;
    private final TaskFormFactory taskFormFactory;

    private TaskDto taskDto = null;

    public TaskFormFrame(TaskFormFactory taskFormFactory) {
        super();
        this.taskFormFactory = taskFormFactory;
        setLayout(MAIN_LAYOUT);
        init();
    }

    public TaskFormFrame(TaskFormFactory taskFormFactory, TaskDto taskDto) {
        super();
        this.taskFormFactory = taskFormFactory;
        setLayout(MAIN_LAYOUT);
        init(taskDto);
    }

    private void initTaskForm() {
        this.taskForm = this.taskFormFactory.createTaskForm();
    }

    private void initTaskForm(TaskDto taskDto) {
        this.taskForm = this.taskFormFactory.createTaskForm(taskDto);
    }

    private void init() {
        initTaskForm();
        addChildComponent(this.taskForm);
    }

    private void init(TaskDto taskDto) {
        initTaskForm(taskDto);
        addChildComponent(this.taskForm);
    }

    public void setFormData(TaskDto taskDto) {
        this.taskForm.setFormData(taskDto);
    }

    public TaskDto getFormData() {
        return this.taskForm.getFormData();
    }

    public void reset() {
        setFormData(this.taskDto);
    }

    public void clear() {
        this.taskForm.clear();
    }

    @Override
    protected void loadChildComponentsSize() {
        this.childComponentSizeMap.clear();

        final int availableWidth = getSize().width - ViewConstant.FRAME_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ViewConstant.FRAME_RESERVE_HEIGHT;

        final int maxChildComponentWidth = availableWidth - MAIN_LAYOUT.getHgap();
        final int maxChildComponentHeight = availableHeight - MAIN_LAYOUT.getVgap();

        this.childComponentSizeMap.put(this.taskForm, new Dimension(maxChildComponentWidth, maxChildComponentHeight));
    }

    @Override
    protected void setNotResizableChildComponents() {
    }
}
