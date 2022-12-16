package org.swing.app;

import org.swing.app.controller.HomeFrameController;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApplication {

    public static void main(String[] args) {
        final HomeFrameController homeFrameController = new HomeFrameController();
        homeFrameController.startHomeFrame();
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

        JButton button = new JButton("Click Me!");
        final JLabel label = new JLabel();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {"Yes! Please.", "No! Not now."};
                int result = JOptionPane.showConfirmDialog(
                        panel,
                        "Sure? You want to exit?",
                        "Swing Tester",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
//                        null,     //no custom icon
//                        options,  //button titles
//                        options[0] //default button
                );
                System.out.println("Test");
                if(result == JOptionPane.YES_OPTION){
                    label.setText("You selected: Yes! Please");
                }else if (result == JOptionPane.NO_OPTION){
                    label.setText("You selected: No! Not now.");
                }else {
                    label.setText("None selected");
                }
            }
        });

        panel.add(button);
        panel.add(label);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }
}
