package org.swing.app.view.components.factory;

import javax.swing.*;

public class JComponentFactory {

    public static JComponent createJPanel() {
        return new JPanel();
    }

    public static JComponent createJScrollPane() {
        return new JScrollPane();
    }

    public static JComponent createJLabel() {
        return new JLabel();
    }

    public static JComponent createJTextField() {
        return new JTextField();
    }

    public static JComponent createJTextArea() {
        return new JTextArea();
    }

    public static JComponent createJComboBox() {
        return new JComboBox<>();
    }

    public static JComponent createJPopupMenu() {
        return new JPopupMenu();
    }

    public static JComponent createJMenuItem() {
        return new JMenuItem();
    }

    public static JComponent createJButton() {
        return new JButton();
    }

    public static JComponent createJCheckBox() {
        return new JCheckBox();
    }
}
