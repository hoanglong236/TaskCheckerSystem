package org.swing.app.view.components.ui.label;

import org.swing.app.common.Observable;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.countdown.Countdown;
import org.swing.app.view.components.countdown.CountdownObserver;
import org.swing.app.view.components.countdown.DateTimeCountDown;

import java.time.LocalDateTime;

public class DeadlineLabel extends Label implements CountdownObserver {

    private final Observable countdownObservable;
    private DateTimeCountDown displayDeadlineTextCountDown;
    private DateTimeCountDown hideDeadlineTextCountDown;

    public DeadlineLabel(LocalDateTime startDateTime, LocalDateTime finishDateTime) {
        super("");
        setIcon(ViewConstant.ICON_LOCATION_DEADLINE);
        setValueForCountDowns(startDateTime, finishDateTime);

        this.countdownObservable = Countdown.getInstance();
        this.countdownObservable.registerObserver(this);
    }

    private void setValueForCountDowns(LocalDateTime startDateTime, LocalDateTime finishDateTime) {
        final LocalDateTime currentDateTime = LocalDateTime.now();

        if (currentDateTime.isBefore(startDateTime)) {
            this.hideDeadlineTextCountDown = new DateTimeCountDown(startDateTime, currentDateTime);
            this.displayDeadlineTextCountDown = new DateTimeCountDown(currentDateTime, finishDateTime);
        } else {
            this.hideDeadlineTextCountDown = new DateTimeCountDown();
            this.displayDeadlineTextCountDown = new DateTimeCountDown(startDateTime, finishDateTime);
        }
    }

    public void update(LocalDateTime startDateTime, LocalDateTime finishDateTime) {
        setValueForCountDowns(startDateTime, finishDateTime);
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
        if (!this.hideDeadlineTextCountDown.isFinish()) {
            this.hideDeadlineTextCountDown.decreaseCountDown();
            return;
        }

        if (!this.displayDeadlineTextCountDown.isFinish()) {
            setText(this.displayDeadlineTextCountDown.toString());
            this.displayDeadlineTextCountDown.decreaseCountDown();
            return;
        }

        this.countdownObservable.removeObserver(this);
    }
}
