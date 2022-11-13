package org.swing.app.common;

public interface Observable {

    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
