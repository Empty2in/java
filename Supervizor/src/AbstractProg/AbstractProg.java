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

    private final String myLock;
    private ProgState state = ProgState.UNKNOWN;

    public AbstractProg(final String myLock) {
        this.myLock = myLock;
    }

    @Override
    public void run() {
        System.out.println("Abstract program starts working!");

        Thread daemon = new Thread(() -> {

            //the daemon runs until the abstract program thread is interrupted
            while (!Thread.currentThread().isInterrupted()) {
                //specified interval for daemon thread
                imaginaryDelay(1);
                //capturing the monitor of an abstract program object by a daemon
                synchronized (myLock) {
                    state = ProgState.values()[new Random().nextInt(ProgState.values().length)]; //set a random state
                    System.out.println(STR."Daemon sets new state: \{state}");
                    myLock.notifyAll();
                }
            }
        });

        daemon.setDaemon(true);
        daemon.start();

        //the abstract program runs until thread is interrupted
        while (!Thread.currentThread().isInterrupted()) {
            abstractWork();
        }
    }

    public ProgState getState() {
        return state;
    }

    public String getLock() {
        return myLock;
    }

    public void setState(final ProgState state) {
        synchronized (myLock) {
            this.state = state;
        }
    }

    private void imaginaryDelay(final int timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread was interrupted");
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

