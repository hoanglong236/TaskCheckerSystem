package org.swing.app.view.components.countdown;

public interface CountdownObserver {

    void decreaseCountDown();

    boolean isCountDownFinish();

    void handleCountDownFinish();
}
