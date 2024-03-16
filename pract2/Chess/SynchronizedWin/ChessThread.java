package Chess.SynchronizedWin;

import java.util.List;

public class ChessThread implements Runnable {
    private final static Object lock = new Object();

    private final List<String> steps;
    private final String name;

    private static int numOfThreads;
    private static int currentThreadId;
    private final int threadId;

    public ChessThread(List<String> steps, String name) {
        threadId = numOfThreads++;
        this.steps = steps;
        this.name = name;
    }

    @Override
    public void run() {
        for (String step: steps) {

            synchronized (lock) {
                while (currentThreadId != threadId) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(name + ": " + step);
                currentThreadId = threadId == numOfThreads - 1 ? 0 : threadId + 1;
                lock.notify();
            }
        }
    }
}