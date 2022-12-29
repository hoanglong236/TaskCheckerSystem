package org.swing.app.view.home;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.view.components.PanelWrapperComponent;

public abstract class HomeWrapperComponent extends PanelWrapperComponent {

    protected final HomeFrameController homeFrameController;

    public HomeWrapperComponent(HomeFrameController homeFrameController) {
        super();
        this.homeFrameController = homeFrameController;
    }
}
