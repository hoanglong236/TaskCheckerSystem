package org.swing.app.view.home.components.taskcontentpanel;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskDto;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.LayoutGapConstants;
import org.swing.app.view.common.ReserveSizeConstants;
import org.swing.app.view.components.modal.OptionPane;
import org.swing.app.view.components.ui.button.BasicButton;
import org.swing.app.view.components.ui.label.Label;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.home.HomeWrapperComponent;
import org.swing.app.view.home.components.listeners.inserttask.InsertTaskActionListener;
import org.swing.app.view.home.components.listeners.loadtaskcontent.LoadTaskContentMouseListener;
import org.swing.app.view.home.components.listeners.inserttask.InsertTaskListenerSubject;
import org.swing.app.view.home.components.listeners.loadtaskcontent.LoadTaskContentListenerSubject;
import org.swing.app.view.home.components.taskpanel.TaskPanel;
import org.swing.app.view.home.components.taskpanel.factory.TaskPanelFactory;
import org.swing.app.view.home.components.TaskPanelContainerWrapper;
import org.swing.app.view.home.observer.taskpanel.modificationevent.TaskPanelModificationEventObserver;
import org.swing.app.view.home.observer.taskpanel.modificationevent.TaskPanelModificationEventSubject;
import org.swing.app.view.taskform.taskformmodal.factory.TaskFormModalFactory;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public abstract class TaskContentPanel extends HomeWrapperComponent implements InsertTaskListenerSubject,
        LoadTaskContentListenerSubject, TaskPanelModificationEventObserver {

    private static final byte HORIZONTAL_GAP = LayoutGapConstants.SMALL_H_GAP;
    private static final byte VERTICAL_GAP = LayoutGapConstants.SMALL_V_GAP;
    private static final LayoutManager MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT, HORIZONTAL_GAP, VERTICAL_GAP);

    private Label masterTitleLabel;
    private TaskPanelContainerWrapper taskPanelContainerWrapper;
    private BasicButton addNewTaskBtn;

    private final TaskFormModalFactory taskFormModalFactory;

    private final TaskPanelFactory taskPanelFactory;

    private TaskPanel activeTaskPanel = null;

    private final Map<Object, TaskPanel> sourceComponentTaskPanelMap = new HashMap<>();

    private final TaskPanelDto masterTaskPanelDto;

    public TaskContentPanel(HomeFrameController homeFrameController,
            TaskFormModalFactory taskFormModalFactory, TaskPanelFactory taskPanelFactory,
            TaskPanelDto masterTaskPanelDto, Set<TaskPanelDto> taskPanelDtos) {

        super(homeFrameController);
        this.taskFormModalFactory = taskFormModalFactory;
        this.taskPanelFactory = taskPanelFactory;
        this.masterTaskPanelDto = masterTaskPanelDto;

        setLayout(MAIN_LAYOUT);
        init(masterTaskPanelDto, taskPanelDtos);
    }

    private String getMasterTaskId() {
        final TaskDto masterTaskDto = this.masterTaskPanelDto.getTaskDto();
        return masterTaskDto.getId();
    }

    private void initMasterTitleLabel(String title) {
        this.masterTitleLabel = UIComponentFactory.createLabel(title);
    }

    private void initTaskPanelContainerWrapper(Set<TaskPanelDto> taskPanelDtos) {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        final String taskPanelManagerComponentTitle = messageLoader.getMessage("task.panel.manager.title");

        this.taskPanelContainerWrapper = new TaskPanelContainerWrapper(
                this.homeFrameController, taskPanelManagerComponentTitle);

        for (final TaskPanelDto taskPanelDto : taskPanelDtos) {
            addTaskPanelByDto(taskPanelDto);
        }
    }

    private void initAddNewTaskBtn() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.addNewTaskBtn = UIComponentFactory.createBasicButton(
                messageLoader.getMessage("add.task.component.text"));

        final ActionListener actionListener = new InsertTaskActionListener(
                this.homeFrameController, this);
        this.addNewTaskBtn.addActionListener(actionListener);
    }

    private void init(TaskPanelDto masterTaskPanelDto, Set<TaskPanelDto> taskPanelDtos) {
        final TaskDto masterTaskDto = masterTaskPanelDto.getTaskDto();

        initMasterTitleLabel(masterTaskDto.getTitle());
        addChildComponent(this.masterTitleLabel);

        initTaskPanelContainerWrapper(taskPanelDtos);
        addChildComponent(this.taskPanelContainerWrapper);

        initAddNewTaskBtn();
        addChildComponent(this.addNewTaskBtn);
    }

    @Override
    protected void loadChildComponentsSize() {
        final int availableWidth = getSize().width - ReserveSizeConstants.SMALL_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ReserveSizeConstants.SMALL_RESERVE_HEIGHT;

        final int maxChildComponentWidth = availableWidth - HORIZONTAL_GAP;

        final byte masterTitleLabelHeight = 100;
        this.childComponentSizeMap.put(this.masterTitleLabel,
                new Dimension(maxChildComponentWidth, masterTitleLabelHeight));

        final int taskPanelContainerWrapperHeight = (int) (((float) 0.8 * availableHeight) - VERTICAL_GAP);
        this.childComponentSizeMap.put(this.taskPanelContainerWrapper,
                new Dimension(maxChildComponentWidth, taskPanelContainerWrapperHeight));

        final int addNewTaskBtnWidth = 100;
        final int addNewTaskBtnHeight = 50;
        this.childComponentSizeMap.put(this.addNewTaskBtn, new Dimension(addNewTaskBtnWidth, addNewTaskBtnHeight));
    }

    @Override
    protected void setNotResizableChildComponents() {
        this.masterTitleLabel.setResizable(true);
        this.taskPanelContainerWrapper.setResizable(true);
        this.addNewTaskBtn.setResizable(false);
    }

    public void updateMasterTask(TaskDto masterTaskDto) {
        this.masterTaskPanelDto.setTaskDto(masterTaskDto);
        this.masterTitleLabel.setText(masterTaskDto.getTitle());
    }

    private void addTaskPanelByDto(TaskPanelDto taskPanelDto) {
        final TaskPanel taskPanel = this.taskPanelFactory.createTaskPanel(this.homeFrameController, taskPanelDto);

        final TaskPanelModificationEventSubject taskPanelModificationEventSubject =
                taskPanel.getTaskPanelModificationEventSubject();
        taskPanelModificationEventSubject.registerObserver(this);

        final MouseListener mouseListener = new LoadTaskContentMouseListener(
                this.homeFrameController, this);
        taskPanel.addMouseListener(mouseListener);

        addTaskPanel(taskPanel);
    }

    private void addTaskPanel(TaskPanel taskPanel) {
        this.taskPanelContainerWrapper.addTaskPanel(taskPanel);
        this.sourceComponentTaskPanelMap.put(taskPanel.getSourceComponent(), taskPanel);
    }

    private void removeTaskPanel(TaskPanel taskPanel) {
        this.taskPanelContainerWrapper.removeTaskPanel(taskPanel);
        this.sourceComponentTaskPanelMap.remove(taskPanel.getSourceComponent());
    }

    @Override
    public Optional<TaskDto> getTaskDtoToInsert(EventObject eventObject) {
        final Optional<TaskDto> formModalResult = this.taskFormModalFactory.showAddingTaskFormModal(getRootFrame());

        if (!formModalResult.isPresent()) {
            return Optional.empty();
        }

        final TaskDto taskDtoToInsert = formModalResult.get();
        taskDtoToInsert.setParentId(getMasterTaskId());

        return Optional.of(taskDtoToInsert);
    }

    @Override
    public void onInsertTaskSuccess(EventObject eventObject, TaskDto insertedTaskDto) {
        final TaskPanelDto insertedTaskPanelDto = new TaskPanelDto();
        insertedTaskPanelDto.setTaskDto(insertedTaskDto);

        addTaskPanelByDto(insertedTaskPanelDto);

        final MessageLoader messageLoader = MessageLoader.getInstance();
        OptionPane.showMessageDialog(messageLoader.getMessage("insert.task.success.dialog"));
    }

    @Override
    public void onInsertTaskFailure(EventObject eventObject) {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        OptionPane.showMessageDialog(messageLoader.getMessage("insert.task.failure.dialog"));
    }

    @Override
    public Optional<String> getTaskIdToLoadContent(EventObject eventObject) {
        final Object eventSource = eventObject.getSource();

        if (this.activeTaskPanel != null && eventSource == this.activeTaskPanel.getSourceComponent()) {
            return Optional.empty();
        }

        if (this.sourceComponentTaskPanelMap.containsKey(eventSource)) {
            final TaskPanel taskPanelToActive = this.sourceComponentTaskPanelMap.get(eventSource);
            return Optional.of(taskPanelToActive.getTaskId());
        }
        return Optional.empty();
    }

    @Override
    public void onLoadTaskContentSuccess(EventObject eventObject, TaskPanelDto masterTaskPanelDto,
            Set<TaskPanelDto> taskPanelDtos) {

        final Object eventSource = eventObject.getSource();

        if (this.sourceComponentTaskPanelMap.containsKey(eventSource)) {
            if (this.activeTaskPanel != null) {
                this.activeTaskPanel.deactivate();
            }
            this.activeTaskPanel = this.sourceComponentTaskPanelMap.get(eventSource);
            this.activeTaskPanel.activate();
            // TODO: handle this
        }
    }

    @Override
    public void onLoadTaskContentFailure(EventObject eventObject) {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        OptionPane.showErrorDialog(messageLoader.getMessage("load.task.panel.content.failure.dialog"));
    }

    @Override
    public void handleUpdateTaskInTaskPanel(TaskPanel taskPanel, TaskDto updatedTaskDto) {
        taskPanel.updateTask(updatedTaskDto);

        if (taskPanel == this.activeTaskPanel) {
            // TODO: handle this
        }
    }

    @Override
    public void handleUpdateTaskCompletionRateInTaskPanel(TaskPanel taskPanel,
            int completedChildTaskCount, int childTaskCount) {

        taskPanel.updateTaskCompletionRate(completedChildTaskCount, childTaskCount);

        removeTaskPanel(taskPanel);
        addTaskPanel(taskPanel);
    }

    @Override
    public void handleDeleteTaskPanel(TaskPanel taskPanel) {
        final TaskPanelModificationEventSubject taskPanelModificationEventSubject =
                taskPanel.getTaskPanelModificationEventSubject();
        taskPanelModificationEventSubject.removeObserver(this);

        taskPanel.cancelAllEventListeners();
        removeTaskPanel(taskPanel);

        if (taskPanel == this.activeTaskPanel) {
            this.activeTaskPanel = null;
            // TODO: handle this
        }
    }
}
