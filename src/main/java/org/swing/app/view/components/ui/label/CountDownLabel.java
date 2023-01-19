package org.swing.app.view.components.ui.label;

import org.swing.app.common.Observable;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.countdown.Countdown;
import org.swing.app.view.components.countdown.CountdownObserver;
import org.swing.app.view.components.countdown.DateTimeCountDown;

import java.awt.Color;
import java.time.LocalDateTime;

public class CountDownLabel extends Label implements CountdownObserver {

    private final Observable countdownObservable;
    private DateTimeCountDown deadlineCountDown;

    private final String lateText;

    public CountDownLabel(LocalDateTime deadline, String lateText) {
        super("");
        setIcon(ViewConstant.ICON_LOCATION_DEADLINE);
        setValueForCountDown(deadline);

        this.lateText = lateText;
        this.countdownObservable = Countdown.getInstance();
        this.countdownObservable.registerObserver(this);
    }

    private void setValueForCountDown(LocalDateTime deadline) {
        this.deadlineCountDown = new DateTimeCountDown(LocalDateTime.now(), deadline);
    }

    public void update(LocalDateTime deadline) {
        setValueForCountDown(deadline);
        if (!((Countdown) this.countdownObservable).isObserverRegistered(this)) {
            this.countdownObservable.registerObserver(this);
        }
    }

    @Override
    public void cancelAllEventListeners() {
        super.cancelAllEventListeners();
        this.countdownObservable.removeObserver(this);
    }

    @Override
    public void decreaseCountDown() {
        if (!this.deadlineCountDown.isFinish()) {
            this.deadlineCountDown.decreaseCountDown();
            setText(this.deadlineCountDown.toString());
            return;
        }

        this.countdownObservable.removeObserver(this);
        setText(this.lateText);
        setForegroundColor(Color.red);
    }
}
