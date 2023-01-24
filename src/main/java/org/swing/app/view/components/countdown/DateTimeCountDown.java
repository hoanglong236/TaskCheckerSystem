package org.swing.app.view.components.countdown;

import org.swing.app.common.Constants;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateTimeCountDown {

    private int day = 0;
    private byte hour = 0;
    private byte minute = 0;
    private byte second = 0;

    public DateTimeCountDown() {
    }

    public DateTimeCountDown(LocalDateTime startDateTime, LocalDateTime finishDateTime) {
        if (finishDateTime.isAfter(startDateTime)) {
            setTimeProperties(startDateTime, finishDateTime);
        }
    }

    private void setTimeProperties(LocalDateTime startDateTime, LocalDateTime finishDateTime) {
        long durationSeconds = ChronoUnit.SECONDS.between(startDateTime, finishDateTime);

        this.day = (int) (durationSeconds / Constants.SECOND_PER_DAY);
        durationSeconds -= this.day * Constants.SECOND_PER_DAY;
        this.hour = (byte) (durationSeconds / Constants.SECOND_PER_HOUR);
        durationSeconds -= this.hour * Constants.SECOND_PER_HOUR;
        this.minute = (byte) (durationSeconds / Constants.SECOND_PER_MINUTE);
        durationSeconds -= this.minute * Constants.SECOND_PER_MINUTE;
        this.second = (byte) durationSeconds;
    }

    public void update(LocalDateTime startDateTime, LocalDateTime finishDateTime) {
        setTimeProperties(startDateTime, finishDateTime);
    }

    private void resetHour() {
        this.hour = 23;
    }

    private void resetMinute() {
        this.minute = 59;
    }

    private void resetSecond() {
        this.second = 59;
    }

    public boolean isFinish() {
        return this.day == 0 && this.hour == 0 && this.minute == 0 && this.second == 0;
    }

    public String getCountDownString() {
        final StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(this.day);
        stringBuilder.append("d : ");
        stringBuilder.append(this.hour);
        stringBuilder.append("h : ");
        stringBuilder.append(this.minute);
        stringBuilder.append("m");

        return stringBuilder.toString();
    }

    public void decreaseCountDown() {
        if (this.second > 0) {
            this.second--;
            return;
        }

        if (this.minute > 0) {
            this.minute--;
            resetSecond();
            return;
        }

        if (this.hour > 0) {
            this.hour--;
            resetMinute();
            resetSecond();
            return;
        }

        if (this.day > 0) {
            this.day--;
            resetHour();
            resetMinute();
            resetSecond();
        }
    }
}
