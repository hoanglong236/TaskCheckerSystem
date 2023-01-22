package org.swing.app.view.home.observer.taskcompletionrate;

import java.util.LinkedHashSet;
import java.util.Set;

public class TaskCompletionRateEventSubject {

    private final Set<TaskCompletionRateEventObserver> observers = new LinkedHashSet<>();

    public TaskCompletionRateEventSubject() {
    }

    public void registerObserver(TaskCompletionRateEventObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(TaskCompletionRateEventObserver observer) {
        this.observers.remove(observer);
    }

    public void notifyObserversToUpdateCompletionRate(int completedChildTaskCount, int childTaskCount) {
        for (final TaskCompletionRateEventObserver observer : this.observers) {
            observer.handleUpdateCompletionRate(completedChildTaskCount, childTaskCount);
        }
    }
}
