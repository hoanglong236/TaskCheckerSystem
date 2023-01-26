package org.swing.app.view.home.components.taskcontentpanel;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskDto;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ComponentSizeConstants;
import org.swing.app.view.common.IconUrlConstants;
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
import org.swing.app.view.home.observer.taskcompletionrate.TaskCompletionRateEventObserver;
import org.swing.app.view.home.observer.taskcompletionrate.TaskCompletionRateEventSubject;
import org.swing.app.view.home.observer.taskcontent.EmptyTaskContentEventSubject;
import org.swing.app.view.home.observer.taskcontent.TaskContentEventSubject;
import org.swing.app.view.home.observer.taskpanel.modification.TaskPanelModificationEventObserver;
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
        LoadTaskContentListenerSubject, TaskPanelModificationEventObserver, TaskCompletionRateEventObserver {

    private static final byte HORIZONTAL_GAP = LayoutGapConstants.SMALL_H_GAP;
    private static final byte VERTICAL_GAP = LayoutGapConstants.SMALL_V_GAP;
    private static final LayoutManager MAIN_LAYOUT = new FlowLayout(FlowLayout.RIGHT, HORIZONTAL_GAP, VERTICAL_GAP);

    private Label masterTitleLabel;
    private TaskPanelContainerWrapper taskPanelContainerWrapper;
    private BasicButton addNewTaskBtn;

    private TaskPanel activeTaskPanel = null;

    private final Map<Object, TaskPanel> sourceComponentTaskPanelMap = new HashMap<>();

    private final TaskCompletionRateEventSubject masterTaskCompletionRateEventSubject;

    private TaskContentEventSubject taskContentEventSubject = new EmptyTaskContentEventSubject();

    private final TaskPanelDto masterTaskPanelDto;

    public TaskContentPanel(HomeFrameController homeFrameController,
            TaskPanelDto masterTaskPanelDto, Set<TaskPanelDto> taskPanelDtos,
            TaskCompletionRateEventSubject masterTaskCompletionRateEventSubject) {

        super(homeFrameController);
        this.masterTaskPanelDto = masterTaskPanelDto;
        this.masterTaskCompletionRateEventSubject = masterTaskCompletionRateEventSubject;

        setLayout(MAIN_LAYOUT);
        init(masterTaskPanelDto, taskPanelDtos);
    }

    public void setTaskContentEventSubject(TaskContentEventSubject taskContentEventSubject) {
        this.taskContentEventSubject = taskContentEventSubject;
    }

    public TaskContentEventSubject getTaskContentEventSubject() {
        return taskContentEventSubject;
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
        final String title = messageLoader.getMessage("task.panel.wrapper.title");

        this.taskPanelContainerWrapper = new TaskPanelContainerWrapper(this.homeFrameController, title);

        for (final TaskPanelDto taskPanelDto : taskPanelDtos) {
            final TaskPanel taskPanel = createTaskPanelByDto(taskPanelDto);
            addTaskPanel(taskPanel);
        }
    }

    private void initAddNewTaskBtn() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.addNewTaskBtn = UIComponentFactory.createBasicButton(
                messageLoader.getMessage("add.task.component.text"));
        this.addNewTaskBtn.setIcon(IconUrlConstants.NEW_TASK_ICON,
                ComponentSizeConstants.DEFAULT_ICON_WIDTH, ComponentSizeConstants.DEFAULT_ICON_HEIGHT);

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

        final int addNewTaskBtnWidth = 130;
        final int addNewTaskBtnHeight = 40;
        this.childComponentSizeMap.put(this.addNewTaskBtn, new Dimension(addNewTaskBtnWidth, addNewTaskBtnHeight));

        final int taskPanelContainerWrapperHeight = availableHeight - VERTICAL_GAP - masterTitleLabelHeight
                - VERTICAL_GAP - addNewTaskBtnHeight - VERTICAL_GAP;
        this.childComponentSizeMap.put(this.taskPanelContainerWrapper,
                new Dimension(maxChildComponentWidth, taskPanelContainerWrapperHeight));
    }

    public void updateMasterTask(TaskDto masterTaskDto) {
        this.masterTaskPanelDto.setTaskDto(masterTaskDto);
        this.masterTitleLabel.setText(masterTaskDto.getTitle());
    }

    protected abstract TaskPanelFactory createTaskPanelFactory();

    private TaskPanel createTaskPanelByDto(TaskPanelDto taskPanelDto) {
        final TaskPanelFactory taskPanelFactory = createTaskPanelFactory();

        final TaskPanel taskPanel = taskPanelFactory.createTaskPanel(this.homeFrameController, taskPanelDto);
        taskPanel.registerModificationEventObserver(this);

        final MouseListener mouseListener = new LoadTaskContentMouseListener(
                this.homeFrameController, this);
        taskPanel.addMouseListener(mouseListener);

        return taskPanel;
    }

    private void addTaskPanel(TaskPanel taskPanel) {
        this.taskPanelContainerWrapper.addTaskPanel(taskPanel);
        this.sourceComponentTaskPanelMap.put(taskPanel.getSourceComponent(), taskPanel);
    }

    private void removeTaskPanel(TaskPanel taskPanel) {
        this.taskPanelContainerWrapper.removeTaskPanel(taskPanel);
        this.sourceComponentTaskPanelMap.remove(taskPanel.getSourceComponent());
    }

    protected abstract TaskFormModalFactory createTaskFormModalFactory();

    @Override
    public Optional<TaskDto> getTaskDtoToInsert(EventObject eventObject) {
        final TaskFormModalFactory taskFormModalFactory = createTaskFormModalFactory();
        final Optional<TaskDto> formModalResult = taskFormModalFactory.showAddingTaskFormModal(getWindowComponent());

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

        final TaskPanel taskPanel = createTaskPanelByDto(insertedTaskPanelDto);
        addTaskPanel(taskPanel);

        // When this method is called, it means that all the old task panels in taskPanelContainerWrapper are resized.
        // The task panel has just been added has not been resized. So we need to resize it
        this.taskPanelContainerWrapper.resizeTaskPanelInContainer(taskPanel);

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
            final TaskDto taskDto = taskPanelToActive.getTaskDto();

            return Optional.of(taskDto.getId());
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

            final TaskCompletionRateEventSubject taskCompletionRateEventSubject = new TaskCompletionRateEventSubject();
            taskCompletionRateEventSubject.registerObserver(this);

            this.taskContentEventSubject.notifyObserversToLoadContent(masterTaskPanelDto, taskPanelDtos,
                    taskCompletionRateEventSubject);
        }
    }

    @Override
    public void onLoadTaskContentFailure(EventObject eventObject) {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        OptionPane.showErrorDialog(messageLoader.getMessage("load.task.panel.content.failure.dialog"));
    }

    @Override
    public void handleUpdateTaskInTaskPanelEvent(TaskPanel taskPanel, TaskDto newTaskDto) {
        final TaskDto oldTaskDto = taskPanel.getTaskDto();

        final boolean oldTaskCompleted = oldTaskDto.isCompleted();
        final boolean newTaskCompleted = newTaskDto.isCompleted();

        if (oldTaskCompleted != newTaskCompleted) {
            removeTaskPanel(taskPanel);
            addTaskPanel(taskPanel);

            final int completedChildTaskCountOfMaster = this.masterTaskPanelDto.getCompletedChildTaskCount();
            final int childTaskCountOfMaster = this.masterTaskPanelDto.getChildTaskCount();

            if (newTaskCompleted) {
                this.masterTaskCompletionRateEventSubject.notifyObserversToUpdateCompletionRate(
                        completedChildTaskCountOfMaster + 1, childTaskCountOfMaster);
            } else {
                this.masterTaskCompletionRateEventSubject.notifyObserversToUpdateCompletionRate(
                        completedChildTaskCountOfMaster - 1, childTaskCountOfMaster);
            }
        }
    }

    @Override
    public void handleDeleteTaskPanelEvent(TaskPanel taskPanel) {
        taskPanel.removeModificationEventObserver(this);
        taskPanel.cancelAllEventListeners();
        removeTaskPanel(taskPanel);

        if (taskPanel == this.activeTaskPanel) {
            this.activeTaskPanel = null;
        }

        final TaskDto taskDto = taskPanel.getTaskDto();
        final boolean isTaskCompleted = taskDto.isCompleted();

        final int completedChildTaskCountOfMaster = this.masterTaskPanelDto.getCompletedChildTaskCount();
        final int childTaskCountOfMaster = this.masterTaskPanelDto.getChildTaskCount();

        if (isTaskCompleted) {
            this.masterTaskCompletionRateEventSubject.notifyObserversToUpdateCompletionRate(
                    completedChildTaskCountOfMaster - 1, childTaskCountOfMaster - 1);
        } else {
            this.masterTaskCompletionRateEventSubject.notifyObserversToUpdateCompletionRate(
                    completedChildTaskCountOfMaster, childTaskCountOfMaster - 1);
        }
    }

    @Override
    public void handleUpdateCompletionRate(int completedChildTaskCount, int childTaskCount) {
        this.activeTaskPanel.updateTaskCompletionRate(completedChildTaskCount, childTaskCount);
    }
}
