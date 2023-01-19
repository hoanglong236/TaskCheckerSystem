package org.swing.app.view.taskform.taskformpanel;

public class NodeTaskFormPanel extends TaskFormPanel {

    public NodeTaskFormPanel() {
        super();
    }

    @Override
    protected boolean isNeedImportantInputWrapper() {
        return true;
    }

    @Override
    protected boolean isNeedDeadlineInputWrapper() {
        return true;
    }

    @Override
    protected boolean isNeedNoteInputWrapper() {
        return true;
    }


}
