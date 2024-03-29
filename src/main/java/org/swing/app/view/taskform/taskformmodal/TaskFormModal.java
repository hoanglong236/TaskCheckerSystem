package org.swing.app.view.taskform.taskformmodal;

import org.swing.app.dto.TaskDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ComponentSizeConstants;
import org.swing.app.view.common.IconUrlConstants;
import org.swing.app.view.common.LayoutGapConstants;
import org.swing.app.view.common.ReserveSizeConstants;
import org.swing.app.view.components.ViewComponent;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.components.modal.ModalWrapperComponent;
import org.swing.app.view.components.modal.OptionPane;
import org.swing.app.view.components.ui.button.BasicButton;
import org.swing.app.view.taskform.taskformpanel.TaskFormPanel;
import org.swing.app.view.taskform.taskformpanel.factory.TaskFormPanelFactory;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public abstract class TaskFormModal extends ModalWrapperComponent implements ActionListener {

    private static final byte HORIZONTAL_GAP = LayoutGapConstants.FORM_WRAPPER_H_GAP;
    private static final byte VERTICAL_GAP = LayoutGapConstants.FORM_WRAPPER_V_GAP;
    private static final LayoutManager MAIN_LAYOUT = new FlowLayout(FlowLayout.CENTER, HORIZONTAL_GAP, VERTICAL_GAP);

    private TaskFormPanel taskFormPanel;
    private BasicButton submitButton;
    private BasicButton resetButton;
    private BasicButton clearButton;

    private boolean isSubmitted = false;

    public TaskFormModal(ViewComponent windowComponent) {
        super(windowComponent);
        setLayout(MAIN_LAYOUT);
        init();
    }

    protected abstract TaskFormPanelFactory createTaskFormPanelFactory();

    private void initTaskFormPanel() {
        final TaskFormPanelFactory taskFormPanelFactory = createTaskFormPanelFactory();
        this.taskFormPanel = taskFormPanelFactory.createTaskFormPanel();
    }

    private void initSubmitButton() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.submitButton = UIComponentFactory.createBasicButton(messageLoader.getMessage("submit.button.text"));

        this.submitButton.setIcon(IconUrlConstants.SUBMIT_ICON,
                ComponentSizeConstants.SMALL_ICON_WIDTH, ComponentSizeConstants.SMALL_ICON_HEIGHT);
        this.submitButton.addActionListener(this);
    }

    private void initResetButton() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.resetButton = UIComponentFactory.createBasicButton(messageLoader.getMessage("reset.button.text"));

        this.resetButton.setIcon(IconUrlConstants.RESET_ICON,
                ComponentSizeConstants.SMALL_ICON_WIDTH, ComponentSizeConstants.SMALL_ICON_HEIGHT);
        this.resetButton.addActionListener(this);
    }

    private void initClearButton() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.clearButton = UIComponentFactory.createBasicButton(messageLoader.getMessage("clear.button.text"));

        this.clearButton.setIcon(IconUrlConstants.CLEAR_ICON,
                ComponentSizeConstants.SMALL_ICON_WIDTH, ComponentSizeConstants.SMALL_ICON_HEIGHT);
        this.clearButton.addActionListener(this);
    }

    private void init() {
        initTaskFormPanel();
        addChildComponent(this.taskFormPanel);

        initSubmitButton();
        addChildComponent(this.submitButton);

        initResetButton();
        addChildComponent(this.resetButton);

        initClearButton();
        addChildComponent(this.clearButton);
    }

    @Override
    protected void loadChildComponentsSize() {
        final int availableWidth = getSize().width - ReserveSizeConstants.FORM_WRAPPER_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ReserveSizeConstants.FORM_WRAPPER_RESERVE_HEIGHT;

        final int maxChildComponentWidth = availableWidth - HORIZONTAL_GAP;

        final byte controlButtonWidth = 100;
        final int controlButtonHeight = 32;
        this.childComponentSizeMap.put(this.submitButton, new Dimension(controlButtonWidth, controlButtonHeight));
        this.childComponentSizeMap.put(this.resetButton, new Dimension(controlButtonWidth, controlButtonHeight));
        this.childComponentSizeMap.put(this.clearButton, new Dimension(controlButtonWidth, controlButtonHeight));

        final int taskFormPanelHeight = availableHeight - VERTICAL_GAP - controlButtonHeight - VERTICAL_GAP;
        this.childComponentSizeMap.put(this.taskFormPanel, new Dimension(maxChildComponentWidth, taskFormPanelHeight));
    }

    private boolean validateFormData() {
        final String validateMessage = this.taskFormPanel.validate();
        if (validateMessage.isEmpty()) {
            return true;
        }
        OptionPane.showErrorDialog(validateMessage);
        return false;
    }

    private void onActionPerformedForSubmitButton() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        final int result = OptionPane.showConfirmDialog(messageLoader.getMessage("confirm.dialog.question"),
                messageLoader.getMessage("confirm.dialog.add.task.title"));

        if (result != OptionPane.YES_DIALOG_OPTION) {
            return;
        }

        if (validateFormData()) {
            this.isSubmitted = true;
            dispose();
        }
    }

    private void onActionPerformedForResetButton() {
        this.taskFormPanel.reset();
    }

    private void onActionPerformedForClearButton() {
        this.taskFormPanel.clear();
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

    public void setFormData(TaskDto taskDto) {
        this.taskFormPanel.setFormData(taskDto);
    }

    public Optional<TaskDto> getFormData() {
        if (this.isSubmitted) {
            return Optional.of(this.taskFormPanel.getFormData());
        }
        return Optional.empty();
    }
}
