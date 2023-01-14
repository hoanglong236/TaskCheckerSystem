package org.swing.app.view.taskform.taskformpanel;

public class RootTaskFormPanel extends TaskFormPanel {

    public RootTaskFormPanel() {
        super();
    }

    @Override
    protected boolean isNeedImportantInputWrapper() {
        return false;
    }

    @Override
    protected boolean isNeedStartDateTimeInputWrapper() {
        return true;
    }

    @Override
    protected boolean isNeedFinishDateTimeInputWrapper() {
        return true;
    }

    @Override
    protected boolean isNeedNoteInputWrapper() {
        return false;
    }
}
