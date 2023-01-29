package org.swing.app.view.components.ui.label;

import org.swing.app.view.common.ComponentSizeConstants;
import org.swing.app.view.common.IconUrlConstants;
import org.swing.app.view.components.countdown.CountDownSubject;
import org.swing.app.view.components.countdown.CountdownObserver;
import org.swing.app.view.components.countdown.CountdownTimer;
import org.swing.app.view.components.countdown.DateTimeCountDown;

import java.awt.Color;
import java.time.LocalDateTime;

public class CountDownLabel extends Label implements CountdownObserver {

    private final CountdownTimer countdownTimer = CountdownTimer.getInstance();
    private DateTimeCountDown deadlineCountDown;

    private String finishCountDownText;

    public CountDownLabel(LocalDateTime deadline) {
        super("");
        setIcon(IconUrlConstants.DEADLINE_ICON,
                ComponentSizeConstants.SMALL_ICON_WIDTH, ComponentSizeConstants.SMALL_ICON_HEIGHT);
        setValueForCountDown(deadline);
        init();
    }

    private void init() {
        final CountDownSubject countDownSubject = this.countdownTimer.getCountDownSubject();
        countDownSubject.registerObserver(this);
    }

    public void setFinishCountDownText(String finishCountDownText) {
        this.finishCountDownText = finishCountDownText;
    }

    private void setValueForCountDown(LocalDateTime deadline) {
        if (deadline == null) {
            this.deadlineCountDown = new DateTimeCountDown();
        } else {
            this.deadlineCountDown = new DateTimeCountDown(LocalDateTime.now(), deadline);
        }
    }

    public void update(LocalDateTime deadline) {
        setValueForCountDown(deadline);

        final CountDownSubject countDownSubject = this.countdownTimer.getCountDownSubject();
        if (countDownSubject.isObserverExist(this)) {
            return;
        }
        countDownSubject.registerObserver(this);
    }

    @Override
    public void cancelAllEventListeners() {
        super.cancelAllEventListeners();

        final CountDownSubject countDownSubject = this.countdownTimer.getCountDownSubject();
        if (countDownSubject.isObserverExist(this)) {
            countDownSubject.removeObserver(this);
        }
    }

    @Override
    public void decreaseCountDown() {
        this.deadlineCountDown.decreaseCountDown();
        setText(this.deadlineCountDown.getCountDownString());
    }

    @Override
    public boolean isCountDownFinish() {
        return this.deadlineCountDown.isFinish();
    }

    @Override
    public void handleCountDownFinish() {
        final CountDownSubject countDownSubject = this.countdownTimer.getCountDownSubject();
        countDownSubject.removeObserver(this);

        setText(this.finishCountDownText);
        setForegroundColor(Color.red);
    }
}
