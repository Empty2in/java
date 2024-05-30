package AbstractProg;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Class Abstract Program
 * runs in a separate thread
 * has a state variable
 * and a daemon thread that sets a random state
 */
public class AbstractProg implements Runnable {

    private final Object myLock; //mutex
    private ProgState state = ProgState.UNKNOWN;

    public AbstractProg(final Object myLock) {
        this.myLock = myLock;
    }

    @Override
    public void run() {
        System.out.println("Abstract program starts working!");
        Thread progThread = Thread.currentThread();

        Thread daemon = new Thread(() -> {

            //the daemon runs until the abstract program thread is interrupted
            while (!progThread.isInterrupted()) {
                //specified interval for daemon thread
                imaginaryDelay(1);
                //capturing the monitor of an abstract program object by a daemon
                synchronized (myLock) {

                    //set a random state without UNKNOWN
                    //nexInt(n) - give value from 0 to n-1
                    //we want int from 1 to n-1, so +1, but for not go out index before length-1
                    state = ProgState.values()[
                                new Random().nextInt(ProgState.values().length - 1) + 1
                            ];

                    System.out.println(STR."Daemon sets new state: \{state}");
                    myLock.notifyAll(); //give monitor to supervisor
                }
            }
        });

        daemon.setDaemon(true);
        daemon.start();

        //the abstract program runs until thread is interrupted
        while (!progThread.isInterrupted()) {
            abstractWork();
        }
    }

    public ProgState getState() {
        return state;
    }

    public Object getLock() {
        return myLock;
    }

    public void setState(final ProgState state) {
        this.state = state;
    }

    private void imaginaryDelay(final int timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread was interrupted in imaginaryDelay");
        }
    }

    //some work that an abstract program can do
    private void abstractWork() {
        int letTryCountMaxInt = 0;
        while (letTryCountMaxInt < Integer.MAX_VALUE) {
            letTryCountMaxInt++;
        }
    }
}

