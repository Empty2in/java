package atomicity_visibility;

public class Thread2 extends Thread {
    private Incrementor incrementor;

    public Thread2(Incrementor incrementor) {
        this.incrementor = incrementor;
        this.start();
    }

    public void run() {
        incrementor.showResult();
    }
}
