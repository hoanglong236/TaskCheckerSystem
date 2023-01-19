package org.swing.app.view.taskform.taskformpanel;

public class LeafTaskFormPanel extends TaskFormPanel {

    public LeafTaskFormPanel() {
        super();
    }

    @Override
    protected boolean isNeedImportantInputWrapper() {
        return false;
    }

    @Override
    protected boolean isNeedDeadlineInputWrapper() {
        return false;
    }

    @Override
    protected boolean isNeedNoteInputWrapper() {
        return false;
    }
}
