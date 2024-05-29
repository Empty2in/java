package AbstractProg;

public interface AbstractProgInterface extends Runnable {
    void run();
    State getState();
    void setState(final State state);
}
