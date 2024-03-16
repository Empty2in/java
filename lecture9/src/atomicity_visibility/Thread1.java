package atomicity_visibility;

public class Thread1 extends Thread {

    private final Incrementor incrementor;

    public Thread1(Incrementor incrementor) {
        this.incrementor = incrementor;
        this.start();
    }

    public void run() {
        incrementor.increment();
    }
}