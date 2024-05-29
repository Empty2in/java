package AbstractProg;

public interface AbstractProgInterface extends Runnable {
    void run();
    State getState();
    String getLock();
    void setState(final State state);
}
