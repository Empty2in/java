package AbstractProg;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AbstractProg implements AbstractProgInterface {

    private final String myLock;
    private State state = State.UNKNOWN;
    private static final Logger logger = Logger.getLogger(AbstractProg.class.getName());

    public AbstractProg(final String myLock) {
        this.myLock = myLock;
    }

    @Override
    public void run() {
        System.out.println("Abstract program starts working!");

        Thread daemon = new Thread(() -> {
            Random random = new Random();
            while (!Thread.currentThread().isInterrupted()) {
                imaginaryDelay(1);
                synchronized (myLock) {
                    state = State.values()[random.nextInt(State.values().length)];
                    System.out.println(STR."Daemon sets new state: \{state}");
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
    @Override
    public State getState() {
        return state;
    }

    @Override
    public String getLock() {
        return myLock;
    }

    @Override
    public void setState(final State state) {
        this.state = state;
    }

    private void imaginaryDelay(final int timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.log(Level.SEVERE, "Thread was interrupted", e);
        }
    }
    private void abstractWork() {
        int letTryCountMaxInt = 0;
        while (letTryCountMaxInt < Integer.MAX_VALUE) {
            letTryCountMaxInt++;
        }
    }
}

