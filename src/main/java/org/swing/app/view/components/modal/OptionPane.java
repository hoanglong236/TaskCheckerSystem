package org.swing.app.view.components.modal;

import javax.swing.JOptionPane;

public class OptionPane {

    public static final byte YES_DIALOG_OPTION = 0;
    public static final byte NO_DIALOG_OPTION = 1;
    public static final byte CLOSED_DIALOG_OPTION = 2;

    public static void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public static byte showConfirmDialog(String confirmQuestion, String confirmTitle) {
        final int confirmResult = JOptionPane.showConfirmDialog(null, confirmQuestion, confirmTitle,
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        switch (confirmResult) {
            case JOptionPane.YES_OPTION:
                return YES_DIALOG_OPTION;
            case JOptionPane.NO_OPTION:
                return NO_DIALOG_OPTION;
            default:
                return CLOSED_DIALOG_OPTION;
        }
    }
}
