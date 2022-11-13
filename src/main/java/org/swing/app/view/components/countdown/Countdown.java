package org.swing.app.view.components.countdown;

import org.swing.app.common.Observable;
import org.swing.app.common.Observer;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Countdown implements Observable, ActionListener {

    private static final Set<CountdownObserver> COUNTDOWN_OBSERVERS = new LinkedHashSet<>();
    private static final Timer TIMER = new Timer(1000, null);
    private static final Countdown UNIQUE_INSTANCE = new Countdown();

    private Countdown() {
        TIMER.addActionListener(this);
        start();
    }

    public static Countdown getInstance() {
        return UNIQUE_INSTANCE;
    }

    public void start() {
        TIMER.start();
    }

    public void stop() {
        TIMER.stop();
    }

    public boolean isObserverRegistered(Observer observer) {
        return COUNTDOWN_OBSERVERS.contains(observer);
    }

    @Override
    public void registerObserver(Observer observer) {
        if (observer instanceof CountdownObserver) {
            COUNTDOWN_OBSERVERS.add((CountdownObserver) observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        COUNTDOWN_OBSERVERS.remove(observer);
    }

    @Override
    public void notifyObservers() {
        final Iterator<CountdownObserver> countdownObserverIterator = COUNTDOWN_OBSERVERS.iterator();

        while (countdownObserverIterator.hasNext()) {
            final CountdownObserver countdownObserver = countdownObserverIterator.next();
            countdownObserver.decreaseCountDown();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        notifyObservers();
    }
}