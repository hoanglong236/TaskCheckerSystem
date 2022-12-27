package org.swing.app.view.taskform;

import org.swing.app.controller.TaskFormFrameController;
import org.swing.app.dto.TaskDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.components.OptionPane;
import org.swing.app.view.components.ui.button.BasicButton;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.components.ui.LabelArea;
import org.swing.app.view.taskform.factory.TaskFormFactory;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskFormFrame extends FrameWrapperComponent implements ActionListener {

    private static final byte HORIZONTAL_GAP = ViewConstant.FORM_FRAME_H_GAP;
    private static final byte VERTICAL_GAP = ViewConstant.FORM_FRAME_V_GAP;
    private static final LayoutManager MAIN_LAYOUT = new FlowLayout(FlowLayout.CENTER, HORIZONTAL_GAP, VERTICAL_GAP);

    private TaskForm taskForm;
    private LabelArea validateMessageArea;
    private BasicButton submitButton;
    private BasicButton resetButton;
    private BasicButton clearButton;

    private final TaskFormFactory taskFormFactory;

    private final TaskFormFrameController taskFormFrameController;

    private final boolean isAddingTask;

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

    protected void initValidateMessageArea(String message) {
        this.validateMessageArea = UIComponentFactory.createLabelArea(message);
    }

    private void initSubmitButton() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.submitButton = UIComponentFactory.createBasicButton(messageLoader.getMessage("submit.button.text"));
        this.submitButton.addActionListener(this);
    }

    private void initResetButton() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.resetButton = UIComponentFactory.createBasicButton(messageLoader.getMessage("reset.button.text"));
        this.resetButton.addActionListener(this);
    }

    private void initClearButton() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.clearButton = UIComponentFactory.createBasicButton(messageLoader.getMessage("clear.button.text"));
        this.clearButton.addActionListener(this);
    }

    private void init() {
        initTaskForm();
        addChildComponent(this.taskForm);

        final String emptyMessage = "";
        initValidateMessageArea(emptyMessage);
        addChildComponent(this.validateMessageArea);

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

        final String emptyMessage = "";
        initValidateMessageArea(emptyMessage);
        addChildComponent(this.validateMessageArea);

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

    private void showValidateMessage(String validateMessage) {
        this.validateMessageArea.setText(validateMessage);
    }

    public void submit() {
        final String validateMessage = this.taskForm.validate();

        if (!validateMessage.isEmpty()) {
            showValidateMessage(validateMessage);
            return;
        }

        if (this.isAddingTask) {
            this.taskFormFrameController.addNewTaskByDto(getFormData());
        } else {
            this.taskFormFrameController.updateTaskByDto(getFormData());
        }
    }

    public void reset() {
        setFormData(this.taskDto);
    }

    public void clear() {
        this.taskForm.clear();

        final String emptyMessage = "";
        this.validateMessageArea.setText(emptyMessage);
    }

    @Override
    protected void loadChildComponentsSize() {
        final int availableWidth = getSize().width - ViewConstant.FORM_FRAME_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ViewConstant.FORM_FRAME_RESERVE_HEIGHT;

        final int maxChildComponentWidth = availableWidth - HORIZONTAL_GAP;

        final int taskFormHeight = (int) ((float) 0.75 * availableHeight);
        this.childComponentSizeMap.put(this.taskForm, new Dimension(maxChildComponentWidth, taskFormHeight));

        final byte controlButtonWidth = 80;
        final int controlButtonHeight = availableHeight - VERTICAL_GAP - taskFormHeight - VERTICAL_GAP;
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

        if (eventSource == this.submitButton.getSourceComponent()) {
            final MessageLoader messageLoader = MessageLoader.getInstance();
            final int result = OptionPane.showConfirmDialog(messageLoader.getMessage("confirm.dialog.question"),
                    messageLoader.getMessage("confirm.dialog.add.task.title"));

            if (result == OptionPane.YES_DIALOG_OPTION) {
                submit();
            }
            return;
        }
        if (eventSource == this.resetButton.getSourceComponent()) {
            reset();
            return;
        }
        if (eventSource == this.clearButton.getSourceComponent()) {
            clear();
            return;
        }
        throw new IllegalArgumentException();
    }
}
