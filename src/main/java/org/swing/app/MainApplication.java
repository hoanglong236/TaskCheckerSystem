package org.swing.app;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.home.HomeFrame;

import java.awt.Dimension;
import java.util.LinkedHashSet;
import java.util.Set;

public class MainApplication {

    public static void main(String[] args) {
        Set<TaskPanelDto> repeatTaskPanelDtos = new LinkedHashSet<>();
        Set<TaskPanelDto> nonRepeatTaskPanelDtos = new LinkedHashSet<>();
        HomeFrame homeFrame = new HomeFrame(repeatTaskPanelDtos, nonRepeatTaskPanelDtos);
        homeFrame.resize(ViewConstant.HOME_FRAME_PREFER_SIZE);
    }
}
