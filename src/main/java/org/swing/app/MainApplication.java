package org.swing.app;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.home.HomeFrame;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.util.LinkedHashSet;
import java.util.Set;

public class MainApplication {

    public static void main(String[] args) {
        Set<TaskPanelDto> repeatTaskPanelDtos = new LinkedHashSet<>();
        Set<TaskPanelDto> nonRepeatTaskPanelDtos = new LinkedHashSet<>();
        HomeFrame homeFrame = new HomeFrame(repeatTaskPanelDtos, nonRepeatTaskPanelDtos);

        homeFrame.resize(ViewConstant.HOME_FRAME_PREFER_SIZE);

        // must be set finally, Neu khong thi frame se hien thi nhung diem anh ko mong muon
        homeFrame.setVisible(true);

        homeFrame.resize(new Dimension(900, 650));
//
//        JFrame frame = new JFrame();
//        frame.setPreferredSize(new Dimension(1140, 650));
//
//
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
    }
}
