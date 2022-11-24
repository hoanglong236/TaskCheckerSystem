package org.swing.app.view.components.factory;

import javax.swing.*;

public class JComponentFactoryImpl implements JComponentFactory {

    @Override
    public JComponent createJPanel() {
        return new JPanel();
    }

    @Override
    public JComponent createJScrollPane() {
        return new JScrollPane();
    }

    @Override
    public JComponent createJLabel() {
        return new JLabel();
    }

    @Override
    public JComponent createJTextField() {
        return new JTextField();
    }

    @Override
    public JComponent createJTextArea() {
        return new JTextArea();
    }

    @Override
    public JComponent createJComboBox() {
        return new JComboBox<>();
    }

    @Override
    public JComponent createJPopupMenu() {
        return new JPopupMenu();
    }

    @Override
    public JComponent createJMenuItem() {
        return new JMenuItem();
    }

    @Override
    public JComponent createJButton() {
        return new JButton();
    }

    @Override
    public JComponent createJCheckBox() {
        return new JCheckBox();
    }
}
