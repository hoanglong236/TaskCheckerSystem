package org.swing.app.view.home;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.components.OptionPane;
import org.swing.app.view.components.PanelWrapperComponent;

public abstract class HomeWrapperComponent extends PanelWrapperComponent {

    protected final HomeFrameController homeFrameController;

    public HomeWrapperComponent(HomeFrameController homeFrameController) {
        super();
        this.homeFrameController = homeFrameController;
    }

    protected void requestFailureHandler() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        OptionPane.showMessageDialog(messageLoader.getMessage("request.failure"));
    }
}
