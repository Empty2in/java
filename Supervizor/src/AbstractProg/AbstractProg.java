package AbstractProg;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AbstractProg extends AbstractClassProg {

    private final String name;

    private State state = State.UNKNOWN;
    public static final Object myLock = new Object();

    public AbstractProg(String name) {
        this.name = name;
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
                    System.out.println(STR."Daemon sets new state: \{state} for \{name}");
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
    public void setState(final State state) {
        this.state = state;
    }

    @Override
    public String getName(){
        return name;
    }

    private void imaginaryDelay(final int timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    private void abstractWork() {
        int letTryCountMaxInt = 0;
        while (letTryCountMaxInt < Integer.MAX_VALUE) {
            letTryCountMaxInt++;
        }
    }
}

