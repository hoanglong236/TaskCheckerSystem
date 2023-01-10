package org.swing.app.view.home.components.taskbase;

import org.swing.app.controller.ControllerResponse;
import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskDto;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.modal.OptionPane;
import org.swing.app.view.components.ui.button.BasicButton;
import org.swing.app.view.components.ui.label.Label;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.home.HomeWrapperComponent;
import org.swing.app.view.home.components.factory.TaskPanelContainerWrapperFactory;
import org.swing.app.view.taskform.factory.TaskFormModalFactory;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;
import java.util.Set;

public abstract class TaskContentPanel extends HomeWrapperComponent implements ActionListener {

    private static final byte HORIZONTAL_GAP = ViewConstant.SMALL_H_GAP;
    private static final byte VERTICAL_GAP = ViewConstant.SMALL_V_GAP;
    private static final LayoutManager MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT, HORIZONTAL_GAP, VERTICAL_GAP);

    private Label masterTitleLabel;
    private TaskPanelContainerWrapper childTaskPanelContainerWrapper;
    private BasicButton addNewChildTaskButton;

    private final TaskPanelContainerWrapperFactory taskPanelContainerWrapperFactory;

    private final TaskFormModalFactory taskFormModalFactory;

    private final String masterTaskPanelDtoId;

    public TaskContentPanel(HomeFrameController homeFrameController,
            TaskPanelContainerWrapperFactory taskPanelContainerWrapperFactory,
            TaskFormModalFactory taskFormModalFactory,
            TaskPanelDto masterTaskPanelDto, Set<TaskPanelDto> childTaskPanelDtos) {

        super(homeFrameController);
        this.taskPanelContainerWrapperFactory = taskPanelContainerWrapperFactory;
        this.taskFormModalFactory = taskFormModalFactory;

        final TaskDto masterTaskDto = masterTaskPanelDto.getTaskDto();
        this.masterTaskPanelDtoId = masterTaskDto.getId();

        setLayout(MAIN_LAYOUT);
        init(masterTaskPanelDto, childTaskPanelDtos);
    }

    private void initMasterTitleLabel(String title) {
        this.masterTitleLabel = UIComponentFactory.createLabel(title);
    }

    private void initChildTaskPanelContainerWrapper(Set<TaskPanelDto> taskPanelDtos) {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        final String taskPanelManagerComponentTitle = messageLoader.getMessage("task.panel.manager.title");

        this.childTaskPanelContainerWrapper = this.taskPanelContainerWrapperFactory.createTaskPanelContainerWrapper(
                this.homeFrameController, taskPanelManagerComponentTitle, taskPanelDtos);
    }

    private void initAddNewChildTaskButton() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.addNewChildTaskButton = UIComponentFactory.createBasicButton(
                messageLoader.getMessage("add.task.component.text"));
        this.addNewChildTaskButton.addActionListener(this);
    }

    private void init(TaskPanelDto masterTaskPanelDto, Set<TaskPanelDto> childTaskPanelDtos) {
        final TaskDto masterTaskDto = masterTaskPanelDto.getTaskDto();

        initMasterTitleLabel(masterTaskDto.getTitle());
        addChildComponent(this.masterTitleLabel);

        initChildTaskPanelContainerWrapper(childTaskPanelDtos);
        addChildComponent(this.childTaskPanelContainerWrapper);

        initAddNewChildTaskButton();
        addChildComponent(this.addNewChildTaskButton);
    }

    @Override
    protected void loadChildComponentsSize() {
        final int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;

        final int maxChildComponentWidth = availableWidth - HORIZONTAL_GAP;

        final byte masterTitleLabelHeight = 100;
        this.childComponentSizeMap.put(this.masterTitleLabel,
                new Dimension(maxChildComponentWidth, masterTitleLabelHeight));

        final int childTaskPanelContainerWrapperHeight = (int) (((float) 0.8 * availableHeight) - VERTICAL_GAP);
        this.childComponentSizeMap.put(this.childTaskPanelContainerWrapper,
                new Dimension(maxChildComponentWidth, childTaskPanelContainerWrapperHeight));

        final int addNewChildTaskButtonWidth = 100;
        final int addNewChildTaskButtonHeight = 50;
        this.childComponentSizeMap.put(this.addNewChildTaskButton,
                new Dimension(addNewChildTaskButtonWidth, addNewChildTaskButtonHeight));
    }

    @Override
    protected void setNotResizableChildComponents() {
        this.masterTitleLabel.setResizable(true);
        this.childTaskPanelContainerWrapper.setResizable(true);
        this.addNewChildTaskButton.setResizable(false);
    }

    private void handleForInsertTaskPanelResponse(ControllerResponse response) {
        final MessageLoader messageLoader = MessageLoader.getInstance();

        if (response.getResponseType() == ControllerResponse.RESPONSE_TYPE_SUCCESS) {
            final Optional<Object> insertedTaskPanelDtoOptional = response.getData(
                    messageLoader.getMessage("inserted.task.panel.dto"));

            if (insertedTaskPanelDtoOptional.isPresent()) {
                this.childTaskPanelContainerWrapper.addTaskPanelByDto(
                        (TaskPanelDto) insertedTaskPanelDtoOptional.get());
                OptionPane.showMessageDialog(messageLoader.getMessage("insert.task.success.dialog"));
                return;
            }

            OptionPane.showMessageDialog(messageLoader.getMessage("insert.task.failure.dialog"));
            return;
        }
        if (response.getResponseType() == ControllerResponse.RESPONSE_TYPE_ERROR) {
            OptionPane.showMessageDialog(messageLoader.getMessage("insert.task.failure.dialog"));
        }
    }

    private void onActionPerformedForAddNewTaskComponent() {
        final Optional<TaskDto> formModalResult = this.taskFormModalFactory.showAddingTaskFormModal(getRootFrame());

        if (!formModalResult.isPresent()) {
            return;
        }

        final TaskDto taskDtoToInsert = formModalResult.get();
        taskDtoToInsert.setParentId(this.masterTaskPanelDtoId);

        final ControllerResponse response = this.homeFrameController.requestInsertTaskPanel(taskDtoToInsert);
        handleForInsertTaskPanelResponse(response);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final Object eventSource = e.getSource();

        if (eventSource == this.addNewChildTaskButton.getSourceComponent()) {
            onActionPerformedForAddNewTaskComponent();
            return;
        }
        throw new IllegalArgumentException();
    }

    public boolean isContentOf(String taskPanelDtoId) {
        return this.masterTaskPanelDtoId.equals(taskPanelDtoId);
    }

    public void updateMaster(TaskPanelDto masterTaskPanelDto) {
        final TaskDto masterTaskDto = masterTaskPanelDto.getTaskDto();
        this.masterTitleLabel.setText(masterTaskDto.getTitle());
    }
}
