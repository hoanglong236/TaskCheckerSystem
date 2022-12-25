package org.swing.app.view.home.sidebar;

import org.swing.app.controller.ControllerBase;
import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.OptionPane;
import org.swing.app.view.components.ui.Button;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.home.HomeWrapperComponent;
import org.swing.app.view.components.request.InsertableTaskComponent;
import org.swing.app.view.home.components.taskbase.TaskPanelManagerComponent;
import org.swing.app.view.home.components.factory.TaskPanelManagerComponentFactory;
import org.swing.app.view.home.components.roottask.factory.RootTaskPanelManagerComponentFactory;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class HomeSideBar extends HomeWrapperComponent implements ActionListener, InsertableTaskComponent {

    private static final byte HORIZONTAL_GAP = ViewConstant.MEDIUM_H_GAP;
    private static final byte VERTICAL_GAP = ViewConstant.MEDIUM_V_GAP;
    private static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.CENTER, HORIZONTAL_GAP, VERTICAL_GAP);

    private TaskPanelManagerComponent staticTaskPanelManagerComponent;
    private TaskPanelManagerComponent dynamicTaskPanelManagerComponent;
    private Button addNewTaskBtn;
    private final TaskPanelManagerComponentFactory taskPanelManagerFactory;

    public HomeSideBar(HomeFrameController homeFrameController,
            Set<TaskPanelDto> staticTaskPanelDtos, Set<TaskPanelDto> dynamicTaskPanelDtos) {

        super(homeFrameController);
        this.taskPanelManagerFactory = new RootTaskPanelManagerComponentFactory();
        setLayout(MAIN_LAYOUT);
        init(staticTaskPanelDtos, dynamicTaskPanelDtos);
    }

    private void initStaticTaskPanelManagerComponent(Set<TaskPanelDto> taskPanelDtos) {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        final String taskPanelContainerTitle =
                messageLoader.getMessage("task.panel.container.title");

        this.staticTaskPanelManagerComponent = this.taskPanelManagerFactory.createTaskPanelManagerComponent(
                this.homeFrameController, taskPanelContainerTitle, taskPanelDtos);
    }

    private void initDynamicTaskPanelManagerComponent(Set<TaskPanelDto> taskPanelDtos) {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        final String taskPanelContainerTitle =
                messageLoader.getMessage("task.panel.container.title");

        this.dynamicTaskPanelManagerComponent = this.taskPanelManagerFactory.createTaskPanelManagerComponent(
                this.homeFrameController, taskPanelContainerTitle, taskPanelDtos);
    }

    private void initAddNewTaskBtn() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.addNewTaskBtn = UIComponentFactory.createButton(messageLoader.getMessage("add.task.component.text"));
        this.addNewTaskBtn.addActionListener(this);
    }

    private void init(Set<TaskPanelDto> staticTaskPanelDtos, Set<TaskPanelDto> dynamicTaskPanelDtos) {
        initStaticTaskPanelManagerComponent(staticTaskPanelDtos);
        addChildComponent(this.staticTaskPanelManagerComponent);

        initDynamicTaskPanelManagerComponent(dynamicTaskPanelDtos);
        addChildComponent(this.dynamicTaskPanelManagerComponent);

        initAddNewTaskBtn();
        addChildComponent(this.addNewTaskBtn);
    }

    @Override
    protected void loadChildComponentsSize() {
        final int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;

        final int maxChildComponentWidth = availableWidth - HORIZONTAL_GAP;

        final int staticTaskPanelManagerComponentHeight = (int) (((float) 0.2 * availableHeight) - VERTICAL_GAP);
        this.childComponentSizeMap.put(this.staticTaskPanelManagerComponent,
                new Dimension(maxChildComponentWidth, staticTaskPanelManagerComponentHeight));

        final int addNewTaskBtnHeight = 45;
        this.childComponentSizeMap.put(this.addNewTaskBtn, new Dimension(maxChildComponentWidth, addNewTaskBtnHeight));

        final int dynamicTaskPanelManagerComponentHeight = availableHeight
                - VERTICAL_GAP - staticTaskPanelManagerComponentHeight
                - VERTICAL_GAP - addNewTaskBtnHeight - VERTICAL_GAP;
        this.childComponentSizeMap.put(this.dynamicTaskPanelManagerComponent,
                new Dimension(maxChildComponentWidth, dynamicTaskPanelManagerComponentHeight));
    }

    @Override
    protected void setNotResizableChildComponents() {
        this.staticTaskPanelManagerComponent.setResizable(false);
        this.dynamicTaskPanelManagerComponent.setResizable(false);
        this.addNewTaskBtn.setResizable(false);
    }

    private void onActionPerformedForAddNewTaskBtn() {
        final boolean requestSuccess = this.homeFrameController.requestAddNewTaskPanel(
                ControllerBase.ROOT_TASK_TYPE, this);

        if (!requestSuccess) {
            requestFailureHandler();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final Object eventSource = e.getSource();

        if (eventSource == this.addNewTaskBtn) {
            onActionPerformedForAddNewTaskBtn();
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void handlerForResultOfInsertTaskAction(boolean isSuccess, TaskPanelDto taskPanelDto) {
        final MessageLoader messageLoader = MessageLoader.getInstance();

        if (isSuccess) {
            this.dynamicTaskPanelManagerComponent.addTaskPanelByDto(taskPanelDto);
            OptionPane.showMessageDialog(messageLoader.getMessage("insert.success.dialog"));
        } else {
            OptionPane.showMessageDialog(messageLoader.getMessage("insert.failure.dialog"));
        }
    }
}
