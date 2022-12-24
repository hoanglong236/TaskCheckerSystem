package org.swing.app;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApplication {

    public static void main(String[] args) {
//        final HomeFrameController homeFrameController = new HomeFrameController();
//        homeFrameController.startHomeFrame();
        createWindow();
    }

    private static void createWindow() {
        JFrame frame = new JFrame("Swing Tester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createUI(frame);
        frame.setSize(560, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    private static void createUI(final JFrame frame){
        JPanel panel = new JPanel();
        LayoutManager layout = new FlowLayout();
        panel.setLayout(layout);

        final JButton button = new JButton("Click Me!");
        final JLabel label = new JLabel();

        final JPopupMenu menu = new JPopupMenu();
        final JMenuItem testItem = new JMenuItem("test item");
        testItem.setText("abc");

        menu.add(testItem);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.show(button, 0, button.getBounds().height);
            }
        });

        panel.add(button);
        panel.add(label);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        System.out.println(button.getBounds().width);
    }
}
