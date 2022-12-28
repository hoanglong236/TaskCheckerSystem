package org.swing.app.view.components.request;

import org.swing.app.util.MessageLoader;
import org.swing.app.view.components.OptionPane;

public class RequestResultProcessor {

    public void requestWaitingHandler() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        OptionPane.showMessageDialog(messageLoader.getMessage("request.waiting"));
    }

    public void requestWaitingHandler(String message) {
        OptionPane.showMessageDialog(message);
    }
}
