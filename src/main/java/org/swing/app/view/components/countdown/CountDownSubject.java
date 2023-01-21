package org.swing.app.view.components.countdown;

import java.util.LinkedHashSet;
import java.util.Set;

public class CountDownSubject {

    private final Set<CountdownObserver> observers = new LinkedHashSet<>();

    public void registerObserver(CountdownObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(CountdownObserver observer) {
        this.observers.remove(observer);
    }

    public boolean isObserverExist(CountdownObserver observer) {
        return this.observers.contains(observer);
    }

    public void notifyObserversToDecreaseCountDown() {
        for (final CountdownObserver observer : this.observers) {
            observer.decreaseCountDown();
        }
    }
}
