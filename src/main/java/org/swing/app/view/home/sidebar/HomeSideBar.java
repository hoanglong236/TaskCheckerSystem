package org.swing.app.view.home.sidebar;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskDto;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.modal.OptionPane;
import org.swing.app.view.components.ui.button.BasicButton;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.home.HomeWrapperComponent;
import org.swing.app.view.home.components.listeners.inserttask.InsertTaskActionListener;
import org.swing.app.view.home.components.listeners.loadtaskcontent.LoadTaskContentMouseListener;
import org.swing.app.view.home.components.listeners.inserttask.InsertTaskListenerSubject;
import org.swing.app.view.home.components.listeners.loadtaskcontent.LoadTaskContentListenerSubject;
import org.swing.app.view.home.components.taskpanel.TaskPanel;
import org.swing.app.view.home.components.taskpanel.factory.TaskPanelFactory;
import org.swing.app.view.home.components.taskpanel.factory.impl.RootTaskPanelFactory;
import org.swing.app.view.home.components.TaskPanelContainerWrapper;
import org.swing.app.view.home.observer.taskpanel.modificationevent.TaskPanelModificationEventObserver;
import org.swing.app.view.home.observer.taskpanel.modificationevent.TaskPanelModificationEventSubject;
import org.swing.app.view.taskform.taskformmodal.factory.TaskFormModalFactory;
import org.swing.app.view.taskform.taskformmodal.factory.impl.RootTaskFormModalFactory;

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

public class HomeSideBar extends HomeWrapperComponent implements InsertTaskListenerSubject,
        LoadTaskContentListenerSubject, TaskPanelModificationEventObserver {

    private static final byte HORIZONTAL_GAP = ViewConstant.MEDIUM_H_GAP;
    private static final byte VERTICAL_GAP = ViewConstant.MEDIUM_V_GAP;
    private static final LayoutManager MAIN_LAYOUT = new FlowLayout(FlowLayout.CENTER, HORIZONTAL_GAP, VERTICAL_GAP);

    private TaskPanelContainerWrapper taskPanelContainerWrapper;
    private BasicButton addNewTaskBtn;

    private final TaskPanelFactory taskPanelFactory;

    private final Map<Object, TaskPanel> sourceComponentTaskPanelMap = new HashMap<>();

    private TaskPanel activeTaskPanel = null;

    public HomeSideBar(HomeFrameController homeFrameController, Set<TaskPanelDto> taskPanelDtos) {
        super(homeFrameController);
        this.taskPanelFactory = new RootTaskPanelFactory();

        setLayout(MAIN_LAYOUT);
        init(taskPanelDtos);
    }

    private void initTaskPanelContainerWrapper(Set<TaskPanelDto> taskPanelDtos) {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        final String taskPanelContainerTitle = messageLoader.getMessage("task.panel.container.title");

        this.taskPanelContainerWrapper = new TaskPanelContainerWrapper(
                this.homeFrameController, taskPanelContainerTitle);

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

    private void init(Set<TaskPanelDto> taskPanelDtos) {
        initTaskPanelContainerWrapper(taskPanelDtos);
        addChildComponent(this.taskPanelContainerWrapper);

        initAddNewTaskBtn();
        addChildComponent(this.addNewTaskBtn);
    }

    @Override
    protected void loadChildComponentsSize() {
        final int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;

        final int maxChildComponentWidth = availableWidth - HORIZONTAL_GAP;

        final int addNewTaskBtnHeight = 45;
        this.childComponentSizeMap.put(this.addNewTaskBtn, new Dimension(maxChildComponentWidth, addNewTaskBtnHeight));

        final int taskPanelContainerWrapperHeight = availableHeight
                - VERTICAL_GAP - addNewTaskBtnHeight - VERTICAL_GAP;
        this.childComponentSizeMap.put(this.taskPanelContainerWrapper,
                new Dimension(maxChildComponentWidth, taskPanelContainerWrapperHeight));
    }

    @Override
    protected void setNotResizableChildComponents() {
        this.taskPanelContainerWrapper.setResizable(false);
        this.addNewTaskBtn.setResizable(false);
    }

    private void addTaskPanelByDto(TaskPanelDto taskPanelDto) {
        final TaskPanel taskPanel = this.taskPanelFactory.createTaskPanel(homeFrameController, taskPanelDto);

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
        final TaskFormModalFactory taskFormModalFactory = new RootTaskFormModalFactory();
        return taskFormModalFactory.showAddingTaskFormModal(getRootFrame());
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
