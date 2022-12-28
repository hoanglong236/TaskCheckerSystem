package org.swing.app.view.home;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.view.components.PanelWrapperComponent;
import org.swing.app.view.components.request.RequestResultProcessor;

public abstract class HomeWrapperComponent extends PanelWrapperComponent {

    protected final HomeFrameController homeFrameController;
    protected final RequestResultProcessor requestResultProcessor = new RequestResultProcessor();

    public HomeWrapperComponent(HomeFrameController homeFrameController) {
        super();
        this.homeFrameController = homeFrameController;
    }
}
