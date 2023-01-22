package org.swing.app.view.home;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ComponentSizeConstants;
import org.swing.app.view.common.LayoutGapConstants;
import org.swing.app.view.common.ReserveSizeConstants;
import org.swing.app.view.common.ViewConstants;
import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.home.body.HomeBodyPanel;
import org.swing.app.view.home.observer.taskcontent.MainTaskContentEventSubject;
import org.swing.app.view.home.observer.taskcontent.TaskContentEventSubject;
import org.swing.app.view.home.sidebar.HomeSideBar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.Set;

public class HomeFrame extends FrameWrapperComponent  {

    private static final byte HORIZONTAL_GAP = LayoutGapConstants.LARGE_H_GAP;
    private static final byte VERTICAL_GAP = LayoutGapConstants.LARGE_V_GAP;
    private static final LayoutManager MAIN_LAYOUT = new FlowLayout(FlowLayout.CENTER, HORIZONTAL_GAP, VERTICAL_GAP);

    private HomeSideBar sideBar;
    private HomeBodyPanel bodyPanel;

    private final HomeFrameController homeFrameController;

    public HomeFrame(HomeFrameController homeFrameController, Set<TaskPanelDto> taskPanelDtos) {
        super();
        this.homeFrameController = homeFrameController;
        setLayout(MAIN_LAYOUT);
        setBackgroundColor(ViewConstants.PRIMARY_BACKGROUND_COLOR);
        init(taskPanelDtos);

        final MessageLoader messageLoader = MessageLoader.getInstance();
        setFrameTitle(messageLoader.getMessage("home.frame.title"));
    }

    private void initSideBar(Set<TaskPanelDto> taskPanelDtos) {
        this.sideBar = new HomeSideBar(this.homeFrameController, taskPanelDtos);
        this.sideBar.setBackgroundColor(Color.cyan);
        this.sideBar.setOpaque(true);
    }

    private void initBodyPanel() {
        this.bodyPanel = new HomeBodyPanel(this.homeFrameController);
    }

    private void init(Set<TaskPanelDto> taskPanelDtos) {
        initSideBar(taskPanelDtos);
        addChildComponent(this.sideBar);

        initBodyPanel();
        addChildComponent(this.bodyPanel);

        final TaskContentEventSubject taskContentEventSubject = new MainTaskContentEventSubject();
        taskContentEventSubject.registerObserver(this.bodyPanel);

        this.sideBar.setTaskContentEventSubject(taskContentEventSubject);
    }

    @Override
    protected void loadChildComponentsSize() {
        final int availableWidth = getSize().width - ReserveSizeConstants.FRAME_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ReserveSizeConstants.FRAME_RESERVE_HEIGHT;

        final int maxChildComponentHeight = availableHeight - VERTICAL_GAP;

        final int sideBarWidth = ComponentSizeConstants.SIDEBAR_WIDTH;
        this.childComponentSizeMap.put(this.sideBar, new Dimension(sideBarWidth, maxChildComponentHeight));

        final int bodyPanelWidth = availableWidth - HORIZONTAL_GAP - sideBarWidth - HORIZONTAL_GAP;
        this.childComponentSizeMap.put(this.bodyPanel, new Dimension(bodyPanelWidth, maxChildComponentHeight));
    }

    @Override
    protected void setNotResizableChildComponents() {
        this.sideBar.setResizable(true);
        this.bodyPanel.setResizable(true);
    }
}