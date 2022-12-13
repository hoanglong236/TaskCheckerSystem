package org.swing.app.view.taskform;

import org.swing.app.controller.TaskFormFrameController;
import org.swing.app.dto.TaskDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.components.OptionPane;
import org.swing.app.view.components.ui.Button;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.taskform.factory.TaskFormFactory;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskFormFrame extends FrameWrapperComponent implements ActionListener {

    private static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.CENTER,
            ViewConstant.FORM_FRAME_H_GAP, ViewConstant.FORM_FRAME_V_GAP);

    private TaskForm taskForm;
    private Button submitButton;
    private Button resetButton;
    private Button clearButton;

    private final TaskFormFactory taskFormFactory;

    private TaskFormFrameController taskFormFrameController;

    private boolean isAddingTask;

    private TaskDto taskDto = null;

    public TaskFormFrame(TaskFormFrameController taskFormFrameController, TaskFormFactory taskFormFactory) {
        super();
        this.taskFormFrameController = taskFormFrameController;
        this.taskFormFactory = taskFormFactory;
        this.isAddingTask = true;
        setLayout(MAIN_LAYOUT);
        init();
    }

    public TaskFormFrame(TaskFormFrameController taskFormFrameController,
            TaskFormFactory taskFormFactory, TaskDto taskDto) {

        super();
        this.taskFormFrameController = taskFormFrameController;
        this.taskFormFactory = taskFormFactory;
        this.isAddingTask = false;
        setLayout(MAIN_LAYOUT);
        init(taskDto);
    }

    private void initTaskForm() {
        this.taskForm = this.taskFormFactory.createTaskForm();
    }

    private void initTaskForm(TaskDto taskDto) {
        this.taskForm = this.taskFormFactory.createTaskForm(taskDto);
    }

    private void initSubmitButton() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.submitButton = UIComponentFactory.createButton(messageLoader.getMessage("submit.button.text"));
        this.submitButton.addActionListener(this);
    }

    private void initResetButton() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.resetButton = UIComponentFactory.createButton(messageLoader.getMessage("reset.button.text"));
        this.resetButton.addActionListener(this);
    }

    private void initClearButton() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.clearButton = UIComponentFactory.createButton(messageLoader.getMessage("clear.button.text"));
        this.clearButton.addActionListener(this);
    }

    private void init() {
        initTaskForm();
        addChildComponent(this.taskForm);

        initSubmitButton();
        addChildComponent(this.submitButton);

        initResetButton();
        addChildComponent(this.resetButton);

        initClearButton();
        addChildComponent(this.clearButton);
    }

    private void init(TaskDto taskDto) {
        initTaskForm(taskDto);
        addChildComponent(this.taskForm);

        initSubmitButton();
        addChildComponent(this.submitButton);

        initResetButton();
        addChildComponent(this.resetButton);

        initClearButton();
        addChildComponent(this.clearButton);
    }

    public void setFormData(TaskDto taskDto) {
        this.taskForm.setFormData(taskDto);
    }

    public TaskDto getFormData() {
        return this.taskForm.getFormData();
    }

    public void submit() {
        // TODO: handle validate

        if (this.isAddingTask) {
            this.taskFormFrameController.insertTaskByDto(getFormData());
        } else {
            this.taskFormFrameController.updateTaskByDto(getFormData());
        }
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

        final int availableWidth = getSize().width - ViewConstant.FORM_FRAME_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ViewConstant.FORM_FRAME_RESERVE_HEIGHT;

        final int maxChildComponentWidth = availableWidth - MAIN_LAYOUT.getHgap();

        final int taskFormHeight = (int) ((float) 0.75 * availableHeight);
        this.childComponentSizeMap.put(this.taskForm, new Dimension(maxChildComponentWidth, taskFormHeight));

        final byte controlButtonWidth = 80;
        final int controlButtonHeight = availableHeight - MAIN_LAYOUT.getVgap()
                - taskFormHeight - MAIN_LAYOUT.getVgap();
        this.childComponentSizeMap.put(this.submitButton, new Dimension(controlButtonWidth, controlButtonHeight));
        this.childComponentSizeMap.put(this.resetButton, new Dimension(controlButtonWidth, controlButtonHeight));
        this.childComponentSizeMap.put(this.clearButton, new Dimension(controlButtonWidth, controlButtonHeight));
    }

    @Override
    protected void setNotResizableChildComponents() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final Object eventSource = e.getSource();

        if (eventSource == this.submitButton.getComponent()) {
            final MessageLoader messageLoader = MessageLoader.getInstance();
            final int result = OptionPane.showConfirmDialog(messageLoader.getMessage("confirm.dialog.question"),
                    messageLoader.getMessage("confirm.dialog.add.task.title"));

            if (result == OptionPane.YES_DIALOG_OPTION) {
                submit();
            }
            return;
        }
        if (eventSource == this.resetButton.getComponent()) {
            reset();
            return;
        }
        if (eventSource == this.clearButton.getComponent()) {
            clear();
            return;
        }
    }
}
