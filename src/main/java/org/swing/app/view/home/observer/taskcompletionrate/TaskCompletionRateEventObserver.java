package org.swing.app.view.home.observer.taskcompletionrate;

public interface TaskCompletionRateEventObserver {

    void handleUpdateCompletionRate(int completedChildTaskCount, int childTaskCount);
}
