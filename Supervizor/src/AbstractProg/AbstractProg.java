package AbstractProg;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AbstractProg implements Runnable {

    public static State state = State.UNKNOWN;
    public static final Object myLock = new Object();

    @Override
    public void run() {
        System.out.println("Abstract program starts working!");

        Thread daemon = new Thread(() -> {
            Random random = new Random();
            while (!Thread.currentThread().isInterrupted()) {
                imaginaryDelay(5);
                synchronized (myLock) {
                    state = State.values()[random.nextInt(State.values().length)];
                    System.out.println("Daemon sets new state: " + state);
                    myLock.notifyAll();
                }

            }
        });

        daemon.setDaemon(true);
        daemon.start();

        while (!Thread.currentThread().isInterrupted()) {
            abstractWork();
        }
    }

    private void imaginaryDelay(final int timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void abstractWork() {
        int letTryCountMaxInt = 0;
        while (letTryCountMaxInt < Integer.MAX_VALUE) {
            letTryCountMaxInt++;
        }
    }
}

