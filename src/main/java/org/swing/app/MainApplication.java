package org.swing.app;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
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


//        JOptionPane.showMessageDialog(null, "abc");
        JDialog dialog = new JDialog(frame, true);
        final JLabel label = new JLabel();
        label.setText("test dialog");
        dialog.add(label);
        dialog.setVisible(true);

        System.out.println("Test dialog modal");
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

        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setText("abc1234567890abc1234567890abc1234567890\nacb");
        panel.add(textArea);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        System.out.println(button.getBounds().width);
    }
}
