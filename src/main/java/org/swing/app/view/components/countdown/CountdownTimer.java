package org.swing.app.view.components.countdown;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CountdownTimer implements ActionListener {

    private static final CountdownTimer UNIQUE_INSTANCE = new CountdownTimer();

    private final CountDownSubject countDownSubject = new CountDownSubject();

    private final Timer timer = new Timer(1000, null);

    private CountdownTimer() {
        this.timer.addActionListener(this);
        start();
    }

    public static CountdownTimer getInstance() {
        return UNIQUE_INSTANCE;
    }

    public CountDownSubject getCountDownSubject() {
        return countDownSubject;
    }

    public void start() {
        this.timer.start();
    }

    public void stop() {
        this.timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.countDownSubject.notifyObserversToDecreaseCountDown();
    }
}