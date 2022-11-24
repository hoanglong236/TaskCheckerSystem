package org.swing.app.view.components.factory;

import javax.swing.JComponent;

public interface JComponentFactory {

    JComponent createJPanel();
    JComponent createJScrollPane();
    JComponent createJLabel();
    JComponent createJTextField();
    JComponent createJTextArea();
    JComponent createJComboBox();
    JComponent createJPopupMenu();
    JComponent createJMenuItem();
    JComponent createJButton();
    JComponent createJCheckBox();
}
