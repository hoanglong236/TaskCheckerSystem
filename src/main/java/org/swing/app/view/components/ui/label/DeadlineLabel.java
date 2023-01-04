package org.swing.app.view.components.ui.label;

import org.swing.app.common.Constant;
import org.swing.app.common.Observable;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.countdown.Countdown;
import org.swing.app.view.components.countdown.CountdownObserver;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DeadlineLabel extends Label implements CountdownObserver {

    private final Observable countdownObservable;
    private long displayCountDown;
    private long hideCountDown;

    public DeadlineLabel(LocalDateTime startDateTime, LocalDateTime finishDateTime) {
        super("");
        setIcon(ViewConstant.ICON_LOCATION_DEADLINE);
        setValueForCountDowns(startDateTime, finishDateTime);

        this.countdownObservable = Countdown.getInstance();
        this.countdownObservable.registerObserver(this);
    }

    private void setValueForCountDowns(LocalDateTime startDateTime, LocalDateTime finishDateTime) {
        final long originalCountDown = DeadlineLabel.getSecondBetween(startDateTime, finishDateTime);
        final long currentCountDown = DeadlineLabel.getSecondBetween(LocalDateTime.now(), finishDateTime);

        this.displayCountDown = currentCountDown;
        this.hideCountDown = 0;

        if (originalCountDown < currentCountDown) {
            this.displayCountDown = originalCountDown;
            this.hideCountDown = currentCountDown - originalCountDown;
        }
    }

    private static String getCountDownText(long countDown) {
        final StringBuilder deadlineText = new StringBuilder();

        final int day = (int) (countDown / Constant.SECOND_PER_DAY);
        countDown = countDown - day * Constant.SECOND_PER_DAY;
        final byte hour = (byte) (countDown / Constant.SECOND_PER_HOUR);
        countDown = countDown - hour * Constant.SECOND_PER_HOUR;
        final byte minute = (byte) (countDown / Constant.SECOND_PER_MINUTE);
        countDown = countDown - minute * Constant.SECOND_PER_MINUTE;
        final byte second = (byte) countDown;

        deadlineText.append(day);
        deadlineText.append("D : ");
        deadlineText.append(hour);
        deadlineText.append("h : ");
        deadlineText.append(minute);
        deadlineText.append("m : ");
        deadlineText.append(second);
        deadlineText.append("s");

        return deadlineText.toString();
    }

    private static long getSecondBetween(LocalDateTime startDateTime, LocalDateTime finishDateTime) {
        return ChronoUnit.SECONDS.between(startDateTime, finishDateTime);
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
        if (this.hideCountDown > 0) {
            this.hideCountDown--;
            return;
        }

        if (this.displayCountDown > 0) {
            setText(DeadlineLabel.getCountDownText(this.displayCountDown));
            this.displayCountDown--;
            return;
        }

        this.countdownObservable.removeObserver(this);
    }

    @Override
    public void update() {
        decreaseCountDown();
    }
}
