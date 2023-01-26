package org.swing.app.view.components.countdown;

/**
 * Maximum delay time between tasks is 1s
 *
 * Convention:
 *     Real-time axis: Ot
 *     The split points on the Ot axis are 1 sec apart and all belong to the sets of natural numbers N
 *
 *     Timer time axis: Ot'
 *     The split points on the Ot' axis are 1 sec apart and all belong to the sets of natural numbers N
 *
 * On the Ot axis,
 *     a' is the time (sec) when the Timer is initialized (a' > 0, a' belongs to R),
 *     a is the nearest separating point (a <= a', a belongs to N)
 * => 0 <= x = a' - a < 1
 *
 * On the Ot' axis,
 *     b' is the time (sec) when the Task is initialized (b' > 0, b' belongs to R),
 *     b is the nearest separating point (b <= b', b belongs to N)
 * => 0 <= y = b' - b < 1
 *
 * On the Ot' axis, at the nth cycle:
 *     The corresponding position of the Task: T' = n + y (each cycle is 1 sec)
 *
 * Reference the Task position from the Ot' axis to the Ot axis:
 *     New position of Task: T = a' + T'
 *                             = a + x + n + y
 *                             = (a + n) + (x + y)
 *
 *                         => a + n <= T < a + n + 2
 * This means that the two tasks have real time difference if and only if
 *     T1 < a + n + 1 and a + n + 1 <= T2
 * <=> 0 <= x + y1 < 1 and 1 <= x + y2 < 2
 *
 * In case T is an integer, then T will have the set of values: {a + n, a + n + 1}
 * Therefore, the maximum time difference between 2 tasks is 1s.
 */
public interface CountdownObserver {

    void decreaseCountDown();
}
