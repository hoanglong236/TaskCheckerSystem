package org.swing.app.view.home;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.WrapperComponent;
import org.swing.app.view.home.body.HomeBodyPanel;
import org.swing.app.view.home.sidebar.HomeSideBar;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Set;

public class HomeFrame extends WrapperComponent {

    private static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT,
            ViewConstant.LARGE_H_GAP, ViewConstant.LARGE_V_GAP);

    private HomeSideBar sideBar;
    private HomeBodyPanel bodyPanel;

    public HomeFrame(Set<TaskPanelDto> repeatTaskPanelDtos, Set<TaskPanelDto> nonRepeatTaskPanelDtos) {
        super();
        this.component = new JFrame();
        setLayout(MAIN_LAYOUT);
        init(repeatTaskPanelDtos, nonRepeatTaskPanelDtos);
    }

    private void initSideBar(Set<TaskPanelDto> repeatTaskPanelDtos, Set<TaskPanelDto> nonRepeatTaskPanelDtos) {
        this.sideBar = new HomeSideBar(repeatTaskPanelDtos, nonRepeatTaskPanelDtos);
    }

    private void initBodyPanel() {
        this.bodyPanel = new HomeBodyPanel();
    }

    private void init(Set<TaskPanelDto> repeatTaskPanelDtos, Set<TaskPanelDto> nonRepeatTaskPanelDtos) {
        initSideBar(repeatTaskPanelDtos, nonRepeatTaskPanelDtos);
        addChildComponent(this.sideBar);

        initBodyPanel();
        addChildComponent(this.bodyPanel);
    }

    public void loadRootTaskContentPanel(String taskTitle, Set<TaskPanelDto> childTaskPanelDtos) {
        this.bodyPanel.loadRootTaskContentPanel(taskTitle, childTaskPanelDtos);
    }

    public void loadNodeTaskContentPanel(String taskTitle, Set<TaskPanelDto> childTaskPanelDtos) {
        this.bodyPanel.loadNodeTaskContentPanel(taskTitle, childTaskPanelDtos);
    }

    @Override
    protected void loadChildComponentsSize() {
        this.childComponentSizeMap.clear();

        final int availableWidth = getSize().width - ViewConstant.FRAME_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ViewConstant.FRAME_RESERVE_HEIGHT;

        final int maxChildComponentHeight = availableHeight - MAIN_LAYOUT.getVgap();

        final int sideBarWidth = ViewConstant.SIDEBAR_WIDTH;
        this.childComponentSizeMap.put(this.sideBar, new Dimension(sideBarWidth, maxChildComponentHeight));

        final int bodyPanelWidth = availableWidth - MAIN_LAYOUT.getHgap()
                - sideBarWidth - MAIN_LAYOUT.getHgap();
        this.childComponentSizeMap.put(this.bodyPanel, new Dimension(bodyPanelWidth, maxChildComponentHeight));
    }

    @Override
    public void resize(Dimension dimension) {
        super.resize(dimension);
        ((JFrame) this.component).pack();
    }

    @Override
    protected void setNotResizableChildComponents() {
        this.sideBar.setResizable(false);
        this.bodyPanel.setResizable(false);
    }
}