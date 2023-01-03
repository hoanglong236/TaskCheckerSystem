package org.swing.app.view.taskform;

import org.swing.app.dto.TaskDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.components.modal.ModalWrapperComponent;
import org.swing.app.view.components.modal.OptionPane;
import org.swing.app.view.components.ui.LabelArea;
import org.swing.app.view.components.ui.button.BasicButton;
import org.swing.app.view.taskform.factory.TaskFormFactory;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class TaskFormModal extends ModalWrapperComponent implements ActionListener {

    private static final byte HORIZONTAL_GAP = ViewConstant.FORM_WRAPPER_H_GAP;
    private static final byte VERTICAL_GAP = ViewConstant.FORM_WRAPPER_V_GAP;
    private static final LayoutManager MAIN_LAYOUT = new FlowLayout(FlowLayout.CENTER, HORIZONTAL_GAP, VERTICAL_GAP);

    private TaskForm taskForm;
    private LabelArea validateMessageArea;
    private BasicButton submitButton;
    private BasicButton resetButton;
    private BasicButton clearButton;

    private final TaskFormFactory taskFormFactory;

    private Optional<TaskDto> formData;

    public TaskFormModal(FrameWrapperComponent parentFrame, TaskFormFactory taskFormFactory) {
        super(parentFrame);
        this.taskFormFactory = taskFormFactory;
        this.formData = Optional.empty();
        setLayout(MAIN_LAYOUT);
        init();
    }

    public TaskFormModal(FrameWrapperComponent parentFrame, TaskFormFactory taskFormFactory, TaskDto taskDto) {
        super(parentFrame);
        this.taskFormFactory = taskFormFactory;
        this.formData = Optional.ofNullable(taskDto);
        setLayout(MAIN_LAYOUT);
        init(taskDto);
    }

    public Optional<TaskDto> getFormData() {
        return formData;
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

    private void saveFormDataBeforeSubmit() {
        final TaskDto taskDto = this.taskForm.getFormData();
        this.formData = Optional.ofNullable(taskDto);
    }

    private boolean validateFormData() {
        final String validateMessage = this.taskForm.validate();

        if (!validateMessage.isEmpty()) {
            this.validateMessageArea.setText(validateMessage);
            return false;
        }

        return true;
    }

    public void reset() {
        if (this.formData.isPresent()) {
            this.taskForm.setFormData(formData.get());
            clearValidateMessageArea();
        } else {
            clear();
        }
    }

    private void clearValidateMessageArea() {
        final String emptyMessage = "";
        this.validateMessageArea.setText(emptyMessage);
    }

    public void clear() {
        this.taskForm.clear();
        clearValidateMessageArea();
    }

    @Override
    protected void loadChildComponentsSize() {
        final int availableWidth = getSize().width - ViewConstant.FORM_WRAPPER_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ViewConstant.FORM_WRAPPER_RESERVE_HEIGHT;

        final int maxChildComponentWidth = availableWidth - HORIZONTAL_GAP;

        final int validateMessageAreaHeight = 80;
        this.childComponentSizeMap.put(this.validateMessageArea,
                new Dimension(maxChildComponentWidth, validateMessageAreaHeight));

        final byte controlButtonWidth = 80;
        final int controlButtonHeight = 30;
        this.childComponentSizeMap.put(this.submitButton, new Dimension(controlButtonWidth, controlButtonHeight));
        this.childComponentSizeMap.put(this.resetButton, new Dimension(controlButtonWidth, controlButtonHeight));
        this.childComponentSizeMap.put(this.clearButton, new Dimension(controlButtonWidth, controlButtonHeight));

        final int taskFormHeight = availableHeight - VERTICAL_GAP - validateMessageAreaHeight
                - VERTICAL_GAP - controlButtonHeight - VERTICAL_GAP;
        this.childComponentSizeMap.put(this.taskForm, new Dimension(maxChildComponentWidth, taskFormHeight));
    }

    @Override
    protected void setNotResizableChildComponents() {
    }

    private void onActionPerformedForSubmitButton() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        final int result = OptionPane.showConfirmDialog(messageLoader.getMessage("confirm.dialog.question"),
                messageLoader.getMessage("confirm.dialog.add.task.title"));

        if (result != OptionPane.YES_DIALOG_OPTION) {
            return;
        }

        if (validateFormData()) {
            saveFormDataBeforeSubmit();
            dispose();
        }
    }

    private void onActionPerformedForResetButton() {
        reset();
    }

    private void onActionPerformedForClearButton() {
        clear();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final Object eventSource = e.getSource();

        if (eventSource == this.submitButton.getSourceComponent()) {
            onActionPerformedForSubmitButton();
            return;
        }
        if (eventSource == this.resetButton.getSourceComponent()) {
            onActionPerformedForResetButton();
            return;
        }
        if (eventSource == this.clearButton.getSourceComponent()) {
            onActionPerformedForClearButton();
            return;
        }
        throw new IllegalArgumentException();
    }
}
