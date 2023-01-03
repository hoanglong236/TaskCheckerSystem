package org.swing.app;

import org.swing.app.controller.HomeFrameController;

public class MainApplication {

    public static void main(String[] args) {
        final HomeFrameController homeFrameController = new HomeFrameController();
        homeFrameController.startHomeFrame();
    }
}
