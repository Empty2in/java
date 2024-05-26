package AbstractProg;

public abstract class AbstractClassProg implements Runnable {
    public abstract void run();
    public abstract State getState();
    public abstract void setState(final State state);

    public abstract String getName();
}
