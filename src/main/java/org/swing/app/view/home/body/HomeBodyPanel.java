package org.swing.app.view.home.body;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.components.WrapperComponent;
import org.swing.app.view.home.components.TaskCenterPanel;
import org.swing.app.view.home.components.TaskContentPanel;

public class HomeBodyPanel extends WrapperComponent {

    private TaskContentPanel rootTaskContentPanel;
    private TaskCenterPanel nodeTaskContentPanel;

    public HomeBodyPanel() {
        super();
        this.rootTaskContentPanel = null;
        this.nodeTaskContentPanel = null;
    }

    public void loadRootTaskContentPanel() {

    }

    public void loadNodeTaskContentPanel() {
    }

    @Override
    protected void loadChildComponentsSize() {

    }

    @Override
    protected void setNotResizableChildComponents() {

    }


}
