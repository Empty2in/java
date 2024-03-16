package Chess.FirstChessThreads;

import java.util.List;

public class TakeStepsThread implements Runnable {
    List<String> steps;
    String name;

    private boolean readyNext = true;

    public TakeStepsThread(List<String> newSteps, String newName) {
        steps = newSteps;
        name = newName;
    }

    @Override
    public void run() {
        for (String step: steps) {
            this.print(step);

            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread Interrupted");
            }
            readyNext = true;
        }
    }

    private synchronized void print(String step) {
        while (!readyNext) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread Interrupted");
            }
        }
        readyNext = false;
        System.out.println(name + " " + step);
        notify();
    }
}
