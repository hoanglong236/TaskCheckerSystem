package org.swing.app.view.components.countdown;

import org.swing.app.common.Observer;

public interface CountdownObserver extends Observer {

    void decreaseCountDown();
}
